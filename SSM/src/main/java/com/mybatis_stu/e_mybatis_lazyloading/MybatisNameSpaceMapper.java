package com.mybatis_stu.e_mybatis_lazyloading;

import com.mybatis_stu.domain.vo.OrdersVO;
import com.mybatis_stu.mapper.OrdersMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisNameSpaceMapper {

	@Test
	public void findUserByIdTest() throws Exception{
		//读取配置文件
		//全局配置文件的路径
		String resource = "com/mybatis_stu/e_mybatis_lazyloading/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用SqlSession的增删改查方法
		//第一个参数：表示statement的唯一标识   命名空间.statement的唯一标识
		// 后面:  类全限定名 作为命名空间  ； 类方法作为 statement的唯一标识 ，比如： com.mybatis_stu.domain.User.findUserById
		//User user = sqlSession.selectOne("test.findUserById", 1);

		//根据 userMapper 和 userMapper.xml来进行查询
		// UserMapper.xml中的namespace 要修改为接口的全限定名：com.mybatis_stu.mapper.UserMapper , id 为借口的方法名
		// 等价于  sqlSession.selectOne("com.mybatis_stu.mapper.UserMapper"+"."+"findUserById", 1);
		OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
		List<OrdersVO> ordersWithUser2 = ordersMapper.getOrdersWithUser2(1);
		System.out.println(ordersWithUser2);
		
		//关闭资源
		sqlSession.close();
	}
	
	/*@Test
	public void findUsersByNameTest() throws Exception{
		//读取配置文件
		//全局配置文件的路径
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用SqlSession的增删改查方法
		//第一个参数：表示statement的唯一标示
		List<User> list = sqlSession.selectOne("test.findUsersByName", "小明");
		System.out.println(list);
		//关闭资源
		sqlSession.close();
	}
	
	@Test
	public void insertUserTest() throws Exception{
		//读取配置文件
		//全局配置文件的路径
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		User user = new User();
		user.setUsername("李四");
		user.setPassword("123456");
		//调用SqlSession的增删改查方法
		//第一个参数：表示statement的唯一标示
		sqlSession.insert("test.insertUser", user);
		
		System.out.println(user.getId());
		//提交事务
		sqlSession.commit();
		//关闭资源
		sqlSession.close();
	}*/
	
//	public void add(User user){
//		user.setId(2);
//	}
//	public void main(){
//		int a = 1;
//		User user = new User();
//		user.setId(1);
//		add(user);
//		System.out.println(user.getId());
//	}
	
}
