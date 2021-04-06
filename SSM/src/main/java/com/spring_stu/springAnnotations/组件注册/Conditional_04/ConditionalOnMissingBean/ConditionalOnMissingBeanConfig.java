package com.spring_stu.springAnnotations.组件注册.Conditional_04.ConditionalOnMissingBean;

import com.spring_stu.springAnnotations.组件注册.Conditional_04.LinuxService;
import com.spring_stu.springAnnotations.组件注册.Conditional_04.ListService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalOnMissingBeanConfig {
//    @Bean
//    @ConditionalOnMissingBean(value = {LinuxService.class},type = "linux2")
//    public ListService linux1(){
//        System.out.println("===========1");
//        return new LinuxService();
//    }
    /**
     * 当LinuxService再IOC中不存在的时候，进行注册；
     * 否则不进行注册。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = {LinuxService.class},type = "linux")
    public ListService linux2(){
        System.out.println("===========2");
        return new LinuxService();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalOnMissingBeanConfig.class);
        ListService listService = applicationContext.getBean(ListService.class);
        System.out.println("listService: "+listService.showListCmd());
    }


}
