package javaBase.反射.reflect_class;

import javaBase.反射.Reflect_util;
import javaBase.反射.domain.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
    public void testConstructor() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {




        System.out.println("<------------------------------------------------>");
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class,char.class);
        System.out.println("拿到指定的-->" + constructor);

        //2.调用构造器的newInstance()方法创建对象
        Object obj= constructor.newInstance("changwen", 11,'M');
    }

}
