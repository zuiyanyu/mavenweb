package com.spring_stu.springAnnotations.annotation_import.ImportSelector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportSelector_Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportSelector_Main.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
