package com.spring_stu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public interface AccountService {
	
	/**
	 * 转账
	 * @param outer
	 * @param inner
	 * @param money
	 */
	public void transfer(String outer, String inner, Integer money);

    @Service
    class StudentServiceImpl implements StudentService {

        private StudentDao studentDao;

        @Autowired
        @Qualifier("studentDaoId")
        public void setStudentDao(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        public void addStudent() {
            studentDao.save();
        }

    }
}
