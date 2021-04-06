package com.spring_stu.springAnnotations.属性赋值.value注解_赋值_01;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用@Value注解进行赋值，有三种方式：
 */
@Data
@Component
public class Student {
    //TODO 1. 基本赋值
    @Value("张三")
    private String name ;

    //TODO 2.可以使用SpEL，#{}进行赋值
    @Value("#{22-2}")
    private Integer age ;

    //TODO 3.可以使用${}进行赋值,取出配置文件中的值(在运行环境变量里面的值)
    //TODO   需要使用 @PropertySource("classpath:属性配置文件路径") 先进行配置文件的加载。
    @Value("${stu.friends}")
    private String friends ;

    //TODO 4.  @Value可以用在方法上面
    private String userName ;
    @Value("${stu.name}")
    public void setUserName(String userName){
        this.userName = userName ;
    }

}
