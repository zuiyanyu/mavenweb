package com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//spring 不扫描@Controller注解的类
@Configuration
@ComponentScan(basePackages="com.java.servlet3_0.ServletContainerInitializer.spring_springMVC"
        ,excludeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,value = {Controller.class})})
public class SpringRootConfig {
}
