package com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.impl;

import com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.Filter;

/**
 * 日志记录
 */
public class DebugFilter implements Filter {
    public void execute(String request){
        System.out.println("request log: " + request);
    }
}