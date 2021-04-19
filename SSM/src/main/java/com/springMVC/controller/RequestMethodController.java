package com.springMVC.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
//处理器的通用映射前缀（父路径）：表示该处理器只处理匹配“/customers/**”的请求；
@RequestMapping("/customers/**") //1 处理器的通用映射前缀
public class RequestMethodController {

    //对类级别的@RequestMapping进行窄化，表示showForm可处理匹配“/customers/**/create”且请求方法为“GET”的请求；
    @RequestMapping(value="/create", method = RequestMethod.GET)//2 类级别的@RequestMapping窄化
    public String showForm() {
        System.out.println("===============GET");
        return "customer/create";
    }

    //对类级别的@RequestMapping进行窄化，表示submit可处理匹配“/customers/**/create”且请求方法为“POST”的请求。
    @RequestMapping(value="/create", method = RequestMethod.POST)//3 类级别的@RequestMapping窄化
    public String submit() {
        System.out.println("================POST");
        return "redirect:/success";
    }

    /**
     * 入参是 JSON串：{ "userName":"张三"}
     * 接收参数是Map。
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/map")
    public Map map(@RequestBody Map map){
        System.out.println("map = "+map);
        return map ;
    }

    /**map接收表单提交：需要满足以下条件
     * 1. map前面加上@RequestParam参数
     * 2. 表单的Content-Type 值为 application/x-www-form-urlencoded
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/map2",consumes = {"application/x-www-form-urlencoded"})
    public Map map2(@RequestParam Map map){
        System.out.println("map = "+map);
        return map ;
    }

    @RequestMapping(value = "/modelAndView",method=RequestMethod.GET)
    public ModelAndView modelAndView(String userName, ModelAndView modelAndView){
        System.out.println("userName"+userName);
        modelAndView.addObject("userName",userName);
        modelAndView.setViewName("success");

        return modelAndView ;
    }


}
