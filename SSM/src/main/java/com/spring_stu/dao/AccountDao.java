package com.spring_stu.dao;


/**
 * create table account(
 *   id int primary key auto_increment,
 *   username varchar(50),
 *   money int
 * );
 *
 * insert into account(username,money) values('jack','10000');
 * insert into account(username,money) values('rose','10000');
 */
public interface AccountDao {
	/**
	 * 汇款
	 * @param outer
	 * @param money
	 */
	public void out(String outer, Integer money);
	
	/**
	 * 收款
	 * @param inner
	 * @param money
	 */
	public void in(String inner, Integer money);

}
