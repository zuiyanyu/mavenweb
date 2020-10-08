package com.spring_stu.spring_aop.c_spring_aop;

import com.spring_stu.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringAOP {
	
	@Test
	public void demo01(){
		String xmlPath = "com/spring_stu/spring_aop/c_spring_aop/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		//获得目标类
		UserService userService = (UserService) applicationContext.getBean("userServiceId");
		userService.addUser();
		userService.updateUser();
		userService.deleteUser();
	}

}
