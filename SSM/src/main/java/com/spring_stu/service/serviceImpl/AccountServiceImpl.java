package com.spring_stu.service.serviceImpl;

import com.spring_stu.dao.AccountDao;
import com.spring_stu.service.AccountService;

public class AccountServiceImpl implements AccountService {

	//需要spring注入
	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String outer, String inner, Integer money) {
		accountDao.out(outer, money);
		//断电
		//int i = 1/0;
		accountDao.in(inner, money);
		System.out.println("转账成功");
	}

}
