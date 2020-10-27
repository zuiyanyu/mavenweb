package com.DesignPattern.patterns.Business_Delegate_Pattern.service.impl;

import com.DesignPattern.patterns.Business_Delegate_Pattern.service.BusinessService;

//创建实体服务类。
public class EJBService implements BusinessService {

    @Override
    public void doProcessing() {
        System.out.println("Processing task by invoking EJB Service");
    }
}