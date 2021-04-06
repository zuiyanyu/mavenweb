package com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.Inject_DI注入_02;

import com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo.Friend;
import lombok.Data;

import javax.inject.Inject;

@Data
public class Student {
    private String name = "张三" ;

    /**
     *TODO  Spring 还支持 @Inject  [java规范的注解]，和@Autowired功能一样，但是没有 required=false的属性。
     * 需要导包：
     *          <!--使用@Inject注解需要的jar包-->
     *         <dependency>
     *             <groupId>javax.inject</groupId>
     *             <artifactId>javax.inject</artifactId>
     *             <version>1</version>
     *         </dependency>
     */
    @Inject
    private Friend friend ;
}
