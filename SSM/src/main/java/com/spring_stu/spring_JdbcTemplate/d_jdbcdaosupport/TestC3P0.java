package com.spring_stu.spring_JdbcTemplate.d_jdbcdaosupport;

import com.spring_stu.dao.UserDao2;
import com.spring_stu.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestC3P0 {
	
	@Test
	public void demo01(){
		
		String xmlPath = "com/spring_stu/spring_JdbcTemplate/d_jdbcdaosupport/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		//获得目标类
		UserDao2 userDao = (UserDao2) applicationContext.getBean("userDaoId");
		List<User> allUser = userDao.findAll();
		for (User user : allUser) {
			System.out.println(user);
		}
	}

}
