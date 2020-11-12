package com.spring_stu.springAnnotations.annotation_import;

import com.spring_stu.dao.BookDao;
import com.spring_stu.dao.daoImpl.BookDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 加载顺序为：
 * 1. 先注入 @Component 注解的类(bean name 是类名首字母小写)；
 * 2. 然后注入 @Import 注解中设置的类。(bean name 是全类名)
 * 3. 然后注入 选择器ImportSelector 返回的类集合。(bean name 是全类名) (此时ApplicationContext还没准备好)
 * 4. 然后注入@Bean注解要注入的类。(bean name 是方法名bookDao) (此时ApplicationContext已经准备好)
 *
 * com.spring_stu.springAnnotations.annotation_import.TestDemo2
 * bookDao
 * testDemo
 */
@Import({ImportSelectorImpl.class})
@Component
public class TestDemo {
    //这里 ApplicationContext 准备好了，能进行注入；
    @Autowired
    ApplicationContext applicationContext ;

    //向容器中注入一个bean ,beanName 是方法名。
    @Bean
    public BookDao bookDao(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        return new BookDaoImpl();
    }
}
