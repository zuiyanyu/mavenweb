package com.spring_stu.guo_ji_hua.ResourceBundleMessageSourceTest;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Date;
import java.util.Locale;

public class AppClient {
    @Test
    public void applicationContext() throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigClass.class);
        //获取MessageSource的Bean
        ReloadableResourceBundleMessageSource resourceBundleMessageSource =
                applicationContext.getBean("reloadableMessageSource", ReloadableResourceBundleMessageSource.class);
        //获取格式化消息的参数列表
        Object[] params = {"张三", new Date()};

        for (int i = 0; i <3 ; i++) {
            //获取格式化的国际化信息 pattern
            String message1 = resourceBundleMessageSource.getMessage("greeting.common", params, Locale.CHINA);
            String message2 = resourceBundleMessageSource.getMessage("greeting.morning", params, Locale.CHINA);
            String message3 = resourceBundleMessageSource.getMessage("greeting.affternoon", params, Locale.CHINA);
            System.out.println("message1=>"+message1);
            System.out.println("message2=>"+message2);
            System.out.println("message3=>"+message3);
            //模拟程序应用，在此期间，我们更改资源文件
            Thread.currentThread().sleep(5000L);
        }
    }

    @Test
    public void reloadableResourceBundleMessageSourceTest() throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigClass.class);
        ResourceBundleMessageSource resource = applicationContext.getBean("messageSource", ResourceBundleMessageSource.class);
        //获取格式化消息的参数列表
        Object[] params = {"张三", new Date()};

        //TODO ResourceBundleMessageSource 实现了MessageSource接口，
        // 那么必然实现了getMessage()，这里会使用容器级的MessageResource
        //TODO 名称为"messageSource"且类型为org.springframework.context.MessageSource的Bean 即为容器级的MessageResource
        //使用容器级的MessageSource来获取格式化的国际化信息 pattern
        /**
         * 底层原理：
         *TODO 1. 根据basename获取ResourceBundle对象
         * ResourceBundle bundle = ResourceBundle.getBundle(basename, locale, classLoader, control)
         *
         *TODO 2.根据bundle + 属性名 获取 MessageFormat
         * MessageFormat messageFormat = getMessageFormat(bundle, code, locale);
         *      //先根据bundle 获取code对应的msg （code 为 属性名，比如"greeting.common"）
         *      String msg = getStringOrNull(bundle, code);
         *      //再根据 msg + local 获取messageFormat
         *      MessageFormat messageFormat =  new MessageFormat(msg, locale);
         *
         * TODo 3. 根据messageFormat + 格式化的参数，进行消息格式化。
         * 最终结果 = messageFormat.format(argsToUse)
         */
        String message1 = resource.getMessage("greeting.common", params, Locale.CHINA);
        String message2 = resource.getMessage("greeting.morning", params, Locale.CHINA);
        String message3 = resource.getMessage("greeting.affternoon", params, Locale.CHINA);
        System.out.println("message1=>"+message1);
        System.out.println("message2=>"+message2);
        System.out.println("message3=>"+message3);

    }
    //启动Spring容器，并通过MessageSource访问配置的国际化资源
    @Test
   public void resourceBundleMessageSourceTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigClass.class);
        //获取MessageSource的Bean
        ResourceBundleMessageSource resourceBundleMessageSource =
                applicationContext.getBean("messageSource", ResourceBundleMessageSource.class);
        //获取格式化消息的参数列表
        Object[] params = {"张三", new Date()};

        //获取格式化的国际化信息 pattern
        String message1 = resourceBundleMessageSource.getMessage("greeting.common", params, Locale.CHINA);
        String message2 = resourceBundleMessageSource.getMessage("greeting.morning", params, Locale.CHINA);
        String message3 = resourceBundleMessageSource.getMessage("greeting.affternoon", params, Locale.CHINA);
        System.out.println("message1=>"+message1);
        System.out.println("message2=>"+message2);
        System.out.println("message3=>"+message3);
    }
}






















