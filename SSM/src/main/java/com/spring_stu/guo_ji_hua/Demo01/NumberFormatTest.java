package com.spring_stu.guo_ji_hua.Demo01;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * NumberFormat 本地化工具类
 */
public class NumberFormatTest {
    /**
     *  TODO  MessageFormat在NumberFormat和DateFormat 的基础上提供了强大的占位符字符串的格式化功能。
     *  TODO 支持时间、货币、数字以及对象属性的格式化操作。
     */
    public static void main(String[] args) {
        //1) 信息格式化串，通过{n}占位符指定动态参数的替换位置索引
        // 这是简单形式的格式化信息串
        String pattern1 = "{0}，你好，你于{1}这天在我行存入{2}元" ;
        //格式化信息除了参数位置索引外，还指定了参数的类型和样式。详细参见JDK的Javadoc。
        String pattern2 = "At{1,time,short} On {1,date,long},{0} paid {2,number,currency}" ;

        //2) 用于动态替换占位符的参数
        //Object[] params = {"John",new GregorianCalendar().getTime(),1.0E3};
        Object[] params = {"John",new Date(),1.0E3};

        //3) 使用默认本地化对象格式化信息
        //由于是中文平台，系统默认使用本地化对象： Locale.CHINA
        String msg1 = MessageFormat.format(pattern1,params) ;
        System.out.println(msg1);

        //4）使用指定的本地化对象格式化信息
        //由于是中文平台，系统默认使用本地化对象： Locale.CHINA ，但是我们可以手动指定本地化对象。
        MessageFormat messageFormat = new MessageFormat(pattern2, Locale.US);
        messageFormat = new MessageFormat(pattern2);
        String msg2 = messageFormat.format(params);
        System.out.println(msg2);
    }



    //格式化日期
    public static void main2(String[] args) {
        Locale locale = new Locale("zh","CN");
        /*
        第一个入参：指定的日期被格式化的样式
            DateFormat.LONG 2020年12月9日
            DateFormat.MEDIUM  2020-12-9
            DateFormat.SHORT    20-12-9
        第二个参数：指定的本地化对象
         */
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM,locale);

        Date date = new Date();
        System.out.println(dateInstance.format(date));
    }
    // 格式化金额
    public static void main1(String[] args) {
        Locale locale = new Locale("zh","CN"); // ￥123,456.79
        //locale = new Locale("en","US");  // $123,456.79

        //Currency :货币  获取对货币进行格式化的实例
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        double amt = 123456.789 ;
        System.out.println(numberFormat.format(amt));


    }
}
