package com.spring_stu.spring_aop.a_proxy.a_jdk;

import com.spring_stu.service.UserService;
import org.junit.Test;

import java.sql.SQLException;

public class TestJDK {
	
	@Test
	public void demo01() throws SQLException {
		UserService userService = MyBeanFactory.createService();
		userService.addUser();
		userService.updateUser();
		userService.deleteUser();

//		TransactionAwareDataSourceProxy transactionAwareDataSourceProxy = new TransactionAwareDataSourceProxy();
//		DataSource targetDataSource = transactionAwareDataSourceProxy.getTargetDataSource();
//		Connection connection = transactionAwareDataSourceProxy.getConnection();
	}

}
