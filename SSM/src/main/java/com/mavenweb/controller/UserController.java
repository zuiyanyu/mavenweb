package com.mavenweb.controller;

//import com.alibaba.dubbo.config.annotation.Reference;

import com.mavenweb.domain.User;
import com.mavenweb.service.UserService;
import com.mavenweb.utils.JsonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
@RestController
@RequestMapping("/userController")
public class UserController {
private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class) ;
    @Resource
    UserService userService ;

    @RequestMapping(value="/getUserName",method = RequestMethod.GET)
    public JsonResp getUserName(HttpServletResponse response, HttpServletRequest request,
                                @RequestParam("userNo") String userNo){
        LOGGER.info("====Comming into UserController.getUserName()====");
        LOGGER.info("入参为：userNo={}",userNo);
        String userName = "userName";
        userName = userService.getUserName(userNo) ;
        JsonResp sucess = JsonResp.sucess(userName);
        LOGGER.info("返回对象为：{}",sucess);
        return sucess;
    }
    @RequestMapping(value="/getUserByNo",method = RequestMethod.GET)
    public JsonResp getUserByNo(HttpServletResponse response, HttpServletRequest request,
                                @RequestParam("userNo") String userNo){
        LOGGER.info("====Comming into UserController.getUserName()====");
        LOGGER.info("入参为：userNo={}",userNo);
        String userName = "userName";
        User user  = userService.getUserByNo(userNo) ;
        JsonResp sucess = JsonResp.sucess(user);
        LOGGER.info("返回对象为：{}",sucess);
        return sucess;
    }
}
