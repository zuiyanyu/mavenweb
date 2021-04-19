package com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.controller;

import com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.pojo.User;
import com.java.servlet3_0.ServletContainerInitializer.spring_springMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping("queryById")
    @ResponseBody
    public User queryUser(@RequestParam("userId") Integer id){
        User user = userService.queryUser(id);
        System.out.println("查询的结果："+user);
        return user ;
    }
}
