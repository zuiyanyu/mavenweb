package com.spring_stu.spring_property_placeholder.sysConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 从数据库中获取要注入的配置信息
 */
@Component
public class SessionClass {
    //引用另一个Bean的属性值，获取数据库中查询的数据配置项
    @Value("#{sysConfig.configs}")
    private  Map<String, Object> configs ;

    //从数据库中获取属性值
    private int sessionTimeout ;//会话超时时间
    private int maxTabPageNum ;

    @PostConstruct
    public void initFromDB(){
         //模拟从数据库获取配置值
        System.out.println("SessionClass 开始初始化配置项。。。");
        String sessionTimeout = (String)configs.get("sessionTimeout");
        String maxTabPageNum = (String)configs.get("maxTabPageNum");
        this.sessionTimeout = Integer.parseInt(sessionTimeout);
        this.maxTabPageNum = Integer.parseInt(maxTabPageNum);
        System.out.println("从数据库中获取的配置项：this.sessionTimeout =>"+this.sessionTimeout
                +"\nthis.maxTabPageNum=>"+this.maxTabPageNum);
    }

    @Override
    public String toString() {
        return "SessionClass{" +
                "sessionTimeout=" + sessionTimeout +
                ", maxTabPageNum=" + maxTabPageNum +
                '}';
    }
}
