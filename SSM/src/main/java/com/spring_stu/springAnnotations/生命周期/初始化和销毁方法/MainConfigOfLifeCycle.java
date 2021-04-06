package com.spring_stu.springAnnotations.生命周期.初始化和销毁方法;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 *TODO bean的生命周期：
 * 		bean创建---初始化----销毁的过程
 *容器管理bean的生命周期；
 *我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * TODO 指定初始化和销毁方法有四种方式（重要）：
 *  1. 方式1： xml配置中指定初始化和销毁方法：
 *     指定init-method 和 destroy-method
 *  2. 方式2，在@Bean注解中指定初始化和销毁方法：(和1可以认为是同一种方式)
 *       @Bean(initMethod="init",destroyMethod="detory")
 *  3. 方式3 通过让Bean实现：
 *      InitializingBean（定义初始化逻辑）
 *      DisposableBean（定义销毁逻辑）;，
 *  4. 方式4 使用注解的方式
 *        @PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
          @PreDestroy：在容器销毁bean之前通知我们进行清理工作
 *  5. BeanPostProcessor【interface】：bean的后置处理器；
 * 		在bean初始化前后进行一些处理工作；
 * 		postProcessBeforeInitialization:在初始化方法（init-method）之前工作
 * 		postProcessAfterInitialization:在初始化方法（init-method）之后工作
 *
 *TODO 方法执行流程(重要)：
 *  执行0： 构造（对象创建）
 * 		   单实例：在容器启动的时候创建对象
 * 		   多实例：在每次获取的时候创建对象\
 *  执行1：BeanPostProcessor.postProcessBeforeInitialization
 *  执行2：初始化：
 *   		对象创建完成，并赋值好，调用初始化方法。。。
 *  执行3：BeanPostProcessor.postProcessAfterInitialization
 *  执行4： 销毁：
 * 		单实例：容器关闭的时候
 * 		多实例：容器不会管理这个bean；容器不会调用销毁方法；

 */
@ComponentScan("com.spring_stu.springAnnotations.生命周期.初始化和销毁方法")
public class MainConfigOfLifeCycle {
    //@Scope("prototype")
   // @Bean(initMethod="init",destroyMethod="detory")
    public Car car(){
        return new Car();
    }

    @Bean
    public Car2 car2(){
        return new Car2();
    }

    /**
     * car3 constructor...
     * car3 ... init...
     * InitializingBean 的afterPropertiesSet方法被执行了...
     * IOC容器创建完成!
     * car3 ... detory...
     * DisposableBean 的销毁方法被执行了...
     * 容器销毁成功！
     */
    //@Bean
    public Car3 car3(){
        return new Car3();
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("IOC容器创建完成!");

        applicationContext.close();
        System.out.println("容器销毁成功！");

    }
}
