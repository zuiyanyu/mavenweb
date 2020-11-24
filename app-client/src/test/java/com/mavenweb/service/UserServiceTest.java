package com.mavenweb.service;

import com.mavenweb.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest {

    @Resource
    LoginLogService loginLogService ;

    @Resource
    UserService userService;

    @Test
    public void hasMatchUserTest(){
        String userName ="zhangsan" ;
        String password="123" ;
        boolean b = userService.hasMatchUser(userName, password);
        System.out.println(b);
    }

    @Test
    public void findUserByUserName(){
        String userName ="zhangsan" ;
        List<User> userByUserName = userService.findUserByUserName(userName);
        System.out.println("用户信息："+userByUserName.size());
        for (User user : userByUserName) {
            System.out.println(user);
        }
    }

    @Test
    public void  loginSuccess(){
        User user = new User();
        user.setUserId(2);
        user.setUserName("zhangsan");
        user.setLastVist(new Date());
        user.setLastIp("127.0.0.1");
        user.setCredits(1);

        userService.loginSuccess(user);
    }




}
