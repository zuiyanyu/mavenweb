package com.spring_stu.dao;

import com.spring_stu.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class UserDao2   extends JdbcDaoSupport {

    public void update(User user){
        String sql = "update t_user set username=?,password=? where id =?";
        Object[] args = {user.getUsername(),user.getPassword(),user.getId()};
        this.getJdbcTemplate().update(sql, args);
    }

    /**
     * 查询所有
     * @return
     */
    public List<User> findAll() {
        return this.getJdbcTemplate().query("select * from t_user", BeanPropertyRowMapper.newInstance(User.class));
    }

}
