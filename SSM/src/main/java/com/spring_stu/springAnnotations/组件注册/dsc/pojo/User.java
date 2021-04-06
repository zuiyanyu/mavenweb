package com.spring_stu.springAnnotations.组件注册.dsc.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(of = {"userName","age"})
public class User {
    private String userName ;
    private Integer age ;
}
