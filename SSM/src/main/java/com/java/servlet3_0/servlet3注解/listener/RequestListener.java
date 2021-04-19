package com.java.servlet3_0.servlet3注解.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        System.out.println(" 收到一个servletRequest 请求 ： "+servletRequest.getClass().getName());
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest ;
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
       // System.out.println("ServletRequestEvent初始化完成...");
    }
}
