package javaBase.反射.reflect_method;

import javaBase.反射.Reflect_util;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * TODO java.lang.reflect.Method
 *      • TypeVariable[] getTypeParameters( ) 5.0
 *          如果这个方法被声明为泛型方法， 则获得泛型类型变量，否则返回长度为 0 的数组。
 *      •Type getGenericReturnType( ) 5.0
 *          获得这个方法被声明的泛型返回类型。
 *      •Type[ ] getGenericParameterTypes( ) 5.0
 *          获得这个方法被声明的泛型参数类型。 如果这个方法没有参数， 返回长度为 0 的数组。
 */
public class Reflect_Method {
    public <E,T> E getTypeParametersTest(E e,T t,String name){
        return e ;
    }

   Class aClass = Reflect_Method.class;
    Method getTypeParametersTest = Reflect_util.getMethodByName(aClass,"getTypeParametersTest");

    //TODO 获取方法的入参类型：Class类型
    @Test
    public void getParameterTypes() throws NoSuchMethodException {
        /**
         * java.lang.Object
         * java.lang.Object
         * java.lang.String
         */
        Class<?>[] parameterTypes = getTypeParametersTest.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            System.out.println(parameterType.getName());
        }
    }

    /**
     * TODO 获取方法 声明的泛型类型
     * TypeVariable[] getTypeParameters( ) 5.0
     * TODO 如果这个方法被声明为泛型方法， 则获得泛型类型变量，否则返回长度为 0 的数组。
     */
    @Test
    public void getTypeParameters()  {
        /**
         * getName :E
         * ------------------
         * getName :T
         */
        TypeVariable<Method>[] typeParameters = getTypeParametersTest.getTypeParameters();
        for (TypeVariable<Method> typeParameter : typeParameters) {
            System.out.println("------------------");
            System.out.println("getName :" + typeParameter.getName());
        }
    }

    /**
     * TODO 获得这个方法被声明的泛型返回类型。
     */
    @Test
    public void getGenericReturnType(){
        Type genericReturnType = getTypeParametersTest.getGenericReturnType();
        System.out.println(genericReturnType.getTypeName()); //E
    }


}
