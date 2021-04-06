package com.spring_stu.springAnnotations.组件注册.Conditional_04.ConditionalOnMissingBean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnBeanCondition implements Condition {
    @Autowired
    ApplicationContext applicationContext ;
    /**
     * 判断IOC容器中是否还没有注入 @ConditionalOnMissingBean注解中的value值指定的类。
     * @param conditionContext
     * @param annotatedTypeMetadata  当前类注解的元数据信息
     * @return  true-value值指定的类还没有被注入到IOC容器，false-已经注入了。
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        System.out.println("applicationContext = "+applicationContext);
        //判断当前类是否被标注了 @ConditionalOnMissingBean 注解。
        if(annotatedTypeMetadata.isAnnotated(ConditionalOnMissingBean.class.getName())){
            //默认 value值指定的类还没有被注入到IOC容器
            boolean beanIsNotExist  = true;

            System.out.println("获取自定义注解的参数：");

            Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnMissingBean.class.getName());
            System.out.println("Conditional=== annotatedTypeMetadata.annotationAttributes =====");
            Class[] classValues =null;
            for (Map.Entry<String, Object> stringObjectEntry : annotationAttributes.entrySet()) {
                String key = stringObjectEntry.getKey();
               Object value = stringObjectEntry.getValue();

               if(key !=null && "value".equals(key)){
                   classValues = (Class[])value ;
               }else{
                   System.out.println("key =" + key + "   ;value = " + value.getClass().getName());
               }
            }

            ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                System.out.println("beanDefinitionName = " + beanDefinitionName);

            }


            System.out.println("============================");

//            if(null != classValues)
//            for (Class classValue : classValues) {
//                String className = classValue.getName();
//                System.out.println("className = " + className);
//
//                String[] beanNamesForType = beanFactory.getBeanNamesForType(classValue,true, false);
//                System.out.println("beanNamesForType.length = "+beanNamesForType.length);
//                if(beanNamesForType.length > 0 ){
//                    System.out.println(className + " 在IOC中已经被注册到过！");
//                    beanIsNotExist = false ;
//                }
//            }


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
            System.out.println("beanIsNotExist = "+beanIsNotExist);
            return beanIsNotExist;
        }

            return  false ;
    }



}
