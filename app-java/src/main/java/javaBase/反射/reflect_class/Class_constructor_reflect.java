package javaBase.反射.reflect_class;

import javaBase.反射.Reflect_util;
import javaBase.反射.domain.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;

//反射机制获取类中的构造器：构造器(Constructor)
public class Class_constructor_reflect {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
   // 构造器：开发用的比较少

    @Test
    //TODO 获取本类中public修饰的构造方法(父类的构造方法获取不到，本类的私有构造也获取不到)
    //返回这个类的公共构造函数的 Constructor对象的数组
    public void getConstructors(){
        //1.获取Constructor对象
        Constructor<Person>[] constructors =  clazz.getConstructors();

        System.out.println("<-------------打印所有的构造器---------------------->");
        /**
         * Person:public javaBase.反射.domain.Person()
         * Person:public javaBase.反射.domain.Person(java.lang.String,int,char)
         */
        for (Constructor<Person> constructor: constructors) {
            Reflect_util.printConstructor(constructor);
        }
    }

    @Test
    //TODO 获取本类中的所有被声明的构造方法(只能获取本类中的所有构造方法)
    //与getConstructor的区别是会返回所有类型的构造方法
    public void getDeclaredConstructors(){
        //1.获取Constructor对象
        Constructor<Person>[] constructors =  clazz.getDeclaredConstructors();

        System.out.println("<-------------打印所有的构造器---------------------->");
        /**
         * Person:private javaBase.反射.domain.Person(java.lang.String)
         * Person:public javaBase.反射.domain.Person(java.lang.String,int,char)
         * Person:public javaBase.反射.domain.Person()
         */
        for (Constructor<Person> constructor: constructors) {
            Reflect_util.printConstructor(constructor);
        }
    }

    @Test
    //TODO 传入一个指定的参数类型来获取特定的 public 公共构造方法类
    public void getConstructor() throws NoSuchMethodException {
        // public Person(String name,int age,char sex)
        Constructor constructor = clazz.getConstructor(String.class, int.class, char.class);
        System.out.println(constructor);//public javaBase.反射.domain.Person(java.lang.String,int,char)
    }
    @Test
    //TODO 传入一个指定的参数类型来获取特定的 public 公共构造方法类
    public void getDeclaredConstructor() throws NoSuchMethodException {
        // private Person(String name)
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        System.out.println(constructor);//private javaBase.反射.domain.Person(java.lang.String)
    }



}
