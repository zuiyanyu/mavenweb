package com.mavenweb.controller;

import com.mavenweb.utils.JsonResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * httpServlet测试
 * 使用示例 1 - 服务启动时动态的加入 ServletContext 内容
 * 1、StartInitListener 类实现 servletContextListerner 接口，将要共享的值通过传入的ServletContextEvent 的 getServletContext()
 * 方法得到的 ServletContext，运行ServletContextEvent.setAttribute(key,value) 设置在内存中 ；
 *
 * 2、在项目的 \WEB-INF\web.xml 中配置监听器：
 * <listener>
 *    <listener-class>com.testCompany.framework.StartInitListener</listener-class>
 * </listener>

 * 3、项目通过ServletContext sct=getServletConfig().getServletContext(); sct.getAttribute(key) 将数据取到 。
 *
 */
@RestController
@RequestMapping("httpServlet/")
public class HttpServletController {
    /**
     * ServletContext是一个web应用的上下文，是一个全局信息的存储空间，代表当前web应用。
     * 其在web应用（服务器）启动时创建，在Web应用（服务器）关闭时释放，即Application生命周期。
     * @return
     */
    @RequestMapping(value = "getServletContextAttribute",method = RequestMethod.GET)
    public JsonResp getServletContextAttribute(HttpServletRequest request, HttpServletResponse response){
        Map map = new HashMap<>() ;

        String contextPath = request.getContextPath();
        map.put("request.getContextPath()",contextPath);


        //无论创建几个 session,但是始终只有一个ServletContext
        String key = "mtf";
        HttpSession session = request.getSession(true);
        ServletContext newServletContext = session.getServletContext();
        map.put("newServletContext.getAttribute(key)",newServletContext.getAttribute(key));


        HttpSession oldSession = request.getSession(false);
        ServletContext servletContext = oldSession.getServletContext();
        map.put("servletContext.getAttribute()",servletContext.getAttribute(key));

        //{"status":"0","message":"ok",
        // "data":{
        // "newServletContext.getAttribute(key)":"matengfei",
        // "servletContext.getAttribute()":"matengfei",
        // "request.getContextPath()":""}
        // }
        Cookie[] cookies = request.getCookies();
         System.out.println("cookies="+cookies);
        for (Cookie cookie : cookies) {
            String value = cookie.getValue();
            System.out.println("value="+value);
        }
         String sessionId = session.getId();
        System.out.println("sessionId="+sessionId);

//        response.setContentType("application/json");
//        try {
//            response.sendError(1,"错误编码！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        return JsonResp.sucess(map);
    }

    @RequestMapping(value = "sendRedirect",method = RequestMethod.GET)
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("getServletContextAttribute");
    }
    @RequestMapping(value = "responseWrite",method = RequestMethod.GET)
    public void responseWrite(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userName = "userName" ;
        try{
            userName = request.getParameter("name");
        }catch (Exception e ){}
        System.out.println("userName="+userName);

        writer.println("Hello World! Your name is: "+userName );

    }

}
