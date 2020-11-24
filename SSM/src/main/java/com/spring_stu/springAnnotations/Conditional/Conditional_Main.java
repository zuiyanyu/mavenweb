package com.spring_stu.springAnnotations.Conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Conditional_Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

        ListService listService = applicationContext.getBean(ListService.class);

        System.out.println(
                applicationContext.getEnvironment().getProperty("os.name") +
                        "系统下的列表命令为：" + listService.showListCmd());
    }
}
