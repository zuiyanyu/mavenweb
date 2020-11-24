package com.spring_stu.springAnnotations.Conditional.ConditionalOnMissingBean;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class OnBeanCondition implements Condition {


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        //如果是
        if(annotatedTypeMetadata.isAnnotated(ConditionalOnMissingBean.class.getName())){
            boolean beanIsNotExist = true ;
            beanIsNotExist = false ;
            System.out.println("获取自定义注解的参数：");
            MultiValueMap<String, Object> allAnnotationAttributes =
                    annotatedTypeMetadata.getAllAnnotationAttributes("org.springframework.context.annotation.Conditional");

            MultiValueMap<String, Object> allAnnotationAttributes2 =
                    annotatedTypeMetadata.getAllAnnotationAttributes("com.spring_stu.springAnnotations.Conditional.ConditionalOnMissingBean.ConditionalOnMissingBean");


            Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes("org.springframework.context.annotation.Conditional");

            Map<String, Object> annotationAttributes1 = annotatedTypeMetadata.getAnnotationAttributes("com.spring_stu.springAnnotations.Conditional.ConditionalOnMissingBean.ConditionalOnMissingBean");



            //            String type = (String)allAnnotationAttributes.getFirst("type");
//            Class[] conditions = ( Class[])allAnnotationAttributes.getFirst("value");

//            System.out.println("type:"+type);
//            for (Class condition : conditions) {
//                System.out.println("condition:"+condition.getName());
//            }
//            ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
//
//            for (Class condition : conditions) {
//                String[] beanNamesForType_condition = beanFactory.getBeanNamesForType(condition);
//                System.out.println(Arrays.toString(beanNamesForType_condition));
//            }
            return beanIsNotExist;
        }

            return  false ;
    }



}
