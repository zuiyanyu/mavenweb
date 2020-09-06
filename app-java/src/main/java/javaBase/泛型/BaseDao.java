package javaBase.泛型;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseDao<T> {
    private Class<T> aClass ;
    {
        //获取子类 中给定的VO类
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass ;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        aClass = (Class<T>)actualTypeArguments[0];

        //获取泛型T 对应的实例类对象
        System.out.println("T = "+ aClass.getName());
    }
}
