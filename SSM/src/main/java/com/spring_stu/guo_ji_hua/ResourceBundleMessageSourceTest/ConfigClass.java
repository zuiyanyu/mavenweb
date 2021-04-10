package com.spring_stu.guo_ji_hua.ResourceBundleMessageSourceTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan("com.spring_stu.guo_ji_hua.ResourceBundleMessageSourceTest")
public class ConfigClass {
    /**
     * 如果没有自定义 messageSource 这个bean id 名的MessageSource类，
     * 那么底层会默认使用DelegatingMessageSource类的实例来作为容器中的messageSource，
     * @return
     */
    @Bean
    public ResourceBundleMessageSource messageSource(){
        String baseName = "fmg_resource" ;
        //对接收的信息进行资源绑定
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        //通过基名指定资源，相对于类根路径
        resourceBundleMessageSource.setBasename("com/spring_stu/guo_ji_hua/i18n/"+baseName);
        return resourceBundleMessageSource ;
    }

    /**
     * 与ResourceBundleMessageSource的唯一区别在于它可以定时刷新资源文件，以便在应用系统不重启的情况下感知资源文件的变化。
     * 很多生产系统需要长时间持续运行，系统重启会给运行带来很大影响。
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource reloadableMessageSource  (){
        ReloadableResourceBundleMessageSource reloadableMessageSource = new ReloadableResourceBundleMessageSource();
        //通过基名指定资源，相对于类根路径
        reloadableMessageSource.setBasename("com/spring_stu/guo_ji_hua/i18n/fmg_resource");
        //设置五秒钟刷新一次
        reloadableMessageSource.setCacheSeconds(5);
        return reloadableMessageSource ;
    }


}
