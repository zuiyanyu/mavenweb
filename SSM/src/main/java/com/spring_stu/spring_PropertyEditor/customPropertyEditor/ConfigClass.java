package com.spring_stu.spring_PropertyEditor.customPropertyEditor;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.spring_stu.spring_PropertyEditor.customPropertyEditor")
public class ConfigClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerCustomEditor(Car.class,CustomCarEditor.class);

        applicationContext.register(ConfigClass.class);
        applicationContext.refresh();

        Boss boss = applicationContext.getBean("boss", Boss.class);
        System.out.println(boss);
        applicationContext.close();
    }
}
