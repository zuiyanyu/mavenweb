package com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters;

/**
 * 过滤器（Filter） - 过滤器在请求处理程序执行请求之前或之后，执行某些任务。
 */
public interface Filter {
    public  void execute(String request);
}
