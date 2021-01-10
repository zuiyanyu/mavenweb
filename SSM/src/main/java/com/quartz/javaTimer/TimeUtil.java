package com.quartz.javaTimer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String getDate(Long scheduleTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm==ss:SS");
        return simpleDateFormat.format(scheduleTime);
    }
    public static String getDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm==ss:SS");
        return simpleDateFormat.format(date.getTime());
    }
}
