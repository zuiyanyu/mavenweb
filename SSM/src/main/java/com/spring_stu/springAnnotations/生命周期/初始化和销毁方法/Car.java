package com.spring_stu.springAnnotations.生命周期.初始化和销毁方法;


public class Car {
    public Car(){
        System.out.println("car constructor...");
    }
    public void init(){
        System.out.println("car ... init...");
    }

    public void detory(){
        System.out.println("car ... detory...");
    }
}
