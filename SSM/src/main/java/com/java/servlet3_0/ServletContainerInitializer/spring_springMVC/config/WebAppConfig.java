package com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//springMVC只扫描 @Controller注解的类
@Configuration
@EnableWebMvc  //包含 <mvc:annotation-driven/> 等功能的开启
@ComponentScan(basePackages="com.java.servlet3_0.ServletContainerInitializer.spring_springMVC"
,includeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,value = {Controller.class})}
,useDefaultFilters = false)
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.freeMarker();
    }

    // Configure FreeMarker...

//    @Bean
//    public FreeMarkerConfigurer freeMarkerConfigurer() {
//        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        configurer.setTemplateLoaderPath("/WEB-INF/freemarker");
//        return configurer;
//    }
}
