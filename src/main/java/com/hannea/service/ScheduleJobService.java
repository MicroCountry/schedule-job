package com.hannea.service;


import com.hannea.model.ScheduleJob;

/**
 * Class
 *
 * @author wgm
 * @date 2018/06/10
 */
public interface ScheduleJobService {
    /**
     * 添加定时器
     *
     * @param scheduleJob
     */
    public ScheduleJob createSchedule(ScheduleJob scheduleJob);

    /**
     * 启动定时器
     *
     * @param scheduleJob
     */
    public void thaw(ScheduleJob scheduleJob);

    /**
     * 重启定时器
     *
     * @param scheduleJob
     */
    public void restart(ScheduleJob scheduleJob);

    /**
     * 删除
     * @param scheduleJob
     */
    void delete(ScheduleJob scheduleJob);

    /**
     * 停止
     * @param scheduleJob
     */
    void stop(ScheduleJob scheduleJob);

    /**
     * 移除
     * @param scheduleJob
     */
    void unscheduleJob(ScheduleJob scheduleJob);
}
