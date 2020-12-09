package com.spring_stu.spring_property_placeholder.sysConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用系统property属性配置类
 * 在XML配置文件中，我们先将SysConfig定义为一个Bean,定义数据源时即可通过#{bean.propName}的方式引用Bean的属性值了。
 */
@Component
public class SysConfig  {
    //连接数据的数据源,需要我们进行注入，用于从数据库查询系统其他动态配置项
    @Autowired
    private JdbcTemplate jdbcTemplate ;
    //用于保存从数据库中查询到的其他的配置项
    private  Map<String, Object> configs ;

    //1）初始化方法：模拟从数据库中获取配置值并设置相应的属性
    @PostConstruct
    public void initFromDB(){
        System.out.println("开始初始化配置类...");
        System.out.println("jdbcTemplate = "+jdbcTemplate);

        System.out.println("开始从数据库中查询配置项...");
        String sql = "select property_name,property_value from sys_config" ;
        List<Map<String, Object>> configList = jdbcTemplate.queryForList(sql);
        //模拟从数据库获取配置值
        System.out.println("从数据库中获取的配置项：=>"+configList);

        System.out.println("开始设置配置项...");
        Map<String, Object> configs = new HashMap<>();
        for (Map<String, Object> config : configList) {
            String property_name = (String)config.get("property_name");
            String property_value = (String)config.get("property_value");
            configs.put(property_name,property_value);
        }
        this.configs = configs ;
        System.out.println("初始化配置类完成！");
        System.out.println(configs);
    }
    // @Autowired  private JdbcTemplate jdbcTemplate ; 会调用此方法
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // 使用@Value("#{sysConfig.configs}") 的时候会调用此方法
    public Map<String, Object> getConfigs() {
        return configs;
    }
 }
