package com.hannea.model;

/**
 * Class
 *
 * @author wgm
 * @date 2018/06/10
 */
public enum JobKeyMap {

    ACTIVITY_START("ACTIVITY", "com.hannea.job.ActivityJob", "run"),
    ;

    JobKeyMap(String jobKey, String scheduleJobClass, String scheduleJobMethod){
        this.jobKey = jobKey;
        this.scheduleJobClass = scheduleJobClass;
        this.scheduleJobMethod = scheduleJobMethod;
    }

    public static JobKeyMap getJobKeyByKey(String jobKey){
        for(JobKeyMap map :JobKeyMap.values()){
            if(map.getJobKey().equalsIgnoreCase(jobKey)){
                return map;
            }
        }
        return null;
    }
    /**
     * jobKey
     */
    private String jobKey;

    /**
     * 任务类
     */
    private String scheduleJobClass;

    /**
     * 任务方法
     */
    private String scheduleJobMethod;

    public String getJobKey() {
        return jobKey;
    }

    public String getScheduleJobClass() {
        return scheduleJobClass;
    }

    public String getScheduleJobMethod() {
        return scheduleJobMethod;
    }
}
