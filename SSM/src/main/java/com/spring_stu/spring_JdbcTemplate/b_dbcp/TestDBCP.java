package com.spring_stu.spring_JdbcTemplate.b_dbcp;

import com.spring_stu.dao.UserDao;
import com.spring_stu.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDBCP {
	
	@Test
	public void demo01(){
		User user = new User();
		user.setId(1);
		user.setUsername("ceshi");
		user.setPassword("112234");
		
		String xmlPath = "com/spring_stu/spring_JdbcTemplate/b_dbcp/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		//获得目标类
		UserDao userDao = (UserDao) applicationContext.getBean("userDaoId");
		userDao.update(user);
		System.out.println("ok");
	}

}
