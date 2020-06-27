package com.mavenweb.service.impl;

//import com.alibaba.dubbo.config.annotation.Service;

import com.mavenweb.mapper.UserMapper;
import com.mavenweb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl   implements UserService {
    @Resource
    UserMapper userMapper ;

    @Override
    public String getUserName(String userNo) {
        String userName = userMapper.getUserNameByNo(userNo);
        return userName;
    }
}
