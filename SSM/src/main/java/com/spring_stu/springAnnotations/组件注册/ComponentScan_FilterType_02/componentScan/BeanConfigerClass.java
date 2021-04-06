package com.spring_stu.springAnnotations.组件注册.ComponentScan_FilterType_02.componentScan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * value : 指定要扫描的包   别名是 basePackages
 * excludeFilters =Filter[] ：指定扫描的时候按照什么规则排除哪些组件

 * includeFilters =Filter[] : 指定扫描的时候只需要包含哪些组件 , 需要关闭自动扫描 useDefaultFilters=false  。
 * useDefaultFilters ：指示是否应该启用用@Component @Repository、@Service或@Controller注释的类的自动检测。
 *
 */
//扫描包中类的时候，排除被@Controller注解的类
// excludeFilters ={@ComponentScan.Filter(type = FilterType.ANNOTATION,value={Controller.class})})

//只扫描包中  @Controller注解的类。 需要关闭自动扫描 useDefaultFilters=false
@ComponentScan(basePackages = "com.spring_stu.springAnnotations.组件注册.dsc",
         includeFilters ={@ComponentScan.Filter(type = FilterType.ANNOTATION,value={Controller.class})}
        ,useDefaultFilters=false)

/*
  FilterType 属性讲解
      type : 要过滤的规则类型 ，有如下几种类型
      1） annotation     ： 根据注解进行过滤
      2） assignable_type ： 根据指定的类型进行过滤
      3） aspectj  ：根据ASPECTJ进行过滤(不常用)
      4) regex     ：根据正则表达式进行过滤
      5) custom    ：用户自定义 过滤规则，需要实现 org.springframework.core.type.filter.TypeFilter 接口
 */
public class BeanConfigerClass  {
    public static void main(String[] args) {
        //加载配置类
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanConfigerClass.class);

        System.out.println("IOC容器注册完成...");

        //获取被注册的Bean实例
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
