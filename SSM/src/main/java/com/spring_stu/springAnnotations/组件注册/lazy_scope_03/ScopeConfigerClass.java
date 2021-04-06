package com.spring_stu.springAnnotations.组件注册.lazy_scope_03;

import com.spring_stu.springAnnotations.组件注册.dsc.pojo.Person;
import com.spring_stu.springAnnotations.组件注册.dsc.pojo.User;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(value ="com.spring_stu.springAnnotations.组件注册.dsc.dao")
public class ScopeConfigerClass {
    /**
     * 实例范围 scope有：
     *   TODO singleton：单实例的(默认值)
     *   TODO prototype：多实例的
     *   TODO       IOC容器启动的时候并不会调用方法创建一个对象放到容器中。每次获取的时候才会调用方法创建对象。
     *              每次请求都会创建一个新实例，不会放到IOC容器中。
     *   TODO request ： 同一个请求创建一个实例
     *   TODO session：  同一个session创建一个实例   一个会话
     *   TODO application：同一个web应用创建一个实例  一个web程序
     *   TODO servletContext：同一个容器创建一个实例 tomcat
     * @return
     */
    @Bean("person")
    @Scope(value="prototype")
    public Person person(){
        System.out.println("Person被创建了...");
        return new Person();
    }

    /**
     *   @Lazy 会让实例只有在使用的时候才会被创建
     * @return
     */
    @Bean("user")
    @Scope(value="singleton")
    @Lazy
    public User user(){
        System.out.println("单例的user被创建了...");
        return new User();
    }
    public static void main(String[] args) {
        //加载配置类
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ScopeConfigerClass.class);

        System.out.println("IOC容器注册完成...");

        //获取被注册的Bean实例
        Person person1 = applicationContext.getBean("person", Person.class);
        Person person2 = applicationContext.getBean("person", Person.class);
        System.out.println("person1 == person2 : "+  (person1 == person2));

        applicationContext.getBean("user",User.class);

    }
}
