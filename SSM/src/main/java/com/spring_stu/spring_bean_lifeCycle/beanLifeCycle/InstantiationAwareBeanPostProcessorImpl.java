package com.spring_stu.spring_bean_lifeCycle.beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

public class InstantiationAwareBeanPostProcessorImpl implements InstantiationAwareBeanPostProcessor {
    public InstantiationAwareBeanPostProcessorImpl(){
        System.out.println("InstantiationAwareBeanPostProcessorImpl的构造器被调用了...");
    }
    /**
     * 1. 此方法会在目标bean被实例化(即目标bean的构造器执行前)前执行；
     * 2. 如果返回的是bean实例，比如bean的代理对象,会替代目标bean；
     * 3. 如果一个非Null实例被返回，那么默认的bean创建过程将被短路，后续的处理过程中，
     *    只有InstantiationAwareBeanPostProcessor.postProcessAfterInitialization() 回调方法被执行。
     *
     * @param beanClass  InstantiationAwareBeanPostProcessor 要被实例化但是还没有被实例化的bean的类型。
     * @param beanName   bean的name
     * @return
     * 1.如果返回的是一个bean实例，比如bean的代理类，那么bean就不会再被创建，直接使用这里返回的实例bean；
     * 2.如果返回的是null,那么就代表仍然使用bean的默认创建方式，创建出的就是普通bean实例本身。
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorImpl.postProcessBeforeInstantiation()方法被执行了...");
        return null;
    }

    /**
     * 1. 方法在bean被实例化之后,且在bean实例属性注入之前执行。
     * 2. bean实例可以是通过构造器执行创建的，也可以是通过 工厂bean的方法调用来创建的。
     * 3.如果你想在spring自动为bean实例自动注入属性前，来自己手动为bean实例设置属性，那么这个方法是一个理想的回调方法
     * @param bean  已经被实例化,但是还没有被注入属性的bean实例。
     * @param beanName the name of the bean
     * @return
     * 1. 如果返回true, 那么spring仍然后为bean实例进行属性注入；
     *      (可能覆盖掉这里自己手动为bean实例注入的属性值；)
     * 2. 如果返回false,那么就会禁用spring为此bean实例进行属性注入；
     *      (如果这里自己手动没有为bean实例进行属性注入值，那么此bean实例就没有被注入属性。)
     * 3.  通常，我们都会返回true ,如果返回false，就会阻止InstantiationAwareBeanPostProcessor对此bean实例执行后续的回调方法。
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorImpl.postProcessAfterInstantiation()方法被执行了...");
        return false;
    }

    /**
     * Post-process the given property values before the factory applies them
     * to the given bean. Allows for checking whether all dependencies have been
     * satisfied, for example based on a "Required" annotation on bean property setters.
     * <p>Also allows for replacing the property values to apply, typically through
     * creating a new MutablePropertyValues instance based on the original PropertyValues,
     * adding or removing specific values.
     * @param pvs the property values that the factory is about to apply (never {@code null})
     * @param pds the relevant property descriptors for the target bean (with ignored
     * dependency types - which the factory handles specifically - already filtered out)
     * @param bean the bean instance created, but whose properties have not yet been set
     * @param beanName the name of the bean
     * @return the actual property values to apply to the given bean
     * (can be the passed-in PropertyValues instance), or {@code null}
     * to skip property population
     * @throws org.springframework.beans.BeansException in case of errors
     * @see org.springframework.beans.MutablePropertyValues
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorImpl.postProcessPropertyValues()方法被执行了...");

        return null;
    }


    //后处理bean  bean实例被初始化前执行
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorImpl.postProcessPropertyValues()方法被执行了...");
        return bean;
    }
    //后处理bean bean实例被初始化后执行
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessorImpl.postProcessPropertyValues()方法被执行了...");
        return bean;
    }
}
