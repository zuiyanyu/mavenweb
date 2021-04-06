package com.spring_stu.springAnnotations.生命周期.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {
    public BeanPostProcessorImpl(){
        System.out.println("BeanPostProcessor的构造方法...");
    }
    //TODO 执行bean实例属性设置 -> 执行此方法 ->执行bean实例的初始化方法( @PostConstruct标注的方法)
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //这里可以进行一些bean处理,比如生成一个 代理bean返回 。 实例；aop的实现就离不开这个方法
        System.out.println("BeanPostProcessor的postProcessBeforeInitialization被调用了，beanName="+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor的postProcessAfterInitialization被调用了,beanName = "+beanName);
        return bean;
    }
}
