package com.spring_stu.spring_property_placeholder.sysConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigClass.class);
        System.out.println("容器初始化完成!");
        SessionClass sessionClass = applicationContext.getBean("sessionClass", SessionClass.class);
        System.out.println(sessionClass);
        applicationContext.close();
     }
}
