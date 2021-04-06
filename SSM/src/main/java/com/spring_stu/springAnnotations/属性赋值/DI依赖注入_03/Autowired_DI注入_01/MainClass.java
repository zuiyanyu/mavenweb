package com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.Autowired_DI注入_01;

import com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo.Car;
import com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo.Friend;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.Autowired_DI注入_01")
@PropertySource("classpath:com/spring_stu/springAnnotations/属性赋值/student.properties")
public class MainClass {
    @Bean
    @Primary
    public Car car01(){ return new Car("car01"); }
    @Bean
    public Car car021(){ return new Car("car02"); }
    @Bean
    public Car car03(){ return new Car("car03"); }
    @Bean
    public Car car04(){ return new Car("car04"); }
    @Bean
    public Car car05(){ return new Car("car05"); }
    @Bean
    public Friend friend(){return new Friend();}

//    @Bean
//    public Student student(){
//        return new Student();
//    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainClass.class);
        Student student = applicationContext.getBean("student", Student.class);
        student.getValueInfo("friend");
    }
}
