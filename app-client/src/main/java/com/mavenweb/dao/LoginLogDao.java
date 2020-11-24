package com.mavenweb.dao;

import com.mavenweb.domain.LoginLog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 负责记录用户的登录日志
 */
@Repository
public class LoginLogDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginlog){
        String insertSql = "INSERT INTO t_login_log(user_id,ip,login_datetime) values(?,?,?)" ;
        jdbcTemplate.update(insertSql,new Object[]{loginlog.getUserId(),loginlog.getIp(),loginlog.getLoginDate()});
    }
}
