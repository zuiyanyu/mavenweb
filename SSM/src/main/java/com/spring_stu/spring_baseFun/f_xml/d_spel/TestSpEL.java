package com.spring_stu.spring_baseFun.f_xml.d_spel;

import com.spring_stu.domain.Customer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpEL {
	
	@Test
	public void demo02() throws Exception{
		//spring 工厂
		String xmlPath = "com/spring_stu/spring_baseFun/f_xml/d_spel/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		Customer customer = (Customer) applicationContext.getBean("customerId");
		System.out.println(customer);
	}

}
