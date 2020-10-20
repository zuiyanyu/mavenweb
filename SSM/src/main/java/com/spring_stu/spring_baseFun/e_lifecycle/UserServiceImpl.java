package com.spring_stu.spring_baseFun.e_lifecycle;

import com.spring_stu.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("e_lifecycle add user");
	}

	public void myInit(){
		System.out.println("初始化");
	}
	public void myDestroy(){
		System.out.println("销毁");
	}





	@Override
	public String updateUser() {
		return null;
	}

	@Override
	public void deleteUser() {

	}
}
