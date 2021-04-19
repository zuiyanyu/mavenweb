package com.java.servlet3_0.ServletContainerInitializer;

import com.java.servlet3_0.servlet3注解.filter.UrlFilter;
import com.java.servlet3_0.servlet3注解.listener.RequestListener;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * Shared libraries（共享库） / runtimes pluggability（运行时插件能力）
 *
 * TODO 1.为了在web容器启动时给第三方组件提供机会做一些初始化的工作，例如注册servlet或者filtes等，servlet规范中通过ServletContainerInitializer实现此功能。
 *      1、Servlet容器启动会扫描，当前应用里面每一个jar包的
 *      	ServletContainerInitializer的实现
 *
 * TODO 2. 每个框架要使用ServletContainerInitializer就必须在对应的jar包的
 *         META-INF/services 目录创建一个名为 javax.servlet.ServletContainerInitializer的文件，文件内容指定具体的ServletContainerInitializer实现类，
 *         那么，当web容器启动时就会运行这个初始化器做一些组件内的初始化工作。
 *      2、提供ServletContainerInitializer的实现类；
 *     	必须绑定在，META-INF/services/javax.servlet.ServletContainerInitializer
 *     	文件的内容就是ServletContainerInitializer实现类的全类名；
 *
 * TODO 3.一般伴随着ServletContainerInitializer一起使用的还有HandlesTypes注解，
 *        通过HandlesTypes可以将感兴趣的一些类注入到ServletContainerInitializerde的onStartup方法作为参数传入。
 *
 *总结：容器在启动应用的时候，会扫描当前应用每一个jar包里面的
 * META-INF/services/javax.servlet.ServletContainerInitializer
 * 文件中指定的实现类，启动并运行这个实现类的方法；传入感兴趣的类型；
 *
 * ServletContainerInitializer；
 * @HandlesTypes；
 */

//容器启动的时候会将制定的这个类型下面的子类(实现类，子接口)全都传递进来,但是本身不会传递进来。
@HandlesTypes(value = {Filter.class,Servlet.class})
public class CustomServletContainerInitializer implements ServletContainerInitializer {
    /**
     * 应用启动的时候，会运行onStartup方法
     *
     * @param set 感兴趣的类型的所有子类(实现类，子接口)全都传递进来,但是本身不会传递进来。
     * @param servletContext    代表当前web应用的servletContext，一个web应用就这一个
     * @throws ServletException
     *
     * 1）、使用ServletContext注册Web组件（Servlet、Filter、Listener）
     * 2）、使用编码的方式，在项目启动的时候给ServletContext里面添加组件；
     * 		必须在项目启动的时候来添加；
     * 		1）、ServletContainerInitializer得到的ServletContext；
     * 		2）、ServletContextListener得到的ServletContext；
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("感兴趣的类...");
        if(null ==set){
            System.out.println("没有发现感兴趣的类");
        }else{
            for (Class<?> aClass : set) {
                System.out.println(aClass.getName());
            }
        }

        System.out.println(" 使用ServletContext 进行一些注册服务...");
        //TODO 注册组件
        //注册servlet
//        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
//        userServlet.addMapping("/userServlet");

        //注册监听器
        servletContext.addListener(RequestListener.class);

        //注册filter
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("urlFilter", UrlFilter.class);
        EnumSet<DispatcherType> dispatcherType = EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD);
        userFilter.addMappingForServletNames(dispatcherType,true,"/*");
        //userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

    }
}
