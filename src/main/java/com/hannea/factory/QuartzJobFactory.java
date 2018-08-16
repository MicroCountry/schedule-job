package com.hannea.factory;

import com.hannea.model.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Class
 *
 * @author wgm
 * @date 2018/06/10
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job{

    private static final Logger console = LoggerFactory.getLogger(QuartzJobFactory.class);
    private static Map<String, Class<?>> maps = new HashMap<String, Class<?>>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        console.info("执行任务："+scheduleJob.getScheduleJobName());
        try {
            String className = scheduleJob.getScheduleJobClass();

            Class<?> exec = null;
            if(maps.containsKey(className)){
                exec = maps.get(className);
            }else{
                ClassLoader clazz = Thread.currentThread().getContextClassLoader();
                if(null==clazz) {
                    clazz = QuartzJobFactory.class.getClassLoader();
                }
                exec=clazz.loadClass(className);
                maps.put(className, exec);
            }
            try {
                Method method = exec.getMethod(scheduleJob.getScheduleJobMethod(), ScheduleJob.class);
                //这里调用直接使用 scheduleJob 参数
                method.invoke(exec.newInstance(), scheduleJob);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            } catch (SecurityException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
