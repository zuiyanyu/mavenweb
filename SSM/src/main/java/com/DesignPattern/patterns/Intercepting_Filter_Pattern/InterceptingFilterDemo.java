package com.DesignPattern.patterns.Intercepting_Filter_Pattern;

import com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.FilterManager;
import com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.impl.AuthenticationFilter;
import com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.impl.DebugFilter;

public class InterceptingFilterDemo {
    public static void main(String[] args) {
        //配置
        FilterManager filterManager = new FilterManager(new Target());
        filterManager.setFilter(new AuthenticationFilter());
        filterManager.setFilter(new DebugFilter());

        //调用
        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("HOME");
    }
}