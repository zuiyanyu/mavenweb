package com.springMVC.domain;

import lombok.Data;

@Data
public class User {
    private String userName ;
    private Integer age ;

    public void setUserName(String userName){
        this.userName = userName ;
    }
}
