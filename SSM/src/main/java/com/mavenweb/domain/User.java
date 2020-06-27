package com.mavenweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    String userName ;
//    Integer userAge ;
    String userNo ; //学号
    Date birthday ;
    String ignoreUnknownTest ;

    public String getIgnoreUnknownTest() {
        return ignoreUnknownTest;
    }

    public void setIgnoreUnknownTest(String ignoreUnknownTest) {
        this.ignoreUnknownTest = ignoreUnknownTest;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
//                ", userAge='" + userAge + '\'' +
                ", userNo='" + userNo + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Integer getUserAge() {
//        return userAge;
//    }
//
//    public void setUserAge(Integer userAge) {
//        this.userAge = userAge;
//    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }


}
