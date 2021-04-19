package com.springMVC.jdk.BeanWrapperTest;

import lombok.Data;
import scala.actors.threadpool.Arrays;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Company {
    private Date dateTime  ;
    private String name;
    private Employee managingDirector;
    private List<String> nameList = Arrays.asList(new String[]{"zhangsan","lisi","wangwu"});
    private Map<String,String> map ;

    {
        map = new HashMap<>() ;
        map.put("a","A");
        map.put("b","B");
    }
}
