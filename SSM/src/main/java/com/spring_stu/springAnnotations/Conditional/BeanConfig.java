package com.spring_stu.springAnnotations.Conditional;

import com.spring_stu.springAnnotations.Conditional.ConditionalOnMissingBean.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/*
 *  配置类。
 *  1. @Configuration 底层就是@Component ，只能在类上进行注解。
 *  2. @Configuration 注解的类代表 此类是配置类，不做为其他功能。
 */
@Configuration
public class BeanConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService window(){
        return new WindowsService();
    }

    /**
     * 注册 LinuxService
     * @return
     */
    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linux(){
        return new LinuxService();
    }

    /**
     * 当LinuxService再IOC中不存在的时候，进行注册；
     * 否则不进行注册。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = {LinuxCondition.class},type = "linux2")
    public ListService linux2(){
        return new LinuxService();
    }

    /**
     * 当LinuxService再IOC中不存在的时候，进行注册；
     * 否则不进行注册。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = {WindowsCondition.class},type = "window2")
    public ListService window2(){
        return new WindowsService();
    }


}
