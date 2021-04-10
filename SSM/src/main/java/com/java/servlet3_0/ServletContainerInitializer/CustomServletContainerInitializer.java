package com.java.servlet3_0.ServletContainerInitializer;

import com.java.servlet3_0.servlet3注解.service.UserService;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * TODO 1.为了在web容器启动时给第三方组件提供机会做一些初始化的工作，例如注册servlet或者filtes等，servlet规范中通过ServletContainerInitializer实现此功能。
 *
 * TODO 2. 每个框架要使用ServletContainerInitializer就必须在对应的jar包的
 *         META-INF/services 目录创建一个名为 javax.servlet.ServletContainerInitializer的文件，文件内容指定具体的ServletContainerInitializer实现类，
 *         那么，当web容器启动时就会运行这个初始化器做一些组件内的初始化工作。
 *
 * TODO 3.一般伴随着ServletContainerInitializer一起使用的还有HandlesTypes注解，
 *        通过HandlesTypes可以将感兴趣的一些类注入到ServletContainerInitializerde的onStartup方法作为参数传入。
 *
 */

//容器启动的时候会将制定的这个类型下面的子类(实现类，子接口)全都传递进来,但是本身不会传递进来。
@HandlesTypes(value = {UserService.class})
public class CustomServletContainerInitializer implements ServletContainerInitializer {
    /**
     * 应用启动的时候，会运行onStartup方法
     *
     * @param set
     * @param servletContext    代表当前web应用的servletContext，一个web应用就这一个
     * @throws ServletException
     *
     * 1。注册实现三大组件 Servlet Filter Listener
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
        //注册组件
        //ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
        //userServlet.addMapping("/user");
        //servletContext.addListener(UserListener.class);
        //FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        //userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

    }
}
