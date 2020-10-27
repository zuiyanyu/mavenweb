package com.DesignPattern.patterns.Business_Delegate_Pattern;

import com.DesignPattern.patterns.Business_Delegate_Pattern.service.BusinessService;
import com.DesignPattern.patterns.Business_Delegate_Pattern.service.impl.EJBService;
import com.DesignPattern.patterns.Business_Delegate_Pattern.service.impl.JMSService;

//业务查询服务。
public class BusinessLookUp {
    public BusinessService getBusinessService(String serviceType){
        if(serviceType.equalsIgnoreCase("EJB")){
            return new EJBService();
        }else {
            return new JMSService();
        }
    }
}
