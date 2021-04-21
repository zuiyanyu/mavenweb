package com.spring_stu.spring_PropertyEditor.string2enum.controller;

import com.spring_stu.spring_PropertyEditor.string2enum.domain.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/test/index?status=1&gender=男
 * {"username":null,"password":null,"gender2":null,"gender":"MALE","status":"ON"}
 */
@RestController
@RequestMapping("test")
public class EnumController {
    @GetMapping("/index")
    public UserEntity test(UserEntity user) {
        return user;
    }
}
