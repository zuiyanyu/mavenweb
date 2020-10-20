package com.spring_stu.service.serviceImpl;

import com.spring_stu.service.StudentDao;
import org.springframework.stereotype.Repository;

@Repository("studentDaoId")
public class StudentDaoImpl implements StudentDao {

	@Override
	public void save() {
		System.out.println("dao");
	}

}
