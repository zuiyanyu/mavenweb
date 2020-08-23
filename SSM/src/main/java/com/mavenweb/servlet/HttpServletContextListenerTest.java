package com.mavenweb.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

// ServletContext是一个web应用的上下文，是一个全局信息的存储空间，代表当前web应用。
// 其在web应用（服务器）启动时创建，在Web应用（服务器）关闭时释放，即Application生命周期。
public class HttpServletContextListenerTest implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("ServletContextListener 测试");
        servletContext.setAttribute("mtf","matengfei");


        //获取web.xml 中的初始化参数：
//          <context-param>
//             <param-name>contextConfigLocation</param-name>
//             <param-value>classpath:applicationContext.xml</param-value>
//          </context-param>
        String keycloak = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation=============="+keycloak);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Properties pr = new Properties();
    }
}
