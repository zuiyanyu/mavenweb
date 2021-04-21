package com.spring_stu.spring_PropertyEditor.customPropertyEditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.*;

/**
 * PropertyEditor
 * TODO 1. 它只能将字符串转换为一个Java对象
 * TODO 2. 注册属性编辑器的时候，一个类型会绑定到一个属性编辑器，关系是 1对1的关系。
 *
 * TODO 3. 在web项目中，如果只看与前端交互的那一部分，这个功能的确已经足够了(只能将字符串转换为一个Java对象),
 *         但是在后台项目内部可就得重新想办法了。Spring针对这个问题设计了Converter模块，它位于org.springframework.core.converter包中.
 * TODO 4. Converter模块足以替代原生的PropertyEditor.
 * TODO 5. 但是spring选择了同时支持两者，在Spring MVC处理参数绑定时就用到了。
 *
 注册其他自定义 PropertyEditor 实现
 当将 bean 属性设置为字符串值时，Spring IoC 容器最终使用标准 JavaBeans PropertyEditor实现，将这些字符串转换为属性的复杂类型。
 Spring 预注册了许多自定义PropertyEditor实现(例如，将表示为字符串的类名转换为Class对象)。另外，Java 的标准 JavaBeans PropertyEditor查找机制允许为类的PropertyEditor适当命名，并与它提供支持的类放在同一包中，以便可以自动找到它。

 如果需要注册其他自定义PropertyEditors，则可以使用几种机制。
 手动的方法(通常不方便或不建议使用)是使用ConfigurableBeanFactory接口的registerCustomEditor()方法(假设您有BeanFactory引用)。
 另一种(稍微方便些)的机制是使用称为CustomEditorConfigurer的特殊BeanFactoryPostProcessor。

 尽管您可以将BeanFactoryPostProcessor与BeanFactory实现一起使用，但是CustomEditorConfigurer具有嵌套的属性设置，因此我们强烈建议您将CustomEditorConfigurer与ApplicationContext一起使用，在这里您可以以与其他任何 Bean 类似的方式部署它，并且可以将其放置在自动检测并应用。

 请注意，
 所有 beanFactory和ApplicationContext通过使用BeanWrapper来处理属性转换，都会自动使用许多内置的属性编辑器。 BeanWrapper寄存器的标准属性编辑器在上一节中列出。此外，ApplicationContexts还重写或添加其他编辑器，以适合于特定应用程序上下文类型的方式处理资源查找。
 */
@Configuration
@ComponentScan("com.spring_stu.spring_PropertyEditor.customPropertyEditor")
@PropertySource("classpath:jdbcInfo.properties")
public class ConfigClass {
//    @Bean
//    public CustomEditorConfigurer customEditorConfigurer(){
//        //TODO 第三种注册属性编辑器的方式  CustomEditorConfigurer
//        HashMap<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();
//        customEditors.put(Car.class,CustomCarEditor.class);
//        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
//        customEditorConfigurer.setCustomEditors(customEditors);
//        return customEditorConfigurer;
//    }
    @Bean
    public CustomEditorConfigurer customEditorConfigurer(PropertyEditorRegistrar propertyEditorRegistrar){
        //TODO 第四种注册属性编辑器的方式  PropertyEditorRegistrar
        //com.spring_stu.spring_PropertyEditor.customPropertyEditor.CustomPropertyEditorRegistrar
        System.out.println(propertyEditorRegistrar.getClass().getName());

        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{propertyEditorRegistrar});
        return customEditorConfigurer;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //CustomEditorConfigurer
        /** TODO 两种注册属性编辑器的方式
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

