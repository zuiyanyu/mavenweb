package com.spring_stu.spring_property_placeholder;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

public class MyPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {
    @Override
    protected Properties mergeProperties() throws IOException {
        //加载父类的配置信息项
        /*<context:property-placeholder location="classpath:jdbcInfo.properties"/>*/
        Properties result = super.mergeProperties();
        result.forEach((k,v) -> System.out.println(k+"->"+v));

        //com.mchange.v2.c3p0.ComboPooledDataSource 的简单配置项
        result.setProperty("c3p0.driverClass","com.mysql.jdbc.Driver") ;
        result.setProperty("c3p0.jdbcUrl","jdbc:mysql://localhost:3306/mavenweb") ;
        result.setProperty("c3p0.user","root") ;
        result.setProperty("c3p0.password","123456") ;
        return result;
    }
}
