package com.spring_stu.spring_property_placeholder.DES;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.spring_stu.spring_property_placeholder.DES")
@PropertySource(value = "classpath:jdbcInfo.properties" ,encoding = "UTF-8")
public class ConfigClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigClass.class);
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
    }
}
