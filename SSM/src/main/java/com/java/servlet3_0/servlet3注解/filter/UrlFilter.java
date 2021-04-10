package com.java.servlet3_0.servlet3注解.filter;

import javax.servlet.*;
import java.io.IOException;

public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("拦截器。。。");
        //放行
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
