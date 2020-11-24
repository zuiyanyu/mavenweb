package com.spring_stu.springAnnotations.annotation_import.ImportBeanDefinitionRegistrar;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import({ImportBeanDefinitionRegistrarImpl.class})
public @interface MyImport {
    Class<?>[] value();
    String importType() default "importBeanDefinitionRegistrar";
}
