package com.spring_stu.springAnnotations.annotation_import.ImportBeanDefinitionRegistrar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ImportBeanDefinitionRegistrarImpl_Test {
    public static void main(String[] args) {
        //1. 通过扫描注解 来获取spring的上下文环境
        String xmlPath ="classpath:com/spring_stu/springAnnotations/applicationContext.xml" ;
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath) ;

        //2.获取IOC 容器中所有注入的bean 的 id名，并进行输出
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
