package com.spring_stu.springAnnotations.属性赋值.StringValueResolver_赋值_04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:com/spring_stu/springAnnotations/属性赋值/student.properties")
public class MainClass {
    @Bean
    public Student student(){
        return new Student();
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainClass.class);
        Student student = applicationContext.getBean("student", Student.class);
        //Student(name=张三, age=20, friends=zhangsan,lisi)
        System.out.println(student);
    }
}
