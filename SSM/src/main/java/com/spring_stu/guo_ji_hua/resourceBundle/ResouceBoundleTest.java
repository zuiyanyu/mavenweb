package com.spring_stu.guo_ji_hua.resourceBundle;

import org.junit.Test;

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
//        getFmgResourceMessage("greeting.common",parms,Locale.CHINA);
        getFmgResourceMessage("greeting.morning",parms,Locale.CHINA);
//        getFmgResourceMessage("greeting.affternoon",parms,Locale.CHINA);

        //英文国际化本地化
//        getFmgResourceMessage("greeting.common",parms,Locale.US);
//        getFmgResourceMessage("greeting.morning",parms,Locale.US);
//        getFmgResourceMessage("greeting.affternoon",parms,Locale.US);

    }
    private static String getFmgResourceMessage(String code,Object[] parms , Locale locale){
        //资源路径/资源名
        String resourcePath = "com/spring_stu/guo_ji_hua/i18n/fmg_resource";

        //获取属性值对应的内容
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resourcePath, locale);
        String msg = resourceBundle.getString(code);

        //对获取到的属性值进行本地化格式化
        MessageFormat messageFormat = new MessageFormat(msg, locale);
        String formatedMessage = messageFormat.format(parms);
        System.out.println(locale.getDisplayName()+"=>"+formatedMessage);
        return formatedMessage;
     }
     @Test
     public  void main1( ) {
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
    // 使用resourceBundle进行解析 properties
    @Test
    public void main0(){
        String baseName = "applicationContext" ;
        //资源路径/资源名
        String resourcePath = "com/spring_stu/guo_ji_hua/i18n/"+baseName;

        //进行资源绑定
        //TODO 底层
        //  stream = classLoader.getResourceAsStream(resourceName);
        //  bundle = new PropertyResourceBundle(stream);
        ResourceBundle resouce = ResourceBundle.getBundle(resourcePath);
        //开始或取某个key - value
        String userName = resouce.getString("user.name");

        System.out.println("userName = "+userName);

    }
}
