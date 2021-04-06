package com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.Resouce_DI注_03;

import com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo.Friend;
import lombok.Data;

import javax.annotation.Resource;

@Data
public class Student {
    private String name = "张三";

    /**
     * TODO  1. Spring 还支持 @Resource  [java规范的注解] ，必须能装配成功，否则报异常。
     * 可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配的；
     * 没有能支持@Primary功能，没有支持@Autowired(required=false)功能。
     */
    @Resource
    private Friend friend;
}
