package com.mavenweb.utils;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 将日期格式化为指定的pattern格式
     * @param date     要被格式化的日期
     * @param pattern  日期将被格式化的格式 比如："yyyy-MM-dd HH:mm:ss" ,"yyyy-MM-dd"
     * @return
     */
    public static String parseDateToStr(Date date,String pattern){
        if(StringUtils.isEmpty(pattern)) pattern ="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return  simpleDateFormat.format(date);
    }

}
