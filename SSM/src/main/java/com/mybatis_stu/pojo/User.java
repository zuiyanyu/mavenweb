package com.mybatis_stu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements Serializable {
    //多对多查询：在User类中添加List<Orders> orders;  一个用户多个订单
    List<Orders> orders ;

    // 注册的账号id
    private Integer userId;

    // 用户名
    private String userName;

    // 用户年龄
    private Integer userAge;

    // 用户生日
    @JsonFormat(pattern="yyyy-MM-dd" ,timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyyy-MM-dd")
    private Date birthday;

    // 用户地址
    private String address;

     // tableName : user
    private static final long serialVersionUID = 1L;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "orders=" + orders +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }
}