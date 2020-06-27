package com.mavenweb.domain;

public class User {
    String userName ;
    Integer userAge ;
    String userNo ; //学号
    String password ;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userAge='" + userAge + '\'' +
                ", userNo='" + userNo + '\'' +
                ", password='" + password + '\'' +
                '}';
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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
