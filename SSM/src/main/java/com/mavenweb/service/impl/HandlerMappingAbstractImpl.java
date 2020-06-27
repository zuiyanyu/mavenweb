package com.mavenweb.service.impl;

import com.mavenweb.service.HandlerMappingAbstract;

public class HandlerMappingAbstractImpl extends HandlerMappingAbstract {

     public String  getHandler() throws Exception{
        return  PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE ;
    }

    public static void main(String[] args) {
        HandlerMappingAbstractImpl handlerMappingAbstract = new HandlerMappingAbstractImpl();
        try {

            String handler = handlerMappingAbstract.getHandler();
            System.out.println("handler = "+handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
