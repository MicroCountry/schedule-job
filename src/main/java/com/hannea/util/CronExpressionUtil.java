package com.hannea.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Class
 *
 * @author wgm
 * @date 2018/06/11
 */
public class CronExpressionUtil {


    public static String getCronExpressionByTime(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.getTimeInMillis() - System.currentTimeMillis() < 3*60*1000){
            calendar.setTime(new Date());
        }
        //进行主动延时
        calendar.add(Calendar.MINUTE, 1);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
        //增加3分钟
        Integer minute = calendar.get(Calendar.MINUTE);
        Integer second = calendar.get(Calendar.SECOND);
        String cor = second + " " + minute +" "+ hour + " " + day +" " + month+" ? "+year;
        return cor;
    }
}
