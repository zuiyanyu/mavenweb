package com.spring_stu.spring_aop.b_factory_bean;

import com.spring_stu.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 方式2 ：半自动实现aop功能
 */
public class TestFactoryBean {
	
	@Test
	public void demo01(){
		String xmlPath = "com/spring_stu/spring_aop/b_factory_bean/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		//获得代理类
		UserService userService = (UserService) applicationContext.getBean("proxyServiceId");
		userService.addUser();
		userService.updateUser();
		userService.deleteUser();
	}

}
