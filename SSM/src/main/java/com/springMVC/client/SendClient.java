package com.springMVC.client;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * 1. 常见媒体类型：
 * text/html ： HTML格式
 * text/plain ：纯文本格式
 * text/xml ：XML格式
 * image/gif ：gif图片格式
 * image/jpeg ：jpg图片格式
 * image/png：png图片格式
 * application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）
 * multipart/form-data ： 当你需要在表单中进行文件上传时，就需要使用该格式；
 * application/xhtml+xml ：XHTML格式
 * application/xml： XML数据格式
 * application/atom+xml ：Atom XML聚合格式 application/json： JSON数据格式
 * application/pdf：pdf格式
 * application/msword ： Word文档格式
 * application/octet-stream ： 二进制流数据（如常见的文件下载）。
 * application/json： JSON数据格式
 *
 * 2. Content-Type：请求头的内容类型，表示发送到服务器的内容数据的媒体类型；
 */
public class SendClient {
    @Test
    public void getXML() throws URISyntaxException, IOException{
        //请求的地址
        String url = "http://localhost:8080/ContentType/responseXML";

        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);

        //②设置客户端可接受的媒体类型（即需要什么类型的响应体数据）
        request.getHeaders().set("Accept", "application/xml");

        //③发送请求并得到响应
        ClientHttpResponse response = request.execute();

        //④得到响应体的编码方式
        Charset charset = response.getHeaders().getContentType().getCharset();

        //⑤得到响应体的内容
        InputStream is = response.getBody();
        byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
        is.read(bytes);
        String xmlData = new String(bytes, charset);
        System.out.println("charset : " + charset + ", xml data : " + xmlData);
    }
    @Test
    public void getJson() throws URISyntaxException, IOException {
        //请求的地址
        String url = "http://localhost:8080/ContentType/responseJSON";

        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);

        //②设置客户端可接受的媒体类型（即需要什么类型的响应体数据）
        request.getHeaders().set("Accept", "application/json");

        //③发送请求并得到响应
        ClientHttpResponse response = request.execute();

        //④得到响应体的编码方式
        Charset charset = response.getHeaders().getContentType().getCharset();

        //⑤得到响应体的内容
        InputStream is = response.getBody();
        byte bytes[] = new byte[(int)response.getHeaders().getContentLength()];
        is.read(bytes);

        //进行接收信息的转码输出
        //charset : UTF-8, json data : {"username":"zhang", "password":"123"}
        String jsonData = new String(bytes, charset);
        System.out.println("charset : " + charset + ", json data : " + jsonData);
    }
    //客户端发送json数据请求
    @Test
    public void sendJson2Server()throws URISyntaxException, IOException {
        //请求的地址
        String url = "http://localhost:8080/ContentType/ContentTypeJSON";
        //①创建Http Request(内部使用HttpURLConnection)
        ClientHttpRequest request =
                new SimpleClientHttpRequestFactory().
                        createRequest(new URI(url), HttpMethod.POST);

        //②设置请求头的内容类型头和内容编码（GBK）
        request.getHeaders().set("Content-Type", "application/json;charset=UTF-8");

        //③以GBK编码写出请求内容体
        String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
        request.getBody().write(jsonData.getBytes("UTF-8"));

        //④发送请求并得到响应
        ClientHttpResponse response = request.execute();
        System.out.println(response.getStatusCode());
    }
}
