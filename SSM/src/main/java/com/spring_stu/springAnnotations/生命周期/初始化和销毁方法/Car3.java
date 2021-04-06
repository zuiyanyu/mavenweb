package com.spring_stu.springAnnotations.生命周期.初始化和销毁方法;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car3 implements InitializingBean, DisposableBean {
    public Car3(){
        System.out.println("car3 constructor...");
    }
//    //bean实例创建完成之后执行
//    @PostConstruct
//    public void init(){
//        System.out.println("car3 ... init...");
//    }
//    //bean实例销毁之前执行
//    @PreDestroy
//    public void detory(){
//        System.out.println("car3 ... detory...");
//    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean 的销毁方法被执行了...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean 的afterPropertiesSet方法被执行了...");
    }
}
