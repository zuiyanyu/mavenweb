package com.spring_stu.spring_junit;

import com.spring_stu.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(value = "classpath:com/spring_stu/spring_junit/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestApp {

	/**
	 * spring与Junit整合的时候
	 */
	@Autowired  //与junit整合，不需要在spring xml配置扫描
	private AccountService accountService;
	@Test
	public void demo02(){
//		String xmlPath = "com/spring_stu/spring_junit/applicationContext.xml";
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
//		AccountService accountService =  (AccountService) applicationContext.getBean("accountService");
		accountService.transfer("jack", "rose", 1000);
	}

	/**
	 * 不与Junit整合的时候
	 */
	@Test
	public void demo01(){
		String xmlPath = "com/spring_stu/spring_junit/applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		AccountService accountService =  (AccountService) applicationContext.getBean("accountService");
		accountService.transfer("jack", "rose", 1000);
	}

}
