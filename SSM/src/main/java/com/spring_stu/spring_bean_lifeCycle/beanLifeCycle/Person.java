package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person implements BeanNameAware,BeanFactoryAware,InitializingBean,DisposableBean {
    @Value("lisi")
    private String userName ="zhangsan" ;
    private Integer age = 20 ;
    private BeanObject beanObject ;

    public BeanObject getBeanObject() {
        System.out.println("Person的构造器被调用了...");

        return beanObject;
    }
    //BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware.setBeanName()方法被调用了...name="+name);
    }
    //BeanFactoryAware
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware.setBeanFactory()方法被调用了...");

    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet()方法被调用了...");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy()方法被调用了...");
    }

    @PostConstruct
    public void init_method(){
        System.out.println("@PostConstruct注解的Person.init_method()方法被调用了...");
    }
    @PreDestroy
    public void destroy_method(){
        System.out.println("@PreDestroy注解的Person.destroy_method()方法被调用了...");
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
