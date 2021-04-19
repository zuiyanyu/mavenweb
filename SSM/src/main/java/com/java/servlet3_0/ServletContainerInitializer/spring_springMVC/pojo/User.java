package com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.pojo;

import lombok.Data;

@Data
public class User {
    private int id ;
    private String userName ;
    private int age ;
    private String addr ;

    public User(int id ){
        this.id = id ;
    }
    public User( ){
    }
}
