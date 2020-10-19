package com.mybatis_stu.d_spring_mybatis.b_mapper_proxy;

import com.mybatis_stu.domain.User;
import com.mybatis_stu.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest  {
    private ApplicationContext applicationContext;
    @Before
    public void setUp(){
        String xlmPath ="com/mybatis_stu/d_spring_mybatis/b_mapper_proxy/applicationContext.xml" ;
        applicationContext = new ClassPathXmlApplicationContext(xlmPath);
    }
    @Test
    public void findUserByIdTest() throws Exception{
       //  方式1：单个mapper代理类配置
        UserMapper userMapper = applicationContext.getBean("userMapperA", UserMapper.class);
        User user = userMapper.findUserById(1);
        System.out.println(user);

        //方式2：批量mapper代理类配置 默认bean的id为类名首字符小写
        userMapper = applicationContext.getBean("userMapper", UserMapper.class);
        user = userMapper.findUserById(1);
        System.out.println(user);
    }

}
