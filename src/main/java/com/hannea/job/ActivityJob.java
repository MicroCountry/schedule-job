package com.hannea.job;

import com.alibaba.fastjson.JSONObject;
import com.hannea.model.ScheduleJob;

/**
 * Class
 *
 * @author wgm
 * @date 2018/08/16
 */
public class ActivityJob {

    public void run(ScheduleJob job){
        System.out.println(JSONObject.toJSONString(job));
    }

}
