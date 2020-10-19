package com.mybatis_stu.d_spring_mybatis.a_dao_model;

import com.mybatis_stu.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTest {
    private ApplicationContext applicationContext;
    @Before
    public void setUp(){
        String xlmPath ="com/mybatis_stu/d_spring_mybatis/a_dao_model/applicationContext.xml" ;
        applicationContext = new ClassPathXmlApplicationContext(xlmPath);
    }
    @Test
    public void findUserByIdTest() throws Exception{
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        User user = userDao.findUserById(1);
        System.out.println(user);
    }
}
