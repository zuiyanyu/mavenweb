package com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.impl;

import com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters.Filter;

/**
 * 登录认证过滤器
 */
public class AuthenticationFilter implements Filter {
    public void execute(String request){
        System.out.println("Authenticating request: " + request);
    }
}
