package com.hannea.service;

import com.hannea.model.ScheduleJob;

/**
 * Class
 *
 * @author wgm
 * @date 2018/08/16
 */
public interface TaskService {

    String createTaskJob(String jobKey, ScheduleJob scheduleJob);

    String stopTaskJob(String jobKey, ScheduleJob scheduleJob);
}

