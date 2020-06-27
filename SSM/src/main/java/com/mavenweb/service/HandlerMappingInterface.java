package com.mavenweb.service;

public interface HandlerMappingInterface {
    String PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE = HandlerMappingInterface.class.getName() + ".pathWithinHandlerMapping";
    String getHandler() throws Exception;

}
