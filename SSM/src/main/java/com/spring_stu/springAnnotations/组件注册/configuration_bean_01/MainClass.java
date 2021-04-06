package com.spring_stu.springAnnotations.组件注册.configuration_bean_01;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        //加载配置类
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanConfigerClass.class);

        System.out.println("IOC容器注册完成...");

        //获取被注册的Bean实例
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

//        Object person = applicationContext.getBean("person");
//        System.out.println(person);

    }
}
