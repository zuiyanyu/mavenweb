package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Person {
    @Value("lisi")
    private String userName ="zhangsan" ;
    private Integer age = 20 ;
    private BeanObject beanObject ;

    public BeanObject getBeanObject() {
        return beanObject;
    }

    //属性注入
    @Autowired
    public void setBeanObject(BeanObject beanObject) {
        System.out.println("属性注入：Person.setBeanObject() 被调用了...");
        this.beanObject = beanObject;
    }

    public void setUserName(String userName) {
        System.out.println("属性注入：Person.setUserName()被调用了...");
        this.userName = userName;
    }

    @Value("23")
    public void setAge(Integer age) {
        System.out.println("属性注入：Person.getAge()被调用了...");
        this.age = age;
    }
    public String getUserName() {
        return userName;
    }
    public Integer getAge() {

        return age;
    }
    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", beanObject=" + beanObject +
                '}';
    }

}
