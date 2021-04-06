package com.spring_stu.springAnnotations.组件注册.profile注解_07;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    @Test
    public void active_profile_type02(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //TODO 方式2：使用代码的形式指定运行环境： 可以同时激活多个环境
        applicationContext.getEnvironment().setActiveProfiles("test","dev");
        applicationContext.register(MainConfigOfProfile.class);
        applicationContext.refresh();


        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void active_profile_type01(){
        //TODO 方式1：使用环境变量指定运行环境：   -Dspring.profiles.active=test
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }



}
