package com.spring_stu.spring_baseFun.g_annotation.c_other;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service("userServiceId")
//@Scope("prototype")
public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("d_scope add user");
	}


	//初始化方法
	@PostConstruct
	public void myInit(){
		System.out.println("初始化");
	}
	@PreDestroy
	public void myDestroy(){
		System.out.println("销毁");
	}

}
