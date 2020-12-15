package com.spring_stu.spring_aop.d_aspect.b_anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//替换beans.xml配置文件
@Configuration
@ComponentScan("com.spring_stu.spring_aop.d_aspect.b_anno")//替换<context:component-scan base-package="com.spring_stu.spring_aop.d_aspect.b_anno"/>
@EnableAspectJAutoProxy//替换<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
public class ConfigClass {

}
