package javaBase.泛型;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SourceCode_01<T> {
    Class<T> clazz = null;
    {   //TODO JDBC中的应用实例
        Class<? extends SourceCode_01> aClass = this.getClass();
        //获取泛型父类
        Type genericSuperclass = aClass.getGenericSuperclass();

        //如果父类是参数化的泛型类
        if(genericSuperclass instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments(); //获取父类的实际泛型类型集
            Type actualTypeArgument = actualTypeArguments[0]; //获取父类的第一个实际泛型类型
            if(actualTypeArgument instanceof  Class){ //如果泛型类给定了实际的类型 比如 A extend B<String> 而不是 A<T> extend B<T>
                clazz = (Class<T>)actualTypeArgument;//获取实际泛型类对象的 Class类
                System.out.println("T type ="+clazz.getName());
            }
        }
    }
    /**TODO HashMap 中的应用实例
     * 获取 Comparable接口中的泛型类  class C implements Comparable<C>
     * Returns x's Class if it is of the form "class C implements Comparable<C>", else null.
     */
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    /**
     * 获取 继承类中的泛型类  A extend B<T>
     *     即获取 T 的class对象
     * @param x
     * @return
     */
//    static Class<?> extendClassFor(Object x){
//        Type genericSuperclass = x.getClass().getGenericSuperclass();
//        genericSuperclass
//    }

}
