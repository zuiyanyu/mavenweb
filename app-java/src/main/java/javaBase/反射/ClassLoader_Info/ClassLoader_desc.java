package javaBase.反射.ClassLoader_Info;

import org.junit.Test;

import java.io.IOException;


public class ClassLoader_desc {

    /**
     * ClassLoader类装载器
     */
    @Test
    public void testClassLoader1() throws ClassNotFoundException, IOException {
        //1、获取一个系统的类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统的类加载器-->" + classLoader);

        //2、获取系统类加载器的父类加载器(扩展类加载器（extensions classLoader）)
        classLoader = classLoader.getParent();
        System.out.println("扩展类加载器-->" + classLoader);

        //3、获取扩展类加载器的父类加载器
        //输出为Null,无法被Java程序直接引用
        classLoader = classLoader.getParent();
        System.out.println("启动类加载器-->" + classLoader);

        //4、测试当前类由哪个类加载器进行加载 ,结果就是系统的类加载器
        classLoader = Class.forName("javaBase.反射.domain.Person").getClassLoader();
        System.out.println("当前类由哪个类加载器进行加载-->" + classLoader);

        //5、测试JDK提供的Object类由哪个类加载器负责加载的
        //输出为Null,无法被Java程序直接引用
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println("JDK提供的Object类由哪个类加载器加载-->" + classLoader);
    }
}
