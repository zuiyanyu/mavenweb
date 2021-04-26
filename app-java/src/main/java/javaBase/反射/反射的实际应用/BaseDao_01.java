package javaBase.反射.反射的实际应用;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//TODO 获取子类中给定的VO类 ,这样上层就能动态获取用户给定的 VO类型了，从而能动态进行处理了。
public class BaseDao_01<T> {
    private Class<T> aClass ;
    {
        //获取子类 中给定的VO类  this代表的是 BaseDao的子类 : User extend  BaseDao<User>
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass ;

        //获取父类的实际泛型类型集
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        //获取父类的第一个实际泛型类型
        Type actualTypeArgument = (Class<T>)actualTypeArguments[0];

        //如果泛型类给定了实际的类型 比如 A extend B<String> 而不是 A<T> extend B<T>
        if(actualTypeArgument instanceof  Class){

            //获取实际泛型类对象的 Class类
            aClass = (Class<T>)actualTypeArgument;
        }
        //获取泛型T 对应的实例类对象
        //T type =class javaBase.反射.反射的实际应用.BaseDao$UserDao
        System.out.println("T type ="+this.aClass);

     }

    static class UserDao extends BaseDao_01<UserDao> {
        //实例测试
        public static void main(String[] args) {
            new UserDao();
        }
    }
}
