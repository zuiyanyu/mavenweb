package com.spring_stu.spring_aop.d_aspect.b_anno;

import com.spring_stu.service.UserService;
import org.springframework.stereotype.Service;

@Service("userServiceId")
public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("d_aspect.b_anno addUser");
	}

	@Override
	public String updateUser() {
		System.out.println("d_aspect.b_anno updateUser");
//		int i = 1/ 0;
		System.out.println("updateUser");
		return "updateUser() ";
	}

	@Override
	public void deleteUser() {
		
		System.out.println("d_aspect.b_anno deleteUser");
	}

}
