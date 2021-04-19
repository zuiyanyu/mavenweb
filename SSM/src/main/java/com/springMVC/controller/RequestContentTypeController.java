package com.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/ContentType")
public class RequestContentTypeController {

    /**
     * request1功能处理方法：只对请求头为“Content-Type:application/x-www-form-urlencoded”的请求进行处理
     * （即消费请求内容区数据）;
     * application/x-www-form-urlencoded ：
     *    <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）
     * @param request   ,headers = "Content-Type=application/x-www-form-urlencoded"
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ContentTypeKV", method = {RequestMethod.POST}
          ,headers = "Content-Type=application/x-www-form-urlencoded" )
    public String request1(HttpServletRequest request) throws IOException {
        //①得到请求的内容区数据的类型
        String contentType = request.getContentType();
        System.out.println("========ContentType:" + contentType);

        //②得到请求的内容区数据的编码方式，如果请求中没有指定则为null
        //注意，我们的CharacterEncodingFilter这个过滤器设置了编码(UTF-8)
        //编码只能被指定一次，即如果客户端设置了编码，则过滤器不会再设置
        String characterEncoding = request.getCharacterEncoding();
        System.out.println("========CharacterEncoding:" + characterEncoding);

        //③表示请求的内容区数据为form表单提交的参数，此时我们可以通过request.getParameter得到数据（key=value）
        System.out.println(request.getParameter("realname"));
        System.out.println(request.getParameter("username"));
        return "success";
    }

    /**
     * 服务器端可以通过指定【headers = "Content-Type=application/json"】来声明可处理（可消费）的媒体类型，即只消
     * 费Content-Type指定的请求内容体数据；
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ContentTypeJSON", method = RequestMethod.POST
            , headers = {"Content-Type=application/json","Accept=application/json"})
    public String acceptJSON(HttpServletRequest request) throws IOException {
        System.out.println("进入方法");

        //①表示请求的内容区数据为json数据
        InputStream is = request.getInputStream();
        byte bytes[] = new byte[request.getContentLength()];
        is.read(bytes);

        //②得到请求中的内容区数据（以CharacterEncoding解码）
        //此处得到数据后你可以通过如json-lib转换为其他对象
        String jsonStr = new String(bytes, request.getCharacterEncoding());
        System.out.println("json data：" + jsonStr);
        return "success";
    }

    /**
     * Accept：用来指定什么媒体类型的响应是可接受的，即告诉服务器我需要什么媒体类型的数据，此时服务器应该
     * 根据Accept请求头生产指定媒体类型的数据。
     * 服务器根据请求头“Accept=application/json”生产json数据。
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/responseJSON", headers = "Accept=application/json")
    public void responseJSON(HttpServletResponse response) throws IOException {
        //①表示响应的内容区数据的媒体类型为json格式，且编码为utf-8(客户端应该以utf-8解码)
        response.setContentType("application/json;charset=utf-8");
        //②写出响应体内容
        String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
        response.getWriter().write(jsonData);
    }

    /**
     * 和生产json数据唯一不同的两点：请求头为“Accept=application/xml”，响应体数据为xml。
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/responseXML", headers = "Accept=application/xml")
    public void response3(HttpServletResponse response) throws IOException {

        //①表示响应的内容区数据的媒体类型为xml格式，且编码为utf-8(客户端应该以utf-8解码)
        response.setContentType("application/xml;charset=utf-8");

        //②写出响应体内容
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xmlData += "<user><username>zhang</username><password>123</password></user>";

        response.getWriter().write(xmlData);
    }
}
