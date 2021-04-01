package com.nacos.mtf.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在 bootstrap.properties 中配置 Nacos server 的地址和应用名 。
 *
 *
 * spring.cloud.nacos.config.server-addr=hadoop102:8848
 * # Nacos 控制台添加配置：
 * # Data ID：nacos-config.properties
 * # Group：DEFAULT_GROUP
 * # 配置内容：useLocalCache=true
 * spring.application.name=nacos-config
 * # 指定配置的后缀，支持 properties、yaml、yml，默认为 properties
 * spring.cloud.nacos.config.file-extension=properties
 * #spring.cloud.nacos.config.file-extension=yaml
 *

 *
 *
 *
 * 说明：之所以需要配置 spring.application.name ，是因为它是构成 Nacos 配置管理 dataId字段的一部分。
 *
 * 在 Nacos Spring Cloud 中，dataId 的完整格式如下：
 *
 * ${prefix}-${spring.profiles.active}.${file-extension}
 * prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
 * spring.profiles.active 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。 注意：当 spring.profiles.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension}
 * file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。
 */
@RestController
@RequestMapping("/config")
@RefreshScope //通过 Spring Cloud 原生注解 @RefreshScope 实现配置自动更新：
public class NacosConfigController {
    @Value("${useLocalCache:false}")
//    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    /**
     * http://localhost:8080/config/get
     */
    @RequestMapping("/get")
    public boolean get() {
        System.out.println("获取配置");
        return useLocalCache;
    }
}
