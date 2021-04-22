package javaBase.反射.reflect_Constructor;

import javaBase.反射.domain.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructor_newInstance {
    Constructor constructor ;
    Class aClass = Person.class ;


    //TODO 使用公有的构造方法进行实例化对象
    @Test
    public void newInstance_public() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        // public Person(String name,int age,char sex)
        constructor =  aClass.getConstructor(String.class, int.class, char.class);
        Person personObj = (Person)constructor.newInstance("", 20, '1');
        personObj.eat();
    }

    //TODO 使用私有的构造方法进行实例化对象
    @Test
    public void newInstance_private() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // private Person(String name)
        Constructor constructor = aClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person personObj = (Person)constructor.newInstance("test");
        personObj.eat();
    }

}
