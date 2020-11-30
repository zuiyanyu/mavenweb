package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.spring_stu.spring_bean_lifeCycle.beanLifeCycle")
public class BeanConfiguration {
    public BeanConfiguration(){
        System.out.println("@Configuration标注的配置类的构造器被执行了...");
    }

    @Bean
    public Person person(){
        return new Person();
    }
}
