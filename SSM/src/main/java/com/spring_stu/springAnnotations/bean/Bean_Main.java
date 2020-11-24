package com.spring_stu.springAnnotations.bean;

import com.spring_stu.springAnnotations.AnnotationVO;
import org.springframework.context.annotation.Bean;

public class Bean_Main {

    /**
     *  等价于 xml中的
     * 	<bean id ="annotationVO" class ="com.spring_stu.springAnnotations.AnnotationVO">
     * 		<constructor-arg value="annotationType"/>
     * 	</bean>
      */
    @Bean
    public AnnotationVO annotationVO(){
        return  new AnnotationVO("@bean") ;
    }
}
