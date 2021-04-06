package com.spring_stu.springAnnotations.组件注册.ImportSelector_05.ImportSelector_03;

import com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean01;
import com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 *TODo @Import注解的 value 可以是有以下几种：
 *TODo     1）@Configuration,
 *TODo     2）ImportSelector,
 *TODo     3）ImportBeanDefinitionRegistrar  @Import({ImportBeanDefinitionRegistrarImpl.class})
 *            只有在@Import标注的目标类进行注入的时候，@Import注解才会生效。
 *TODo     4）普通的类.     @Import(value = {Address.class,Bean01.class})
 */
/**
 * 加载顺序为：
 * 1. 先注入 @Component 注解的类(bean name 是类名首字母小写)；
 * 2. 然后注入 @Import 注解中设置的类。(bean name 是全类名)
 * 3. 然后注入 选择器ImportSelector 返回的类集合。(bean name 是全类名) (此时ApplicationContext还没准备好)
 * 4. 然后注入@Bean注解要注入的类。(bean name 是方法名bookDao) (此时ApplicationContext已经准备好)
 *
 */
@Import({ImportSelectorImpl.class})
//@Component
public class ImportSelector_Main {
    //这里 ApplicationContext 准备好了，能进行注入；
    @Autowired
    ApplicationContext applicationContext ;

    //向容器中注入一个bean ,beanName 是方法名。
    @Bean
    public Bean01 bean01(){
        System.out.println("===========ImportSelector_Main：applicationContext==============");
        // bean id的名称都已经进行注册，但是bean01的真实实体还没有注入进来，其他的两个已经注入进IOC了。
        /**
         * importSelector_Main
         * com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean02
         * bean01
         */
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if(!beanDefinitionName.startsWith("org.springframework.context")){
                System.out.println(beanDefinitionName);
            }
        }
        System.out.println("===========1=============");
        //[importSelector_Main]
        String[] beanNamesForType = applicationContext.getBeanNamesForType(ImportSelector_Main.class);
        System.out.println(Arrays.toString(beanNamesForType)); //[importSelector_Main]

        //[bean01]
        String[] beanNamesForType1 = applicationContext.getBeanNamesForType(Bean01.class);
        System.out.println(Arrays.toString(beanNamesForType1)); //[importSelector_Main]

        //[com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean02]
        String[] beanNamesForType2 = applicationContext.getBeanNamesForType(Bean02.class);
        System.out.println(Arrays.toString(beanNamesForType2)); //[importSelector_Main]

        System.out.println("=========================");
        return new Bean01();
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportSelector_Main.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
