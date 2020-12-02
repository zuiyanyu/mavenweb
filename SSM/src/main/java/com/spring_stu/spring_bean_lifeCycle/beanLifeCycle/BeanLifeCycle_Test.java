package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycle_Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        System.out.println("spring 的容器被创建完成...");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        System.out.println("spring 的容器将被销毁...");
        applicationContext.close();
        System.out.println("spring 的容器销毁成功！");

    }
}
