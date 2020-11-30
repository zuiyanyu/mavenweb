package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanObject  {
   private String beanName ;

    public BeanObject(){
        System.out.println("BeanObject的构造器被调用了...");
    }
    @Value("BeanObjectName")
    public void setBeanName(String beanName) {
        System.out.println("属性注入：Person.BeanObject() 被调用了...");
        this.beanName = beanName;
    }
    public String getBeanName() {
        return beanName;
    }

    @Override
    public String toString() {
        return "BeanObject{" +
                "beanName='" + beanName + '\'' +
                '}';
    }
}
