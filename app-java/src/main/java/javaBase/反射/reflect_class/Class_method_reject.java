package javaBase.反射.reflect_class;

import javaBase.反射.Reflect_util;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

//TODO 反射获取类中的方法
public class Class_method_reject {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO 3、获取类中 public方法修饰的方法.(本类和父类中所有public修饰的方法)
    @Test
    public void getMethods_Public(){
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Reflect_util.printMethod(method);
        }
    }
    //TODO 4. 获取类中所有被声明的方法。(只获取本类中各个权限的方法)
    @Test
    public void getDeclaredMethods_all(){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Reflect_util.printMethod(method);
        }
    }
    /**TODO 7.根据方法名获取一个方法
     * @throws NoSuchMethodException
     */
    @Test
    public void getMethod_test() throws NoSuchMethodException {
        //只能获取本类或者父类的public修饰的方法
        Arrays.asList(new String[]{"method_public","method_private","method_protected","method_default"
                ,"method_private_Human"})
                .forEach((methodName)->getMethod(methodName));
        System.out.println("========================================");
        System.out.println("========================================");
        //只能获取本类中的方法，任意修饰符号都可以
        Arrays.asList(new String[]{"method_public","method_private","method_protected","method_default"
                ,"method_private_Human"})
                .forEach((methodName)->getDeclaredMethod(methodName));
    }

    /**
     * TODO 根据方法名获取一个方法: 只能获取本类或者父类的public修饰的方法
     * ----------获取method_public方法--------------
     * public  method_public
     * ----------获取method_private方法--------------
     * 异常：javaBase.反射.domain.Person.method_private()
     * ----------获取method_protected方法--------------
     * 异常：javaBase.反射.domain.Person.method_protected()
     * ----------获取method_default方法--------------
     * 异常：javaBase.反射.domain.Person.method_default()
     * ----------获取method_public_Human方法--------------
     * public  method_public_Human
     * @param methodName
     */
    public void getMethod(String methodName) {
        System.out.println("----------获取"+methodName +"方法--------------");
        try{
            Method method = clazz.getMethod(methodName);
            Reflect_util.printMethod(method);
        }catch (Exception e ){
            System.out.println("异常："+ e.getMessage());
        }
    }

    /**TODO 根据方法名获取一个方法: 只能获取本类中的方法，任意修饰符号都可以
     * ----------获取method_public方法--------------
     * public  method_public
     * ----------获取method_private方法--------------
     * private  method_private
     * ----------获取method_protected方法--------------
     * protected  method_protected
     * ----------获取method_default方法--------------
     * default  method_default
     * ----------获取method_public_Human方法--------------
     * 异常：javaBase.反射.domain.Person.method_private_Human()
     * @param methodName
     */
    public void getDeclaredMethod(String methodName) {
        System.out.println("----------获取"+methodName +"方法--------------");
        try{
            Method method = clazz.getDeclaredMethod(methodName);
            Reflect_util.printMethod(method);
        }catch (Exception e ){
            System.out.println("异常："+ e.getMessage());
        }
    }

    //TODO 获取指定方法：方法名 + 入参类型 （本类中）
    @Test
    public void getDeclaredMethod_withParam() throws NoSuchMethodException {
        //第一个参数是方法名，后面的是方法里的参数
        Method method2 = clazz.getDeclaredMethod("method_private_2",String.class);
        Reflect_util.printMethod(method2);

        //第一个参数是方法名，后面的是方法里的参数
        Method method3 = clazz.getDeclaredMethod("method_public_2", String.class, int.class, char.class);
        Reflect_util.printMethod(method3);
    }

    //TODO 获取指定方法：方法名 + 入参类型 （本类 + 父类）
    @Test
    public void getMethod_withParam() throws NoSuchMethodException {
        //第一个参数是方法名，后面的是方法里的参数
        Method method2 = clazz.getMethod("setHuman_name",String.class);
        Reflect_util.printMethod(method2);//public void setHuman_name (String arg0)


    }
}
