package com.spring_stu.spring_property_placeholder.sysConfig;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.spring_stu.spring_property_placeholder.sysConfig")
@PropertySource(value = "classpath:jdbcInfo.properties" ,encoding = "UTF-8")
 public class ConfigClass {
    //从配置文件中获取数据库的配置信息
    @Value("${jdbc.jdbcUrl}")
    protected String url = null;
    @Value("${jdbc.driverClass}")
    protected String driverClassName = null;
    @Value("${jdbc.user}")
    protected String username = null;
    @Value("${jdbc.password}")
    protected volatile String password = null;

    //设置数据源
    @Bean
    public JdbcTemplate dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setUsername(username);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        return jdbcTemplate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
