package com.spring_stu.springAnnotations.组件注册.Conditional_04.ConditionalOnMissingBean;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 我们来模拟下SringBoot 中的 ConditionalOnMissingBean 简单实现原理
 * 功能描述：仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean。
 * 条件判断类：OnBeanCondition
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional({OnBeanCondition.class})
public @interface ConditionalOnMissingBean {
    Class<? extends Object>[] value();
    String type() default "conditional";
}
