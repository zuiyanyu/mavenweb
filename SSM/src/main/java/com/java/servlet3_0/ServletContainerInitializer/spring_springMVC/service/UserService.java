package com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.service;

import com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.dao.UserDao;
import com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao ;

    public User queryUser(int id ){
       return userDao.getUserById(id) ;
    }
}
