package com.spring_stu.spring_baseFun.f_xml.b_setter;

import com.spring_stu.domain.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSetter {
	
	@Test
	public void demo01(){
		//从spring容器获得
		String xmlPath = "com/spring_stu/spring_baseFun/f_xml/b_setter/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		Person person = (Person) applicationContext.getBean("personId");
		
		System.out.println(person);
		
	}

}
