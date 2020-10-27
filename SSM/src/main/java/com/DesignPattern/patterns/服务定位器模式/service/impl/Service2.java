package com.DesignPattern.patterns.服务定位器模式.service.impl;

import com.DesignPattern.patterns.服务定位器模式.service.Service;

public class Service2 implements Service {
    public void execute(){
        System.out.println("Executing Service2");
    }

    @Override
    public String getName() {
        return "Service2";
    }
}
