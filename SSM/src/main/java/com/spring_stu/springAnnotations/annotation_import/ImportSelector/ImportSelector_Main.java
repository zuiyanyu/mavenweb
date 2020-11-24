package com.spring_stu.springAnnotations.annotation_import.ImportSelector;

import com.spring_stu.dao.BookDao;
import com.spring_stu.dao.daoImpl.BookDaoImpl;
import com.spring_stu.springAnnotations.Bean02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

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
//@Component
public class ImportSelector_Main {
    //这里 ApplicationContext 准备好了，能进行注入；
    @Autowired
    ApplicationContext applicationContext ;

    //向容器中注入一个bean ,beanName 是方法名。
    @Bean
    public BookDao bookDao(){
        System.out.println("===========ImportSelector_Main：applicationContext==============");
        // bean id的名称都已经进行注册，但是bookDao的真实实体还没有注入进来，其他的两个已经注入进IOC了。
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if(!beanDefinitionName.startsWith("org.springframework.context")){
                System.out.println(beanDefinitionName);
            }
        }

        //beanNamesForType_BookDaoImpl = []
        String[] beanNamesForType_BookDaoImpl = applicationContext.getBeanNamesForType(BookDaoImpl.class);
        System.out.println("beanNamesForType_BookDaoImpl = "+ Arrays.toString(beanNamesForType_BookDaoImpl));

        //beanNamesForType_TestDemo2 = [com.spring_stu.springAnnotations.annotation_import.TestDemo2]
        String[] beanNamesForType_TestDemo2 = applicationContext.getBeanNamesForType(Bean02.class);
        System.out.println("beanNamesForType_TestDemo2 = "+ Arrays.toString(beanNamesForType_TestDemo2));

        //beanNamesForType_ImportSelector_Main = [importSelector_Main]
        String[] beanNamesForType_ImportSelector_Main = applicationContext.getBeanNamesForType(ImportSelector_Main.class);
        System.out.println("beanNamesForType_ImportSelector_Main = "+ Arrays.toString(beanNamesForType_ImportSelector_Main));

        System.out.println("=========================");
        return new BookDaoImpl();
    }
}
