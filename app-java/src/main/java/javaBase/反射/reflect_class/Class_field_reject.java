package javaBase.反射.reflect_class;

import javaBase.反射.Reflect_util;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

//TODO 反射获取类中的字段信息
public class Class_field_reject {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //TODO 8. 获取本类中被声明的字段（本类中）
    @Test
    public void getDeclaredFields()  {
        /**
         * java.lang.String  name
         * protected java.lang.String  Person_name_protected
         * private int  age
         * public char  sex
         */
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Reflect_util.printField(field);
        }
    }
    //TODO 9. 获取本类与父类中被 public修饰的字段（本类与父类中）
    @Test
    public void getFields()  {
        /**
         * public char  sex
         * public java.lang.String  human_name_public
         */
        Field[] declaredFields = clazz.getFields();
        for (Field field : declaredFields) {
            Reflect_util.printField(field);
        }
    }

    /**
     * TODO 获取泛型变量，其他变量不会获取到
     * typeName = T
     * typeName = K
     */
    @Test
    public void getTypeParameters(){
        Type[] types = clazz.getTypeParameters();
        for (Type type : types) {
            System.out.println("typeName = "+type.getTypeName());
        }
    }
}
