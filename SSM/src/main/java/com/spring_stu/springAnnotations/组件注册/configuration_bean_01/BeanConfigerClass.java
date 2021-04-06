package com.spring_stu.springAnnotations.组件注册.configuration_bean_01;

import com.spring_stu.springAnnotations.组件注册.dsc.pojo.Person;
import com.spring_stu.springAnnotations.组件注册.dsc.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//配置类 == 配置文件
@Configuration //告诉spring这是一个配置文件， 配置类也会被加载到IOC中年
public class BeanConfigerClass {
    /**
     *  等价于 xml中的
     * 	<bean id ="user" class ="包路径.User">
     * 		<constructor-arg value="annotationType"/>
     * 	</bean>
     */
    //给容器中注册一个bean ; 类型为返回值的类型 ， id默认是用方法名作为id
    @Bean
    public User user(){
        return new User();
    }

    // id默认是用方法名作为id,但是也可以指定定 id名称
    @Bean("person")
    public Person person1(){
        return new Person();
    }
}
