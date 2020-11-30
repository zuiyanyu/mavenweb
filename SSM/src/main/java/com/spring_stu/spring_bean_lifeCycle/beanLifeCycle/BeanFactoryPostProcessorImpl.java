package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * BeanFactory的后处理bean
 */
@Component
public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
    public BeanFactoryPostProcessorImpl(){
        System.out.println("BeanFactoryPostProcessorImpl的构造方法被调用了...");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessorImpl.postProcessBeanFactory()方法被调用了...");
    }
}
