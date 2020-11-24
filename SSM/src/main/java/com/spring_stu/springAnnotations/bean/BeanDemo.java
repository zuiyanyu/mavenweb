package com.spring_stu.springAnnotations.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Bean_Main.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        Object annotationVO = annotationConfigApplicationContext.getBean("annotationVO");
        System.out.println(annotationVO);

    }
}
