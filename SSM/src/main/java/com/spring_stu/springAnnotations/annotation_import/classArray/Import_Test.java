package com.spring_stu.springAnnotations.annotation_import.classArray;

import com.spring_stu.domain.Address;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Import_Test {
    public static void main(String[] args) {
        //1.将Import_main类注入到IOC容器中.  等价于对Import_main类添加@Commont注解
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Import_main.class);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //2. 获取IOC 容器中所有注入的bean 的 id名，并进行输出
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        //3.验证 当注入类的构造方法只有一个有参构造时候，参数会从IOC容器中自动获取，进行注入
        Import_main import_main = applicationContext.getBean("import_main", Import_main.class);
        //System.out.println(import_main.getAddress());
        System.out.println(import_main);

        Address address = import_main.getAddress();
        address.setTel("dssssss");

        Address address2 = import_main.getAddress2();
        System.out.println(address == address2);

        address2.setTel("123456");

        System.out.println(address.getTel());

    }
}
