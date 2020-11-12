package com.spring_stu.springAnnotations.annotation_import;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

//同样是一个接口，类似于第二种ImportSelector用法，相似度80%，只不过这种用法比较自定义化注册
public class ImportBeanDefinitionRegistrarImpl implements ImportBeanDefinitionRegistrar {
    /**
     * 不常用
     * 第三种用法：ImportBeanDefinitionRegistrar：手动注册bean到容器,可自定义方式。
     * 参数分析：
     * 第一个参数：annotationMetadata 和之前的ImportSelector参数一样都是表示当前被@Import注解给标注的所有注解信息
     * 第二个参数表示用于注册定义一个bean
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        System.out.println("==== registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry)===");
        //指定bean定义信息（包括bean的类型、作用域...）
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TestDemo4.class);
        //注册一个bean指定bean名字（id）
        beanDefinitionRegistry.registerBeanDefinition("TestDemo4444",rootBeanDefinition);
    }
}
