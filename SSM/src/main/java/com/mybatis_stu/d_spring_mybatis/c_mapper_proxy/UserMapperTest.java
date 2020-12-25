package com.mybatis_stu.d_spring_mybatis.c_mapper_proxy;

import com.mybatis_stu.domain.User;
import com.mybatis_stu.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class UserMapperTest  {
    private ApplicationContext applicationContext;
    @Before
    public void setUp(){
        String xlmPath ="com/mybatis_stu/d_spring_mybatis/c_mapper_proxy/applicationContext.xml" ;
        applicationContext = new ClassPathXmlApplicationContext(xlmPath);
    }
    @Test
    public void findUserByIdTest() throws Exception{
        UserMapper userMapper = applicationContext.getBean("userMapper", UserMapper.class);
        User user = userMapper.findUserById(1);
        System.out.println(user);

        Map userRstMap = userMapper.findUserByIdRetMap(1);
        System.out.println(userRstMap);


    }

}
