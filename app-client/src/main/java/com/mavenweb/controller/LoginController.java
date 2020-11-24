package com.mavenweb.controller;

import com.mavenweb.domain.User;
import com.mavenweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 1. 通过Spring MVC的@Controller注解可以将任何一个POJO的类标注为Spring MVC的控制器，处理HTTP的请求。
 * 2. 标注了@Controller的类首先会是一个Bean,所以我们可以使用@Autowired进行Bean的注入。
 *
 * 3. 一个控制器可以拥有多个处理映射不同HTTP请求路径的方法，通过@RequestMapping指定方法如何映射请求路径。
 * 4. 请求的参数会根据参数名默认契约自动绑定到响应方法的入参中。
 * 5. 请求响应方法可以返回一个ModelAndView，或者直接返回一个字符串，Spring MVC会解析它并转向目标响应页面。
 * 6. ModelAndView 对象既包括了视图信息，又包括了视图渲染所需要的模型数据信息。这里我们只需要了解它代表一个视图即可。
 *    后面我们学习如何根据这个对象转向真正的页面。
 *
 */
@Controller
//@RequestMapping("login")
public class LoginController {

    @Resource
    private UserService userService ;

    //负责处理/index.html的请求,跳转到 登录页面  /WEB-INF/jsp/login.jsp
    @RequestMapping("/index.html")
    public String loginPage(){
        return "login";
    }

    /**
     * 负责处理/loginCheck.html的请求
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, User user){
        boolean isValidUser = userService.hasMatchUser(user.getUserName(), user.getPassword());
        if(!isValidUser){
            //ModelAndView 对象既包括了视图信息，又包括了视图渲染所需要的模型数据信息。
            /**
             * ModelAndView：参数1：视图的逻辑名。 参数2：数据模型名 参数3：数据模型对象。
             * 数据模型对象将以数据模型名称为参数放到request的属性中。
             * 视图解析器会将 视图逻辑名 解析为具体的视图页面。
             */
            return new ModelAndView("login","error","用户名或密码错误");
        }
        else {
            List<User> users = userService.findUserByUserName(user.getUserName());
            user = users.get(0);
            System.out.println(users.get(0));
            user.setLastIp(request.getLocalAddr());
            user.setLastVist(new Date());

            userService.loginSuccess(user);
            request.getSession().setAttribute("loginUser",user);
            System.out.println(user);

            return new ModelAndView("main") ;
        }
    }


















}
