package com.spring_stu.spring_bean_lifeCycle.spring_PostProcessor.BeanFactoryPostProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:jdbcInfo.properties")
@ComponentScan("com.spring_stu.spring_bean_lifeCycle.spring_PostProcessor.BeanFactoryPostProcessor")
public class MainConfig {
    public MainConfig(){
        System.out.println("MainConfig 的构造方法被调用了...");
    }

    @PostConstruct
    public void initMethod(){
        System.out.println("MainConfig 的初始化方法");
    }

    @Bean
    public Bean01 bean01(){
        return  new Bean01();
    }
}
