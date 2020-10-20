package com.spring_stu.spring_baseFun.g_annotation.a_ioc;

import com.spring_stu.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnoIoC {
	
	@Test
	public void demo02(){
		//从spring容器获得
		String xmlPath = "com/spring_stu/spring_baseFun/g_annotation/a_ioc/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		UserService userService = (UserService) applicationContext.getBean("userServiceId");
		userService.addUser();
		
	}

}
