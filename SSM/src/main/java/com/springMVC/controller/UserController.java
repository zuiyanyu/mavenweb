package com.springMVC.controller;

import com.springMVC.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/springmvc")
public class UserController {
    @RequestMapping(value = "/hello") //②请求URL到处理器功能处理方法的映射
    public ModelAndView helloWorld() {
        //1、收集参数
        //2、绑定参数到命令对象
        //3、调用业务对象
        //4、选择下一个页面
        ModelAndView mv = new ModelAndView();
        //添加模型数据 可以是任意的POJO对象
        mv.addObject("message", "Hello World!");
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("success");
        return mv; //○3 模型数据和逻辑视图名
    }


    @GetMapping(value = "user")
    @ResponseBody
    public User getUserInfo(@RequestParam("userName") String userName){
        User user = new User();
        user.setUserName(userName);
        user.setAge(20);
        return user ;
    }

    @GetMapping(value = "user2")
    @ResponseBody
    public User getUserInfo2( Map<String,String> map){
        System.out.println(map);
        User user = new User();
        user.setUserName("");
        user.setAge(20);
        return user ;
    }



}
