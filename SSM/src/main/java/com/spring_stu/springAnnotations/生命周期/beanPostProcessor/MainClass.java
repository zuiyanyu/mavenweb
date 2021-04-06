package com.spring_stu.springAnnotations.生命周期.beanPostProcessor;

import com.spring_stu.springAnnotations.生命周期.初始化和销毁方法.Car2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *TODO  BeanPostProcessor执行原理：
 * （在postProcessBeforeInitialization（）方法中打一个断点进行执行过程的跟踪。）
 *遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
 * 一但返回null，跳出for循环，不会执行后面的BeanPostProcessor.postProcessorsBeforeInitialization
 *
 * BeanPostProcessor原理
 * populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 * initializeBean
 * {
 * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化
 * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *}
 *
 * Spring底层对 BeanPostProcessor 的使用；
 * 		bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async,xxx BeanPostProcessor;
 *
 */
@Configuration
public class MainClass {
    //注入后处理bean
    @Bean
    public BeanPostProcessorImpl beanPostProcessorImpl(){
        return new BeanPostProcessorImpl();
    }
    //bean实现了初始化方法和销毁方法
    @Bean
    public Car2 car2(){
        return new Car2();
    }

    /**
     * BeanPostProcessor的构造方法...
     * car2 constructor...
     * BeanPostProcessor的postProcessBeforeInitialization被调用了，beanName=car2
     * car2 ... init...
     * BeanPostProcessor的postProcessAfterInitialization被调用了,beanName = car2
     * IOC容器创建完成!
     * car2 ... detory...
     * 容器销毁成功！
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainClass.class);
        System.out.println("IOC容器创建完成!");

        applicationContext.close();
        System.out.println("容器销毁成功！");

    }
}
