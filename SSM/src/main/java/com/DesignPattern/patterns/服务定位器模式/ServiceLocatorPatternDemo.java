package com.DesignPattern.patterns.服务定位器模式;

import com.DesignPattern.patterns.服务定位器模式.service.Service;

//使用 ServiceLocator 来演示服务定位器设计模式
public class ServiceLocatorPatternDemo {
    public static void main(String[] args) {
        Service service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();

        service = ServiceLocator.getService("Service1");
        service.execute();

        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}