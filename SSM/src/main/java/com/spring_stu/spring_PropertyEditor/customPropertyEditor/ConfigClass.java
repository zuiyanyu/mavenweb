package com.spring_stu.spring_PropertyEditor.customPropertyEditor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.spring_stu.spring_PropertyEditor.customPropertyEditor")
@PropertySource("classpath:jdbcInfo.properties")
public class ConfigClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        /**
         * 如果在Car的相同包下有CarEditor，那么spring就会自动使用CarEditor作为此Car类型的编辑器，
         * 那么这里就不用进行手工注册CarEditor编辑器了。
         * 但是我们为Car类型自定义的编辑器命名是CustomCarEditor，所以还需要进行手工注册编辑器。
         */
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        beanFactory.registerCustomEditor(Car.class,CustomCarEditor.class);

        applicationContext.register(ConfigClass.class);
        applicationContext.refresh();

        Boss boss = applicationContext.getBean("boss", Boss.class);
        System.out.println(boss);
        applicationContext.close();
    }
}

