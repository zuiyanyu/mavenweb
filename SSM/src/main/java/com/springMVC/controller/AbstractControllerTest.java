package com.springMVC.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AbstractControllerTest extends AbstractController implements LastModified {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("缓存测试方法被调用了");
        //点击后再次请求当前页面
        response.getWriter().write("<a href=''>this</a>");
        return null;
    }

    //Spring也提供了Last-Modified机制的支持，只需要实现LastModified接口
    //HelloWorldLastModifiedCacheController只需要实现LastModified接口的getLastModified方法，保证当内容发生
    //改变时返回最新的修改时间即可。
    private long lastModified;
    @Override
    public long getLastModified(HttpServletRequest request) {
        if(lastModified == 0L) {
            //TODO 此处更新的条件：如果内容有更新，应该重新返回内容最新修改的时间戳
            lastModified = System.currentTimeMillis();
        }
        return lastModified;
    }
}
