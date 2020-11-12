package com.spring_stu.springAnnotations.annotation_import;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Import(ImportBeanDefinitionRegistrarImpl.class)
@Component
public class ImportBeanDefinitionRegistrarImpl_Test {
}
