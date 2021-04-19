package com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.dao;

import com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public User getUserById(Integer id ){
        return new User(id);
    }
}
