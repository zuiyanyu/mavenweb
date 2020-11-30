package com.spring_stu.spring_bean_lifeCycle.spring_PostProcessor.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    public MyBeanPostProcessor(){
        System.out.println("BeanPostProcessor 后处理bean的构造器。。。");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(!beanName.startsWith("org.springframework")){
            System.out.println("-------start--------------");
            System.out.println(beanName+"-> 初始化方法之前[postProcessBeforeInitialization]。。。");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!beanName.startsWith("org.springframework")) {
            System.out.println(beanName + "-> 初始化方法之后[postProcessAfterInitialization]。。。");
            System.out.println("-------end--------------");
        }
        return bean;
    }
}
