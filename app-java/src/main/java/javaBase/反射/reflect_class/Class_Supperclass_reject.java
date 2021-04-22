package javaBase.反射.reflect_class;

import javaBase.反射.Reflect_util;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Class_Supperclass_reject {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //TODO 通过当前反射对象，拿到父类反射对象
    @Test
    public void getSuperclass() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class superclass = clazz.getSuperclass();

        System.out.println(superclass.getName());//javaBase.反射.domain.Human
        System.out.println(superclass.getSimpleName());//Human

        //拿到父类后，就能获取父类的方法等信息了
        Method[] declaredMethods = superclass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Reflect_util.printMethod(declaredMethod);
        }

        Method  method3= superclass.getDeclaredMethod("play_public");
        Reflect_util.printMethod(method3);
        method3.invoke(superclass.newInstance());

        Method  method9= superclass.getDeclaredMethod("play_private");
        method9.setAccessible(true); //需要设置权限为可访问的，才能执行父类的私有方法
        Reflect_util.printMethod(method3); //Human: public void play_public ()
        method9.invoke(superclass.newInstance());//private无参：play_private


    }


}
