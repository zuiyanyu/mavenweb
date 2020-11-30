package com.spring_stu.spring_bean_lifeCycle.spring_PostProcessor.BeanFactoryPostProcessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        //创建spring容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        //获取bean实例
        Bean01 bean01 = applicationContext.getBean("bean01", Bean01.class);
        System.out.println(bean01);

        //关闭容器
        applicationContext.close();

    }
}
