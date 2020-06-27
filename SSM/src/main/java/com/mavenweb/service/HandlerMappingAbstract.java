package com.mavenweb.service;

public abstract class HandlerMappingAbstract implements HandlerMappingInterface{

    @Override
    public String  getHandler() throws Exception{
         return  PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE ;
    }

}
