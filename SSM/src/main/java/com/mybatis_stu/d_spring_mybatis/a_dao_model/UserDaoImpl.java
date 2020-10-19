package com.mybatis_stu.d_spring_mybatis.a_dao_model;

import com.mybatis_stu.domain.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl  extends SqlSessionDaoSupport implements UserDao {
    @Override
    public User findUserById(int id) throws Exception {
        return this.getSqlSession().selectOne("test.findUserById", 1);
    }
}
