package com.mavenweb.controller;

//import com.alibaba.dubbo.config.annotation.Reference;

import com.mavenweb.Resp.JsonResp;
import com.mavenweb.domain.User;
import com.mavenweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/userController")
public class UserController {
private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class) ;
//    @Reference
    UserService userService ;
    @RequestMapping(value="/getUserName",method = RequestMethod.GET)
    @ResponseBody
    public JsonResp getUserName(HttpServletRequest request, HttpServletResponse response, @RequestParam("userAge") String userAge){
        LOGGER.info("====Comming into UserController.getUserName()====");
        LOGGER.info("入参为：userAge={}",userAge);
        //   String userName = userService.getUserName(userAge) ;
        JsonResp sucess = JsonResp.sucess(userAge);
        LOGGER.info("返回对象为：{}",sucess);
        return sucess;
    }











    @RequestMapping(value="/getUserName5",method = RequestMethod.GET)
    @ResponseBody
    public JsonResp getUserName5(@RequestParam("userAge") String userAge){
        LOGGER.info("====Comming into UserController.getUserName()====");
        LOGGER.info("入参为：userAge={}",userAge);

        return JsonResp.sucess();
    }

    @RequestMapping(value="/getUserName4",method = RequestMethod.GET)
    @ResponseBody
    public User getUserName4(@RequestParam("userAge") String userAge){
        LOGGER.info("====Comming into UserController.getUserName()====");
        LOGGER.info("入参为：userAge={}",userAge);

        return new User();
    }


    @RequestMapping(value="/getUserName2",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> getUserName2(@RequestParam(value="userAge",required = false) String userAge){
        LOGGER.info("====Comming into UserController.getUserName()====");
        LOGGER.info("入参为：userAge={}",userAge);
        List<String> list = new ArrayList<String>(Arrays.asList("demo","test","hehei"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "demo");
        map.put("2", "test");
        List res = new ArrayList() ;
        res.add(map);
        return map;
    }
    @RequestMapping(value="/getUserName3",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String ,Object>> getUserName3(@RequestParam(value="userAge",required = false) String userAge){
        LOGGER.info("====Comming into UserController.getUserName()====");
        LOGGER.info("入参为：userAge={}",userAge);
        List<String> list = new ArrayList<String>(Arrays.asList("demo","test","hehei"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "demo");
        map.put("2", "test");
        List res = new ArrayList() ;
        res.add(map);
        return res;
    }
}
