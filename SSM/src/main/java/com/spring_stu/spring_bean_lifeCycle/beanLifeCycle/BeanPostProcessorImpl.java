package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * bean实例的后处理bean
 */
@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {
    public BeanPostProcessorImpl(){
        System.out.println("BeanPostProcessorImpl的构造方法被调用了...");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(!beanName.startsWith("org.springframework")){
            System.out.println("BeanPostProcessorImpl.postProcessBeforeInitialization()方法被执行了..."+beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!beanName.startsWith("org.springframework")) {
            System.out.println("BeanPostProcessorImpl.postProcessAfterInitialization()方法被执行了..."+beanName);
        }
        return bean;
    }
}
