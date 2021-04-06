package com.spring_stu.springAnnotations.组件注册.ImportSelector_05.ImportBeanDefinitionRegistrar_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 *TODo @Import注解的 value 可以是有以下几种：
 *TODo     1）@Configuration,
 *TODo     2）ImportSelector,
 *TODo     3）ImportBeanDefinitionRegistrar  @Import({ImportBeanDefinitionRegistrarImpl.class})
 *            只有在@Import标注的目标类进行注入的时候，@Import注解才会生效。
 *TODo     4）普通的类.     @Import(value = {Address.class,Bean01.class})
 */
/**
 *只有在@Import标注的目标类进行注入的时候，@Import注解才会生效。
 */

@Import({ImportBeanDefinitionRegistrarImpl.class})
//@Component
//@Configuration
@ComponentScan("com.spring_stu.springAnnotations.组件注册.ImportSelector_05.ImportBeanDefinitionRegistrar_2")
public class ImportBeanDefinitionRegistrarImpl_Main {

    public static void main(String[] args) {
        //1. 获取spring上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportBeanDefinitionRegistrarImpl_Main.class);

        //2.获取IOC 容器中所有注入的bean 的 id名，并进行输出
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
