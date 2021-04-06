package com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo;

import lombok.Data;

@Data
public class Car {
    private String carName  ;
    private String color ="RED" ;

    public Car(String carName){
        this.carName = carName ;
    }
}
