package com.spring_stu.spring_PropertyEditor.customPropertyEditor;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class MyPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
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
        result.setProperty("eds.jdbc.user","WnplV/ietfQ=") ;
        result.setProperty("eds.jdbc.password","QAHlVoUc49w=") ;
        return result;
    }
}