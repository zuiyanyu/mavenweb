package com.mavenweb.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;

/**
 *  注意：必须提供属性对应的getter方法，@ResponseBody 才能通过getter方法拿到对应的字段值生成json返回；否则返回空串：{}
 */
@JsonIgnoreProperties
public class JsonResp implements Serializable {
    public static final String SUCCESS = "0" ;
    public static final String FAILUER = "500" ;
    public static final String URL_NOT_FOUND= "404" ;
    public static final String MESSAGE_OK= "ok" ;
    public static final String MESSAGE_ERROR= "error" ;
    private String status ;
    private String message ;
    private Object data ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private JsonResp() {};
    private JsonResp(String status, Object data){
        this.status  = status ;
        this.data =data ;
    }
    private JsonResp(String status, String message, Object data){
        this.status  = status ;
        this.data =data ;
        this.message = message ;
    }

    public static JsonResp sucess(){
        return new JsonResp("0","ok",new HashMap());
    }

    public static JsonResp sucess(Object data){
        return new JsonResp("0","ok",data);
    }
    public static JsonResp sucess(String message ,Object data ){
        return new JsonResp("0",message ,data);
    }


    public static JsonResp failure(){
        return new JsonResp("500","error",new HashMap());
    }

    public static JsonResp failure(String message){
        return new JsonResp("500",message,new HashMap());
    }
    public static JsonResp failure(String status, String message ,Object data ){
        return new JsonResp(status,message ,data);
    }
    public static JsonResp failure(String status, String message  ){
        return new JsonResp(status,message,new HashMap());
    }

    @Override
    public String toString() {
        return "JsonResp{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
