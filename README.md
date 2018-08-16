## 动态定时任务 & 特定时间点运行任务
1. 用户只在com.hannea.job里面增加自己的业务job就行，然后在com.hannea.model.JobKeyMap里面
   
   维护名称，类路径，执行发放，当然这个程序也是有漏洞的，是个很简单的动态创建定时任务，只要程序
   
   重启定时任务就将消失，复杂的程序可以参考 :
   
   https://github.com/MicroCountry/ddy-schedule-job

2. 创建任务
```$xslt
    @Test
    public void testCreate() throws Exception{
        ScheduleJob scheduleJob = new ScheduleJob();
        //任务id需要唯一
        scheduleJob.setScheduleJobId(1L);
        //任务组id，这个如果没有分组要求，默认为一个id
        scheduleJob.setScheduleJobGroupId(1L);
        
        //这里可以设置需要传递的自定义参数
        JSONObject jsonObject = new JSONObject();        
        jsonObject.put("activityId", "1");
        scheduleJob.setParams(jsonObject.toJSONString());
        
        //任务名
        scheduleJob.setScheduleJobName("活动" + 1);
        //时间自己设置，不要早于当前时间
        String startCor = CronExpressionUtil.getCronExpressionByTime(new Date());
        scheduleJob.setScheduleJobCronExpression(startCor);
        
        //ACTIVITY 是需要在JobKeyMap中事先定义好，程序根据这个key，反射找到对应的执行类和执行方法
        taskService.createTaskJob("ACTIVITY", scheduleJob);

        //测试需要，正式不需要这个
        Thread.sleep(200000);
    }
    
    //根据jobid和组id删除某个定时任务
    @Test
    public void stopJob(){
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setScheduleJobId(1L);
        scheduleJob.setScheduleJobGroupId(1L);
        taskService.stopTaskJob("ACTIVITY", scheduleJob);
    }
```

