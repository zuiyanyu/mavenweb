package com.DesignPattern.patterns.Business_Delegate_Pattern;

import com.DesignPattern.patterns.Business_Delegate_Pattern.service.BusinessService;

//创建业务代表。
public class BusinessDelegate {
    private BusinessLookUp lookupService = new BusinessLookUp();
    private BusinessService businessService;
    private String serviceType;

    public void setServiceType(String serviceType){
        this.serviceType = serviceType;
    }

    public void doTask(){
        businessService = lookupService.getBusinessService(serviceType);
        businessService.doProcessing();
    }
}