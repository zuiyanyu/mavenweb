package com.spring_stu.dao;

import com.spring_stu.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class UserDao  {
	
	//jdbc模板将由spring注入
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void update(User user){
		String sql = "update t_user set username=?,password=? where id =?";
		Object[] args = {user.getUsername(),user.getPassword(),user.getId()};
		jdbcTemplate.update(sql, args);
	}
	/**
	 * 查询所有
	 * spring 4.x 使用BeanPropertyRowMapper
	 * spring 3.x 使用ParameterizedBeanPropertyRowMapper
	 * @return
	 */
	public List<User> findAll() {
		return jdbcTemplate.query("select * from t_user", BeanPropertyRowMapper.newInstance(User.class));
	}
}
