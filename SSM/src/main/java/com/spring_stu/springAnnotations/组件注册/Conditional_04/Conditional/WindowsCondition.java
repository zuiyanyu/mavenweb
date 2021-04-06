package com.spring_stu.springAnnotations.组件注册.Conditional_04.Conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断Windows的条件
 */
public class WindowsCondition implements Condition {
    /**
     *
     * @param conditionContext       获取spring上下文环境
     * @param annotatedTypeMetadata   当前类的所有注解的元数据信息
     * @return   true-被匹配成功，将被注解的类注入到IOC容器中。 false-匹配失败，不进行类注入到IOC容器
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        Environment environment = conditionContext.getEnvironment();
        // window 环境运行的系统
        return environment.getProperty("os.name").toLowerCase().contains("windows") ;
    }
}
