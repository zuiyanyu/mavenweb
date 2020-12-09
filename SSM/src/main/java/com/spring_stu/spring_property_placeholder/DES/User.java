package com.spring_stu.spring_property_placeholder.DES;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class User {
    //直接给字段进行注入
    @Value("${eds.jdbc.password}")
    private String password ;

    private String userName ;
    //通过setter方法进行属性进行注入
    @Value("${eds.name}")
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
