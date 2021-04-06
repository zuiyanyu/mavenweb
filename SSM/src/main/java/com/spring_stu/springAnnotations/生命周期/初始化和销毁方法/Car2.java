package com.spring_stu.springAnnotations.生命周期.初始化和销毁方法;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class Car2 implements ApplicationContextAware {
    public Car2(){
        System.out.println("car2 constructor...");
    }

    //bean实例创建完成之后执行
    @PostConstruct
    public void init(){
        System.out.println("car2 ... init...");
    }
    //bean实例销毁之前执行
    @PreDestroy
    public void detory(){
        System.out.println("car2 ... detory...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
