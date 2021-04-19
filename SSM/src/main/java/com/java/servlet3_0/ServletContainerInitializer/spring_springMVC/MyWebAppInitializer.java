package com.java.servlet3_0.ServletContainerInitializer.spring_springMVC;


/**
 * 使用注解的方式启动SpringMVC，而不是web.xml的配置方式
 */

public class MyWebAppInitializer //  extends AbstractAnnotationConfigDispatcherServletInitializer
{
//
//    //获取根容器的配置类：（Spring的配置文件） 父容器。
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{SpringRootConfig.class};
//    }
//
//    //获取web容器的配置类：（SpringMVC配置文件） 子容器
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{WebAppConfig.class};
//    }
//
//    //获取DispatcherServlet的映射信息
//    /**
//     *  / : 拦截所有的请求(包括静态资源(xx.js ,xx.png)),但是不包括 *.jsp
//     *  /* :拦截所有的请求,连 *.jsp页面都拦截
//     * @return
//     */
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
}
