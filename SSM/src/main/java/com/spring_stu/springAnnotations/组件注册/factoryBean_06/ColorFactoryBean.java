package com.spring_stu.springAnnotations.组件注册.factoryBean_06;

import com.spring_stu.springAnnotations.组件注册.dsc.pojo.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * 给容器中注册组件；
 * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
 * 2）、@Bean[导入的第三方包里面的组件]
 * 3）、@Import[快速给容器中导入一个组件]
 * 		1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
 * 		2）、ImportSelector:返回需要导入的组件的全类名数组；
 * 		3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
 * 4）、使用Spring提供的 FactoryBean（工厂Bean）;
 * 		1）、默认获取到的是工厂bean调用getObject创建的对象
 * 		2）、要获取工厂Bean本身，我们需要给id前面加一个&
 * 			&colorFactoryBean
 */
/**
 * TODO 使用spring提供的FactoryBean(工厂bean)创建组件.
 * spring在整合其他框架的时候用的很多
 */
//创建一个Spring定义的FactoryBean
//FactoryBean 是只为一个特定的bean服务
public class ColorFactoryBean implements FactoryBean<Color> {


    //返回一个Color对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("ColorFactoryBean...getObject...");
        return new Color();
    }

    //返回创建bean的类型
    @Override
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return Color.class;
    }

    //返回被创建的bean 是否是单例？
    //true：这个bean是单实例，在容器中保存一份
    //false：多实例，每次获取都会创建一个新的bean；
    @Override
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return true;
    }

}
