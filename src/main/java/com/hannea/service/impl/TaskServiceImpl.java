package com.hannea.service.impl;

import com.hannea.factory.SchedulerFactory;
import com.hannea.model.JobKeyMap;
import com.hannea.model.ScheduleJob;
import com.hannea.service.ScheduleJobService;
import com.hannea.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class
 *
 * @author wgm
 * @date 2018/06/10
 */
@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private ScheduleJobService scheduleJobService;

    @Override
    public String createTaskJob(String jobKey, ScheduleJob scheduleJob) {
        JobKeyMap jobKeyMap = JobKeyMap.getJobKeyByKey(jobKey);
        if(jobKeyMap != null){
            scheduleJob.setStatus(0);
            if(scheduleJob.getScheduleJobId() == null){
                scheduleJob.setScheduleJobId(System.currentTimeMillis());
            }
            if(scheduleJob.getScheduleJobGroupId() == null){
                scheduleJob.setScheduleJobGroupId(scheduleJob.getScheduleJobId());
            }
            if(StringUtils.isBlank(scheduleJob.getScheduleJobName())){
                scheduleJob.setScheduleJobName("任务"+scheduleJob.getScheduleJobId());
            }
            scheduleJob.setScheduleJobClass(jobKeyMap.getScheduleJobClass());
            scheduleJob.setScheduleJobMethod(jobKeyMap.getScheduleJobMethod());
            scheduleJobService.createSchedule(scheduleJob);
            return "success";
        }
        return null;
    }

    @Override
    public String stopTaskJob(String jobKey, ScheduleJob scheduleJob) {
        //主动去暂停删除job
        SchedulerFactory.stop(scheduleJob);
        SchedulerFactory.unscheduleJob(scheduleJob);
        SchedulerFactory.del(scheduleJob);
        return "success";
    }
}
