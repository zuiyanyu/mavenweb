package com.DesignPattern.patterns.Intercepting_Filter_Pattern;

import com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.FilterManager;

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

    public void sendRequest(String request){
        //过滤器进行过滤后 进行请求的执行
        filterManager.filterRequest(request);

    }
}
