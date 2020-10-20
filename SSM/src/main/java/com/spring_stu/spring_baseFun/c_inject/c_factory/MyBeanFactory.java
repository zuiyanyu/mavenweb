package com.spring_stu.spring_baseFun.c_inject.c_factory;

import com.spring_stu.service.UserService;
import com.spring_stu.service.serviceImpl.UserServiceImpl;

/**
 * 实例工厂,所有方法非静态
 *
 */
public class MyBeanFactory {
	
	/**
	 * 创建实例
	 * @return
	 */
	public UserService createService(){
		return new UserServiceImpl();
	}

}
