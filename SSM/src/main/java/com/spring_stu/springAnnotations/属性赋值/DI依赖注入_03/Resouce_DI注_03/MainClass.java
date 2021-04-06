package com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.Resouce_DI注_03;

import com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo.Friend;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainClass {
    @Bean
    public Friend friend(){
        return new Friend();
    }
    @Bean
    public Student student(){
        return new Student() ;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainClass.class);
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student);
    }
}
