package com.spring_stu.spring_bean_lifeCycle.spring_PostProcessor.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * bean工厂的bean属性处理容器，说通俗一些就是可以管理我们的bean工厂内所有的beandefinition（未实例化）数据，可以随心所欲的修改属性。
 * BeanFactoryPostProcessor 只能修改 beanDefination 属性，却不能修改已经实例化的bean属性.
 *
 */
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public CustomBeanFactoryPostProcessor(){
        System.out.println("CustomBeanFactoryPostProcessor 的构造器被调用了...");
    }
    //这里定义的初始化方法不会被调用的。
    @PostConstruct
    public void initMethod(){
        System.out.println("CustomBeanFactoryPostProcessor 的初始化方法被调用了...");
    }
    /**
     * 主要是用来自定义修改持有的bean
     * ConfigurableListableBeanFactory 其实就是DefaultListableBeanDefinition对象
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("=====================================");
        System.out.println("调用了自定义的BeanFactoryPostProcessor " + beanFactory);
        //Iterator it = beanFactory.getBeanNamesIterator();

        /*
        这里获取的是 BeanDefinition，bean被定义了，但是不一定被实例化了。
            1).普通bean只被定义了BeanDefinition，而没有被实例化；
            2).特殊bean,比如BeanFactoryPostProcessor，就已经被实例化了。
            3).所以BeanFactoryPostProcessor 可以通过修改普通bean的BeanDefinition来修改bean的创建过程，
               但是却无法对已经创建实例的特殊bean进行bean创建过程的干扰。
         */
        String[] names = beanFactory.getBeanDefinitionNames();
        // 获取了所有的bean名称列表
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if(name.startsWith("org.springframework")){
                continue;
            }
            BeanDefinition bd = beanFactory.getBeanDefinition(name);
            MutablePropertyValues propertyValues = bd.getPropertyValues();
            System.out.println(name + "--> bean properties: " +propertyValues.toString() );
            // 本内容只是个demo，打印持有的bean的属性情况


        }
        System.out.println("=====================================");
    }
}
