package com.spring_stu.spring_tx.b_手动事务管理;

import com.spring_stu.dao.AccountDao;
import com.spring_stu.service.AccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	//需要spring注入事务模板
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void transfer(String outer, String inner, Integer money) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				System.out.println("transactionStatus.hasSavepoint()->"+transactionStatus.hasSavepoint());
				System.out.println("transactionStatus.isCompleted()->"+transactionStatus.isCompleted());
				System.out.println("transactionStatus.isNewTransaction()->"+transactionStatus.isNewTransaction());
				System.out.println("transactionStatus.isRollbackOnly()->"+transactionStatus.isRollbackOnly());
				accountDao.out(outer, money);
				//断电
				//int i = 1/0;
				accountDao.in(inner, money);
				System.out.println("transactionStatus.isCompleted()->"+transactionStatus.isCompleted());
			}
		}) ;
	}
}
