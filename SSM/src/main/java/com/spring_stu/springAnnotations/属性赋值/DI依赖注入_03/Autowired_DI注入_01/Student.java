package com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.Autowired_DI注入_01;


import com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo.Car;
import com.spring_stu.springAnnotations.属性赋值.DI依赖注入_03.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 使用@Autowired 实现依赖注入。
 * AutowiredAnnotationBeanPostProcessor :解析完成自动装配功能
 */
@Component
public class Student  {
    //TODO 1.@Autowired *会先根据类型进行依赖注入； *如果有多个相同类型的bean注入，就根据字段名作为组件id来查找
    @Autowired
    private Car car01;

    //TODO 2. @Autowired默认是强制注入，找不到就会报异常； 我们可以设置 required = false属性来设置非强制注入，找不到会注入null.
    //TODO 但是必须结合  @Qualifier来使用。否则 当有多个 bean实例存在，并且没匹配上，仍然会报错(依赖注入异常)。
    @Autowired(required = false)
    @Qualifier("car02")
    private Car car02;

    //TODO 3. @Autowired + @Qualifier可以指定要注入的bean id 组件
    @Autowired
    @Qualifier("car01")
    private Car car03;


    //TODO 4. @Autowired可以标注在方法上
    private Car car04;
    @Autowired
    @Qualifier("car04")
    public void setCar04(Car car04){
     this.car04 = car04 ;
    }

    //TODO 5. @Autowired @Qualifier可以标注在方法参数上
    //TODO （测试没有生效，会赋值null）
    private Car car05;
    public void setCar05(@Autowired Car car05){
        this.car05 = car05 ;
    }

    //TODO 6. 在注入组件的时候，如果当使用@Primary，那么在使用 @Autowired的时候，会优先使用这个实例进行依赖注入
    //TODO   （如果使用了@Qualifier，那么@Primary就会失效）
    @Autowired
    private Car car06;


    //TODO 7. @Autowired 可以标注在构造器上(@Qualifier不能进行标注)，如果只要一个入参构造器， @Autowired可以省略；
    //TODO  (只有一个构造方法的时候，组件扫描的时候会生效)
    private Friend friend;
    @Autowired
    public Student(Friend friend){
        this.friend = friend ;
    }

//    public Student(){}

    //测试注入的结果
    public void getValueInfo(String beanName){
        switch (beanName){
            case "car01":
                System.out.println("car01 : " + car01);   break;
            case "car02":
                System.out.println("car02 : " + car02);   break;
            case "car03":
                System.out.println("car03 : " + car03);   break;
            case "car04":
                System.out.println("car04 : " + car04);   break;
            case "car05":
                System.out.println("car05 : " + car05);   break;
            case "car06":
                System.out.println("car06 : " + car06);   break;
            case "friend":
                System.out.println("friend :"+ friend);   break;
            default:
                System.out.println("没有找对应的bean！");
        }


    }


}
