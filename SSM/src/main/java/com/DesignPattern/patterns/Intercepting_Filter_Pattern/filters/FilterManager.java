package com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters;

import com.DesignPattern.patterns.Intercepting_Filter_Pattern.Target;

public class FilterManager {
    FilterChain filterChain;

    /**
     * 构造器 -初始化过滤器链 和 目标类
     * @param target
     */
    public FilterManager(Target target){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }
    public void setFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }
}