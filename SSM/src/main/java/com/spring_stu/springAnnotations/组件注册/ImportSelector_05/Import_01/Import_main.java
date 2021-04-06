package com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Import_01;


import com.spring_stu.domain.Address;
import com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean01;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 *TODo @Import注解的 value 可以是有以下几种：
 *TODo     1）@Configuration,
 *TODo     2）ImportSelector,
 *TODo     3）ImportBeanDefinitionRegistrar,
 *TODo     4）普通的类.
 */

/** TODO @Import注解的 value值为普通类的时候:  @Import(value = {Address.class,Bean01.class})
 * 1. 先进行 Import中的类进行注入到IOC中
 * 2. 然后进行 @Component 注解的类的注入。
 * 3. @Component 注解的类，当构造方法只有一个有参构造时候，参数会从IOC容器中自动获取，进行注入。
 */

//TODO 使用@Import 可以使用 class类将类注入到IOC容器中。 可以指定多个
@Import(value = {Address.class,Bean01.class})
//@Component
@Getter
@Setter
@ToString
public class Import_main {
    private Address address ;

    @Autowired
    private Address address2 ;

    /**
     * TODO 当只有一个有参构造方法的时候，参数会从容器中获取。
     * @param address
     */
    public Import_main(Address address){
        this.address = address ;
    }

    public static void main(String[] args) {
        //1.将Import_main类注入到IOC容器中.  等价于对Import_main类添加@Commont注解
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Import_main.class);
        //ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        //2. 获取IOC 容器中所有注入的bean 的 id名，并进行输出
        /**
         * import_main
         * com.spring_stu.domain.Address
         * com.spring_stu.springAnnotations.组件注册.ImportSelector_05.Bean01
         */
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        //3.验证 当注入类的构造方法只有一个有参构造时候，参数会从IOC容器中自动获取，进行注入
        Import_main import_main = applicationContext.getBean("import_main", Import_main.class);

        Address address = import_main.getAddress();
        Address address2 = import_main.getAddress2();
        System.out.println(address == address2); //true


    }

}
