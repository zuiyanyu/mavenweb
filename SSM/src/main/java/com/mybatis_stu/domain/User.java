package com.mybatis_stu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements Serializable {
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userAge=").append(userAge);
        sb.append(", birthday=").append(birthday);
        sb.append(", address=").append(address);
        sb.append("]");
        return sb.toString();
    }
}