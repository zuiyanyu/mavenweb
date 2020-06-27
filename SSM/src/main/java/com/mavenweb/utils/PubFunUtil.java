package com.mavenweb.utils;

import java.util.UUID;

public class PubFunUtil {
    /**
     * 获取UUID 字符串
     * @param move_
     * @return
     */
    public static String getStrUUID(boolean move_){
        if(move_){
            return UUID.randomUUID().toString().replaceAll("-","");
        }
        return UUID.randomUUID().toString();
    }

    /**
     * 获取UUID 字符串
     * @return
     */
    public static String getStrUUID(){
        return getStrUUID(true);
    }

}
