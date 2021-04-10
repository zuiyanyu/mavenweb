package com.spring_stu.guo_ji_hua.Demo01;

import org.junit.Test;

import java.util.Locale;

public class LocaleTest  {

    @Test
    public void createLocal() {
        //1）带有语言和国家/地区信息的本地化对象
        Locale locale1 = new Locale("zh","CN");

        //2) 只有语言信息的本地化对象
        Locale locale2 = new Locale("zh");

        //3) 等同于Locale("zh","CN")
        Locale locale3 =   Locale.CHINA ;

        //4) 等同于Locale("zh")
        Locale locale4 =  Locale.CHINESE ;

        //5）获取本地系统默认的本地化对象
        Locale locale5 =   Locale.getDefault() ;


    }
}
