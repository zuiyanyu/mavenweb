package com.spring_stu.service.controller;

import com.spring_stu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("studentActionId")
public class StudentActionController {
	
	@Autowired //默认按照类型
	private StudentService studentService;

	public void execute() {
		studentService.addStudent();
	}

}
