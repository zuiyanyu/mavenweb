package com.spring_stu.spring_aop.d_aspect.c_Annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.spring_stu.spring_aop.d_aspect.c_Annotation")
public class AspectConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AspectConfig.class);
        // 必须是接口类型,否则抛类型转换异常 .基于接口生成了代理类，所以必须 使用Waiter接口接收bean对象
        Waiter naiveWaiter = (Waiter)applicationContext.getBean("naiveWaiter");
        naiveWaiter.greetTo("张三");
    }
}
