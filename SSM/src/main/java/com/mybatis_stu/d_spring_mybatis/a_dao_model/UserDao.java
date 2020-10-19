package com.mybatis_stu.d_spring_mybatis.a_dao_model;

import com.mybatis_stu.domain.User;

public interface UserDao   {
    // 1、 根据用户ID查询用户信息
    public User findUserById(int id) throws Exception;
}
