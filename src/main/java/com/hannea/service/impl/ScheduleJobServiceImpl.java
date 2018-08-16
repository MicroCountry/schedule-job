package com.hannea.service.impl;

import com.hannea.factory.SchedulerFactory;
import com.hannea.model.ScheduleJob;
import com.hannea.service.ScheduleJobService;
import com.hannea.util.BeanUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Class
 *
 * @author wgm
 * @date 2018/06/10
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    private BeanUtil beanUtil = new BeanUtil();

    @Override
    public ScheduleJob createSchedule(ScheduleJob scheduleJob) {
        thaw(scheduleJob);
        return scheduleJob;
    }

    @Override
    public void thaw(ScheduleJob scheduleJob) {
        if(scheduleJob == null){
            throw new RuntimeException("启动错误,定时器不存在");
        }
        //执行方法验证是否通过验证
        verifySchedule(scheduleJob);
        //验证表达式是否正确
        SchedulerFactory.verifyTrigger(scheduleJob);
        //添加到执行队列中
        SchedulerFactory.add(scheduleJob, true);
    }

    @Override
    public void restart(ScheduleJob scheduleJob) {

        //重启定时器
        SchedulerFactory.reStart(scheduleJob);
    }


    @Override
    public void delete(ScheduleJob scheduleJob) {
        //删除定时器
        SchedulerFactory.del(scheduleJob);
    }


    @Override
    public void stop(ScheduleJob scheduleJob) {
        //执行方法验证是否通过验证
        verifySchedule(scheduleJob);
        //在队列中停止
        SchedulerFactory.stop(scheduleJob);
    }

    @Override
    public void unscheduleJob(ScheduleJob scheduleJob) {
        SchedulerFactory.unscheduleJob(scheduleJob);
    }

    public void verifySchedule(ScheduleJob scheduleJob){
        Class<?> class1 = beanUtil.classExists(scheduleJob.getScheduleJobClass());
        if(class1!=null){
            Method method = null;
            if(null!=(method=beanUtil.methodExists(class1,scheduleJob.getScheduleJobMethod()))){

            }else{
                throw new RuntimeException("执行方法不存在此调用类中.");
            }
        }else{
            throw new RuntimeException("调用类不存在此系统中.");
        }
    }

}
