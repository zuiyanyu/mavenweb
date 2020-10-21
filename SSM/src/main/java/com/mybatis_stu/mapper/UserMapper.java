package com.mybatis_stu.mapper;

import com.mybatis_stu.domain.User;
import com.mybatis_stu.domain.vo.UserQueryVO;
import com.mybatis_stu.domain.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	// 1、 根据用户ID查询用户信息
	public User findUserById(int id) throws Exception;
	public Map findUserByIdRetMap(int id) throws Exception;

	// 2、 先添加用户，然后获取生成的主键
	public void getIdAfterInsert_type1(User user) throws Exception;
	public void getIdAfterInsert_type2(User user) throws Exception;

   //3.先生成主键key, 然后添加用户(使用新生成的key作为主键)
	public void getIdBeforeInsert(User user) throws Exception;

	//4.综合查询
	public List<User> findUserList(UserQueryVO vo);

	//5.resultMap入门
	public User findUserRstMap(int id);

	//6.查询用户，并查询用户的订单信息  一对多的关系：一个用户有多个订单
	List<UserVO> getUserWithOrders();



	/**
	 * 7 .多对多的映射
	 * 主信息：user
	 * 从信息：items、orders、orderdetail
	 * @return
	 */
	List<com.mybatis_stu.pojo.User> findUserWithItems();
}
