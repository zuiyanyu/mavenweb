package com.spring_stu.springAnnotations.annotation_import;

/**
 * TODO spring注解之@Import注解的三种使用方式
 * 目录
 * 1、@Import注解须知
 * 2、@Import的三种用法
 * 2.1、第一种用法：直接填class数组
 * 2.2、第二种用法：ImportSelector方式【重点】
 * 2.3、第三种用法：ImportBeanDefinitionRegistrar方式
 *
 *com.spring_stu.springAnnotations.annotation_import.TestDemo
 * 一、@Import注解须知
 * 1、@Import只能用在类上 ，@Import通过快速导入的方式实现把实例加入spring的IOC容器中
 * 2、加入IOC容器的方式有很多种，@Import注解可以用于导入第三方包 ，当然@Bean注解也可以，但是@Import注解快速导入的方式更加便捷
 * 3、@Import注解有三种用法
 *
 * 二、@Import的三种用法
 * @Import的三种用法主要包括：
    2.1、第一种用法：直接填class数组
    直接填对应的class数组，class数组可以有0到多个。
    语法如下：
     @Import({ 类名.class , 类名.class... })
     public class TestDemo {

     }
     对应的import的bean都将加入到spring容器中，这些在容器中bean名称是该类的全类名 ，比如com.yc.类名


 *
 */
public interface ImportAnnotationInfo {
}
