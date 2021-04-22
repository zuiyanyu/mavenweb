package javaBase.反射.reflect_class;

import javaBase.反射.domain.Person;
import org.junit.Test;

import java.lang.reflect.Type;


//TODO 反射获取类的基本信息
public class Class_baseInfo_reflect {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * TODO 1. 反射机制获取类有三种方法
     */
    @Test
    public void GetClass() throws ClassNotFoundException {
        Class clazz = null;

        //1 直接通过类名.Class的方式得到
        clazz = Person.class;
        System.out.println("通过类名: " + clazz);

        //2 通过对象的getClass()方法获取,这个使用的少（一般是传的是Object，不知道是什么类型的时候才用）
        Object obj = new Person();
        clazz = obj.getClass();
        System.out.println("通过getClass(): " + clazz);

        //3 通过全类名获取，用的比较多，但可能抛出ClassNotFoundException异常
        clazz = Class.forName("javaBase.反射.domain.Person");
        System.out.println("通过全类名获取: " + clazz);
    }

    //TODO 5.获取全限定类名
    @Test
    public void getName(){
        String name = clazz.getName();
        //name: javaBase.反射.domain.Person
        System.out.println(name);
    }

    //TODO 6.获取类所在的包名
    @Test
    public void getPackage(){
        Package aPackage = clazz.getPackage();
        //aPackage = package javaBase.反射.domain
        System.out.println("aPackage = "+aPackage);
    }

    //TODO 获取类的父类
    public void a(){


    }
    //TODO 获取类的接口

    //TODO 获取类的泛型信息
    @Test
    public void getGenericSuperclass(){
        Type genericSuperclass = clazz.getGenericSuperclass();

    }

    // TODO 2.Class类的newInstance()方法，创建类的一个对象。
    @Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException {
        //使用Class类的newInstance()方法使用类的无参构造方法一个对象。
        Object obj = clazz.newInstance();
        System.out.println(obj);
    }






}
