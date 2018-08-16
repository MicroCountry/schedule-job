package com.hannea;

import com.alibaba.fastjson.JSONObject;
import com.hannea.model.ScheduleJob;
import com.hannea.service.TaskService;
import com.hannea.util.CronExpressionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Class
 *
 * @author wgm
 * @date 2018/08/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCreateJob {

    @Autowired
    private TaskService taskService;

    /**
     * 创建人物
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception{
        ScheduleJob scheduleJob = new ScheduleJob();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("activityId", "1");
        scheduleJob.setScheduleJobId(1L);
        scheduleJob.setScheduleJobGroupId(1L);
        scheduleJob.setParams(jsonObject.toJSONString());
        scheduleJob.setScheduleJobName("活动" + 1);
        //时间自己设置，不要早于当前时间
        String startCor = CronExpressionUtil.getCronExpressionByTime(new Date());
        scheduleJob.setScheduleJobCronExpression(startCor);
        taskService.createTaskJob("ACTIVITY", scheduleJob);

        Thread.sleep(200000);
    }

    @Test
    public void stopJob(){
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setScheduleJobId(1L);
        scheduleJob.setScheduleJobGroupId(1L);
        taskService.stopTaskJob("ACTIVITY", scheduleJob);
    }

}
