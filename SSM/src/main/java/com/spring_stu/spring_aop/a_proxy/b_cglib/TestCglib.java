package com.spring_stu.spring_aop.a_proxy.b_cglib;

import com.spring_stu.service.serviceImpl.UserServiceImpl;
import org.junit.Test;

public class TestCglib {
	
	@Test
	public void demo01(){
		UserServiceImpl userService = MyBeanFactory.createService();
		userService.addUser();
		userService.updateUser();
		userService.deleteUser();
	}

}
