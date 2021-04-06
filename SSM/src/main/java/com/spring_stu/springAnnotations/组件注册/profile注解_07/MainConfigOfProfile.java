package com.spring_stu.springAnnotations.组件注册.profile注解_07;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.spring_stu.springAnnotations.组件注册.dsc.pojo.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 *TODO Profile：
 * 		Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能；
 *
 * 开发环境、测试环境、生产环境；
 * 数据源：(/A)(/B)(/C)；
 *
 *
 *TODO
 *  @Profile ：指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *  1）、加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
 *       @Profile + @Bean 联合使用，控制某个bean在哪个环境进行注册
 *  2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 *       @Profile + @Configuration 控制某个配置类是否生效
 *  3）、没有标注环境标识的bean在任何环境下都是加载的；
 *
 * TODO 环境激活：
 *    方式1：使用环境变量指定运行环境：   -Dspring.profiles.active=test
 *    方式2：使用代码的形式指定运行环境：
 *         AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
 *         //TODO 方式2：使用代码的形式指定运行环境： 可以同时激活多个环境
 *         applicationContext.getEnvironment().setActiveProfiles("test","dev");
 *         applicationContext.register(MainConfigOfProfile.class);
 *         applicationContext.refresh();
 */
//@Profile("test") //控制配置类是否有效
@PropertySource("classpath:com/spring_stu/springAnnotations/组件注册/profile注解_07/application.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {
    private StringValueResolver valueResolver;

    @Value("${db.user}")
    private String user;
    @Value("${db.jdbcUrl}")
    private String jdbcUrl ;

    private String  driverClass;
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        // TODO Auto-generated method stub
        this.valueResolver = resolver;
        driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }

    @Profile("test") //控制某个bean的创建
    @Bean
    public Yellow yellow(){
        return new Yellow();
    }

    //TODO 根据不同的环境，注册不同的数据源
    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}")String pwd) throws Exception{
        System.out.println("testDataSource: pwd = " + pwd);
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}")String pwd) throws Exception{
        System.out.println("dataSourceDev: pwd = " + pwd);
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }
    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}")String pwd) throws Exception{
        System.out.println("prodDataSource: pwd = " + pwd);
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl(jdbcUrl);

        dataSource.setDriverClass(driverClass);
        return dataSource;
    }





}
