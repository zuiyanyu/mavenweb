package com.spring_stu.spring_aop.a_proxy.a_jdk;

import com.spring_stu.service.UserService;
import org.junit.Test;

public class TestJDK {
	
	@Test
	public void demo01(){
		UserService userService = MyBeanFactory.createService();
		userService.addUser();
		userService.updateUser();
		userService.deleteUser();
	}

}
