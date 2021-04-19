package com.java.servlet3_0.servlet3注解.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Enumeration;

/**
 *
 *
 * TODO @WebListener(value) 监听注解
 *      value:对监听器的一些描述信息
 * 1）这个注解用于向 web容器(ServletContext)注册web的监听器.
 * 2）被这个注解标注的类，必须实现一个或者多个以下的接口：
 * javax.servlet.ServletContextListener
 * javax.servlet.ServletContextAttributeListener
 * javax.servlet.ServletRequestListener
 * javax.servlet.ServletRequestAttributeListener
 * javax.servlet.http.HttpSessionListener
 * javax.servlet.http.HttpSessionAttributeListener
 * javax.servlet.http.HttpSessionIdListener
 *
 * 等价：web.xml中的配置：
 *     <listener>
 *         <display-name>ServletContextListener_test</display-name>
 *         <listener-class>com.java.servlet3_0.servlet3注解.listener.ServletContextListener_01</listener-class>
 *     </listener>
 */
//TODO ServletContextListener 监听 ServletContext(web容器)的创建和销毁
@WebListener("ServletContextListener_test")
public class ServletContextListener_01 implements ServletContextListener {
    //监听web容器的创建
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听ServletContext 的初始化...");

        //这里可以获取servletContext容器，可以进行一些 其他监听器或者servlet的注册
        ServletContext servletContext = sce.getServletContext();


        Enumeration<String> attributeNames = servletContext.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String parmName = attributeNames.nextElement();
            Object parmvalue = servletContext.getAttribute(parmName);
            if(!"org.apache.tomcat.util.scan.MergedWebXml".equals(parmName)){
                System.out.println("ServletContextListener.contextInitialized :" + parmName+ "  =  "+parmvalue);
            }
        }
    }

    //监听web容器的销毁事件
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听ServletContext 的销毁...");
    }
}
