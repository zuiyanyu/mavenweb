package com.spring_stu.spring_baseFun.b_di;

import com.spring_stu.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;

public class TestDI {
	
	@Test
	public void demo01(){
		//从spring容器获得
		String xmlPath = "com/spring_stu/spring_baseFun/b_di/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		BookService bookService = (BookService) applicationContext.getBean("bookServiceId");
		
		bookService.addBook();
		
	}
	@Test
	public void demo02(){
		//使用BeanFactory  --第一次使用getBean实例化
		String xmlPath = "com/spring_stu/spring_baseFun/b_di/beans.xml";
		
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(xmlPath));
		
		BookService bookService = (BookService) beanFactory.getBean("bookServiceId");

		String[] beanDefinitionNames = ((XmlBeanFactory) beanFactory).getBeanDefinitionNames();
		System.out.println(Arrays.toString(beanDefinitionNames));
		bookService.addBook();
		
	}

}
