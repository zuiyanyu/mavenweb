package com.springMVC.controller;

import com.springMVC.domain.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/MethdoParamType")
public class MethdoParamTypeController {
    //TODO =====================支持的参数类型-功能处理方法=====================
    public void inputOrOutBody(InputStream requestBodyIn, OutputStream responseBodyOut)
            throws IOException {
        responseBodyOut.write("success".getBytes());
    }

    public void readerOrWriteBody(Reader reader, Writer writer)
            throws IOException {
        writer.write("hello");
    }

    public String webRequest(WebRequest webRequest, NativeWebRequest nativeWebRequest) {
        System.out.println(webRequest.getParameter("test"));//①得到请求参数test的值
        webRequest.setAttribute("name", "value", WebRequest.SCOPE_REQUEST);//②
        System.out.println(webRequest.getAttribute("name", WebRequest.SCOPE_REQUEST));
        HttpServletRequest request =
                nativeWebRequest.getNativeRequest(HttpServletRequest.class);//③
        HttpServletResponse response =
                nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/commandObject", method = RequestMethod.GET)
    public UserModel toCreateUser(HttpServletRequest request, UserModel user) {
        System.out.println(user);
        return user ;
    }

    @ResponseBody
    @RequestMapping(value = "/commandObject", method = RequestMethod.POST)
    public UserModel createUser(HttpServletRequest request, UserModel user) {
        System.out.println(user);
        return user;
    }

    /**
     * ModelMap 继承了Map
     * @param model_1
     * @param model_2
     * @param model_3
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modelMap", method = RequestMethod.POST)
    public Map modelMap(Model model_1, Map model_2, ModelMap model_3) {
        //都是： org.springframework.validation.support.BindingAwareModelMap
        System.out.println("model_1 = "+ model_1.getClass().getName());
        System.out.println("model02 = "+ model_2.getClass().getName());
        System.out.println("model03 = "+ model_3.getClass().getName());

        //其实这三个参数是同一个 实例
        System.out.println(model_1 == model_2); //true
        System.out.println(model_2 == model_3); //true

        //同一实例的三个引用
        model_1.addAttribute("a", "a");
        model_2.put("b", "b");
        model_3.put("c", "c");
        System.out.println(model_1);

        //返回的类型不是 Model类型，@ResponseBody注解才会生效
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.putAll(model_2);

        return  retMap;
    }

    @RequestMapping(value = "/mergeModel")
    public ModelAndView mergeModel(Model model) {
        model.addAttribute("a", "a");//①添加模型数据
        ModelAndView mv = new ModelAndView("success");
        mv.addObject("a", "update");//②在视图渲染之前更新③处同名模型数据
        model.addAttribute("a", "new");//③修改①处同名模型数据
        //视图页面的a将显示为"update" 而不是"new"
        return mv;
    }


}
