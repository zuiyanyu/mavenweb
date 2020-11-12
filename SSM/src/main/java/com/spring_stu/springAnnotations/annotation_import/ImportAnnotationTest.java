package com.spring_stu.springAnnotations.annotation_import;

import com.spring_stu.domain.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring_stu/springAnnotations/applicationContext.xml")
public class ImportAnnotationTest {

    @Autowired
    ApplicationContext applicationContext ;

    @Test
    public void ImportType03() {
        System.out.println("=======ImportType03======入口：" +
                "com.spring_stu.springAnnotations.annotation_import.ImportBeanDefinitionRegistrarImpl_Test");
        String[] beanDefinitionNames1 = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames1) {
            System.out.println(beanDefinitionName);
        }
    }
    @Test
    public void ImportType02() {
        System.out.println("=======ImportType02======入口： " +
                "com.spring_stu.springAnnotations.annotation_import.TestDemo");
        //配置文件进行扫描 进行加载
        String[] beanDefinitionNames1 = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames1) {
            System.out.println(beanDefinitionName);
        }

//        //没有配置 @Component 的类无法进行自动扫描，这种方式可以进行。
//        //applicationContext 和 annotationConfigApplicationContext 是两套上下文环境。建议使用第一种方式
//        AnnotationConfigApplicationContext annotationConfigApplicationContext=
//                new AnnotationConfigApplicationContext(TestDemo.class);  //这里的参数代表要做操作的类
//        String[] beanDefinitionNames2 = annotationConfigApplicationContext.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames2){
//            System.out.println(name);
//        }
     }

    @Test
    public void ImportType01(){
        System.out.println("=======ImportType01======入口：" +
                " com.spring_stu.springAnnotations.annotation_import.Import_Type1");
        System.out.println("applicationContext ="+applicationContext);

//        @Autowired
//        Import_Type1 Import_Type1 ;
//        System.out.println("Import_Type1 = "+ this.Import_Type1);
//        System.out.println(this.Import_Type1.getAddress());


        System.out.println("-----------------------");
        Import_Type1 importAnnotation_type1 = applicationContext.getBean("import_Type1", Import_Type1.class);
        System.out.println(importAnnotation_type1);
        Address address = applicationContext.getBean("com.spring_stu.domain.Address", Address.class);
        System.out.println(address);
        System.out.println("-----------------------");
    }

    }
