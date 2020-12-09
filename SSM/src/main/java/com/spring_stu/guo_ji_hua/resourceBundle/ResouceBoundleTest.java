package com.spring_stu.guo_ji_hua.resourceBundle;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResouceBoundleTest {
    public static void main(String[] args) {
        //资源路径/资源名
        String resourcePath = "com/spring_stu/guo_ji_hua/i18n/fmg_resource";
        Object[] parms = {"张三",new Date()};
        //中文国际化本地化
        ResourceBundle zhBundle = ResourceBundle.getBundle(resourcePath, Locale.CHINA);
        String CN_greeting_common = zhBundle.getString("greeting.common");
        MessageFormat CN_messageFormat = new MessageFormat(CN_greeting_common, Locale.CHINA);
        String CN_greeting_common_format = CN_messageFormat.format(parms);
        System.out.println(CN_greeting_common_format);

        //英文国际化本地化
        ResourceBundle enBundle = ResourceBundle.getBundle(resourcePath, Locale.US);
        String US_greeting_common = enBundle.getString("greeting.common");
        MessageFormat US_messageFormat = new MessageFormat(US_greeting_common, Locale.US);
        String US_greeting_common_format =US_messageFormat.format(parms);
        System.out.println(US_greeting_common_format);
    }
    public static void main1(String[] args) {
        //资源路径/资源名
        String resourcePath = "com/spring_stu/guo_ji_hua/i18n/resource";

        //中文国际化本地化
        Locale zhLocale = new Locale("zh","CN");
        ResourceBundle zhBundle = ResourceBundle.getBundle(resourcePath, zhLocale);
        System.out.println("cn:"+zhBundle.getString("greeting.common"));

        //英文国际化本地化
        Locale enLocale = new Locale("en","US");
        ResourceBundle enBundle = ResourceBundle.getBundle(resourcePath, enLocale);
        System.out.println("us:"+enBundle.getString("greeting.common"));


        //如果不指定本地化对象，将使用本地系统默认的本地化对象 ，这里为中文
        ResourceBundle defaultBundle = ResourceBundle.getBundle(resourcePath);
        System.out.println("default:"+defaultBundle.getString("greeting.common"));

    }
}
