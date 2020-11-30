package com.spring_stu.spring_bean_lifeCycle.spring_PostProcessor.BeanFactoryPostProcessor;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Bean01 {
    @Value("${jdbc.driverClass}")
    private String name ;

    public Bean01(){
        System.out.println("Bean01 的无参构造器...");
    }
    //初始化方法
    @PostConstruct
    public void initMethod(){
        System.out.println("Bean01的初始化 initMethod() ... ");
    }
    //销毁方法
    @PreDestroy
    public void destoryMethod(){
        System.out.println("Bean01的销毁方法 destoryMethod() ...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean01{" +
                "name='" + name + '\'' +
                '}';
    }
}
