package com.spring_stu.springAnnotations.属性赋值.environment_赋值_02;


import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;


@Data
public class Student implements ApplicationContextAware {

    //TODO 3. 可以从Environment中取出配置文件中的值(在运行环境变量里面的值)
    //TODO    需要使用 @PropertySource("classpath:属性配置文件路径") 先进行配置文件的加载。
    private String friends ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Environment environment = applicationContext.getEnvironment();
        String friends = environment.getProperty("stu.friends");
        this.friends = friends ;
    }
}
