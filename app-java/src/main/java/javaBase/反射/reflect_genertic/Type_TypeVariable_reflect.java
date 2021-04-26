package javaBase.反射.reflect_genertic;

import org.junit.Test;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * TypeVariable：类型变量
 * 范型信息在编译时会被转换为一个特定的类型, 而TypeVariable就是用来反映在JVM编译该泛型前的信息。
 * (通俗的来说，TypeVariable就是我们常用的T，K这种泛型变量)
 *
 */
public class Type_TypeVariable_reflect<K extends Number&List, T> {
    //K有指定了上边界Number
    K key;
    //T没有指定上边界，其默认上边界为Object
    T value;
    String name ;

    //TODO 获取泛型变量，其他变量不会获取到，所以不会获取 String name这样的字段声明。
    Type[] types = Type_TypeVariable_reflect.class.getTypeParameters();

    //TODO 返回当前类型的类名
    @Test
    public void getName(){
        for (Type type : types){
            TypeVariable t = (TypeVariable) type;
            //输出名称
            /**
             *getTypeName(): K
             * getName(): K
             * ----------------
             * getTypeName(): T
             * getName(): T
             * ----------------
             */
            //getTypeName(): Returns a string describing this type, including information about any type parameters.
            System.out.println("getTypeName(): " + t .getTypeName());

            //获取源码中的变量名
            System.out.println("getName(): " + t .getName());

            System.out.println("----------------");
        }
    }


    /**
     * TODO 返回当前类型的上边界，如果没有指定上边界，则默认为Object。
     * TODO 类型变量的上界可能有多个。 比如 <K extends Number&List>的泛型变量有两个
     *
     */
    @Test
    public void getBounds(){
        for (Type type : types){
            TypeVariable t = (TypeVariable) type;
            //TODO 类型变量的上界可能有多个。 比如 <K extends Number&List>的泛型变量有两个
            /**
             * <K extends Number&List> :
             * bound name =java.lang.Number
             * bound name =java.util.List
             */
            Type[] bounds = t.getBounds();
            for (Type bound : bounds) {
                System.out.println("bound name ="+ bound.getTypeName());
            }
            //输出上边界
            System.out.println("---------------------");
        }
    }

    //TODO 返回当前类型所在的类的Type。
    @Test
    public void getGenericDeclaration(){
        for (Type type : types){
            TypeVariable t = (TypeVariable) type;
            GenericDeclaration genericDeclaration = t.getGenericDeclaration();

            /**
             * --getGenericDeclaration():class javaBase.反射.reflect_genertic.Type_TypeVariable_reflect
             * --getGenericDeclaration():class javaBase.反射.reflect_genertic.Type_TypeVariable_reflect
             */
            //输出所在的类的类型
            System.out.println("--getGenericDeclaration():" + genericDeclaration);
        }
    }
}
