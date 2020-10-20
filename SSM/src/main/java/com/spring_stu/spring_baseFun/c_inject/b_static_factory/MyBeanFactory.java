package com.spring_stu.spring_baseFun.c_inject.b_static_factory;

import com.spring_stu.service.UserService;
import com.spring_stu.service.serviceImpl.UserServiceImpl;

public class MyBeanFactory {
	
	/**
	 * 创建实例
	 * @return
	 */
	public static UserService createService(){
		return new UserServiceImpl();
	}

}
