package com.spring_stu.springAnnotations.annotation_import.ImportBeanDefinitionRegistrar;

import com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean01;
import org.springframework.stereotype.Component;

/**
 * 注释掉@Component ，TestDemo01类也没有进行注入，即Import注解失效了。
 * 说明，只有在@Import标注的目标类进行注入的时候，@Import注解才会生效。
 */
@MyImport(Bean01.class)
@Component
public class ImportBeanDefinitionRegistrarImpl_Main {
}
