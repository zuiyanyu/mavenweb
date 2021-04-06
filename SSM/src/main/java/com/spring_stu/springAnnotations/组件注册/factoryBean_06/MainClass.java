package com.spring_stu.springAnnotations.组件注册.factoryBean_06;

import com.spring_stu.springAnnotations.组件注册.dsc.pojo.Color;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainClass {
    /**
     * 给容器中注册组件；
     * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速给容器中导入一个组件]
     * 		1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     * 		2）、ImportSelector:返回需要导入的组件的全类名数组；
     * 		3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4）、使用Spring提供的 FactoryBean（工厂Bean）;
     * 		1）、默认获取到的是工厂bean调用getObject创建的对象
     * 		2）、要获取工厂Bean本身，我们需要给id前面加一个& （在BeanFactory中定义着变量）
     * 		   applicationContext.getBean("&colorFactoryBean");
     *
     */
    /**
     * 在获取bean实例的是偶，
     * @return 返回值是一个 工厂bean实例。
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainClass.class);
        //TODO ============获取工厂bean创建的bean实例
        //TODO 1. 通过bean类型获取
        //bean = com.spring_stu.springAnnotations.组件注册.dsc.pojo.Color@309e345f
        Color bean = applicationContext.getBean(Color.class);
        System.out.println("bean = "+ bean);

        //TODO 通过bean 的id获取  如果bean是一个工厂bean,那么在获取bean实例的时候会调用工厂bean的getObjet()，将返回值作为bean实例返回。
        //bean2 = com.spring_stu.springAnnotations.组件注册.dsc.pojo.Color@309e345f
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean2 = "+ bean2);

        //TODO 如果就是想获取工厂bean实例本身，我们需要给id前面加一个&
        //bean3 = com.spring_stu.springAnnotations.组件注册.factoryBean.ColorFactoryBean@56a6d5a6
        Object bean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean3 = "+ bean3);

        //TODO ============ 查看 Color被注入到IOC中的bean id 名字有哪些
        System.out.println();
        String[] namesForType = applicationContext.getBeanNamesForType(Color.class);
        for (String name : namesForType) {
            System.out.println(name); //colorFactoryBean
        }
    }
}
