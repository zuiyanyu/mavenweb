package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.beans.factory.annotation.Value;

public class BeanObject {

   private String beanName ;

    public String getBeanName() {
        return beanName;
    }

    @Value("BeanObjectName")
    public void setBeanName(String beanName) {
        System.out.println("属性注入：Person.BeanObject() 被调用了...");
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "BeanObject{" +
                "beanName='" + beanName + '\'' +
                '}';
    }
}
