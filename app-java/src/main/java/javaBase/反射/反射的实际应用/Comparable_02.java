package javaBase.反射.反射的实际应用;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Comparable_02 {
    /**TODO HashMap 中的应用实例 。
     * 源码如下
     *
     * TODO 如果 x是 是 class X implements Comparable<X>;这种格式的声明,就返回 x的Class类； 否则返回null
     * Returns x's Class if it is of the form "class C implements Comparable<C>", else null.
     * 实现的关键点是：
     *     获取 Comparable接口中的泛型类  class C implements Comparable<C>
     */
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c;
            Type[] ts, as;
            Type t;
            ParameterizedType p;

            if ((c = x.getClass()) == String.class) // bypass checks
                //String类型实现了Comparable<C>，并且 直接将 C 定为了String类型。所以直接返回String即可。
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                // 其他类型，就需要进行判断 X implements Comparable<C> 时候，C 是否给定为 X类型了。如果不是返回null ,如果是返回X.
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
