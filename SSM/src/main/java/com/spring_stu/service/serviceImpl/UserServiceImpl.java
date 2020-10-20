package com.spring_stu.service.serviceImpl;

import com.spring_stu.service.UserService;
import org.springframework.stereotype.Component;

@Component("userServiceId")
public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("addUser");
	}

	@Override
	public String updateUser() {
		System.out.println("updateUser");
		return "updateUser() ";
	}

	@Override
	public void deleteUser() {

		System.out.println("deleteUser");
	}

}
