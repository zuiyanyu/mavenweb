package javaBase.反射.reflect_class;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public class Class_Interface_reject {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void a(){
        Type[] genericInterfaces = clazz.getGenericInterfaces();
    }



    /**
     * TODO Type[] getGenericInterfaces( ) 5.0
     * 获得接口的泛型类型（以声明的次序)， 如果这个类型没有实现接口，返回长度为 0 的数组。
     */
    @Test
    public void getGenericInterfaces(){

        //public interface Collection<E> extends Iterable<E>
        Class<Collection> aClass = Collection.class;

        //TODO ParameterizedType 和 Class 是同级别的，是不能互相转换的。

        Class<?>[] interfaces = aClass.getInterfaces();
        //TODO 获取class类型：
        for (Class<?> anInterface : interfaces) {
            //anInterface name :java.lang.Iterable   类全限定名中没有泛型信息
            System.out.println("anInterface name :"+ anInterface.getName());

        }

        //TODO 获取ParameterizedType 类型:保留接口的泛型信息
        Type[] genericInterfaces = aClass.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            //genericInterface name :java.lang.Iterable<E>  类全限定名中有泛型信息
            System.out.println("genericInterface name :"+genericInterface.getTypeName());
            //true
            System.out.println(genericInterface instanceof ParameterizedType);
        }

    }
}
