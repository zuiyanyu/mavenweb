package com.spring_stu.springAnnotations.组件注册.Conditional_04.Conditional;

import com.spring_stu.springAnnotations.组件注册.Conditional_04.LinuxService;
import com.spring_stu.springAnnotations.组件注册.Conditional_04.ListService;
import com.spring_stu.springAnnotations.组件注册.Conditional_04.WindowsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/*
 *  配置类。
 *  1. @Configuration 底层就是@Component ，只能在类上进行注解。
 *  2. @Configuration 注解的类代表 此类是配置类，不做为其他功能。
 */
@Configuration
public class ConditionalBeanConfig {

    /**
     * All Condition classes that must match in order for the component to be registered.
     * TODO 1. @Conditional的value值  为 Condition接口的实现类
     * TODO 2. value值 是一个数组，可以指定多个 Condition，
     * TODO 2.所有给定的Condition类的match()方法都必须返回true，@Bean注解才能生效； 否则@Bean失效，被注释的类不能注入到IOC容器。
     * @return
     */
    @Bean
    @Conditional(value = {WindowsCondition.class})
    public ListService window(){
        System.out.println("Windows 运行环境中注册WindowsService...");
        return new WindowsService();
    }

    /**
     * 注册 LinuxService
     * @return
     */
    @Bean
    @Conditional(value = LinuxCondition.class)
    public ListService linux(){
        System.out.println("linux 运行环境中注册LinuxService...");

        return new LinuxService();
    }

    public static void main(String[] args) {
        System.setProperty("os.name","linux") ;

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalBeanConfig.class);
        ListService listService = applicationContext.getBean(ListService.class);
        System.out.println(
                applicationContext.getEnvironment().getProperty("os.name") +
                        "系统下的列表命令为：" + listService.showListCmd());
    }

}
