package javaBase.反射.reflect_genertic;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * ParameterizedType：参数化类型
 * 参数化类型即我们通常所说的泛型类型，一提到参数，最熟悉的就是定义方法时有形参，然后调用此方法时传递实参。
 * 那么参数化类型怎么理解呢？顾名思义，就是将类型由原来的具体的类型参数化，类似于方法中的变量参数，
 * 此时类型也定义成参数形式（可以称之为类型形参），然后在使用/调用时传入具体的类型（类型实参）。
 * 那么我们的ParameterizedType就是这样一个类型，下面我们来看看它的三个重要的方法：
 */
public class Type_ParameterizedType_reflect<T> {
    //是ParameterizedType
    private Map<String,?> map ;
    private List<? extends Number> listNumber;
    private List<T> listT;
    private Map.Entry<String,Object> mapEntry ;


    Class aClass = Type_ParameterizedType_reflect.class ;

    /**
     *  getRawType(): Type
     *  TODO 该方法的作用是返回当前的ParameterizedType的类型。
     *  如一个List，返回的是List的Type，即返回当前参数化类型本身的Type。
     */
    @Test
    public void getRawType() throws NoSuchFieldException {
        /**
         * field typeName is :java.util.List<? extends java.lang.Number>
         * rowTypeName is :java.util.List
         */
        Field field = aClass.getDeclaredField("listNumber");
        //获取能表示声明字段的泛型类型
        Type genericType = field.getGenericType();
        String typeName = genericType.getTypeName();
        System.out.println("field typeName is :"+typeName);

        //如果是参数类型
        if(genericType instanceof ParameterizedType){
            Type rawType = ((ParameterizedType) genericType).getRawType();
            String rowTypeName = rawType.getTypeName();
            System.out.println("rowTypeName is :" + rowTypeName);
        }
    }


    /**
     * getOwnerType(): Type
     * TODO  如果是内部类型， 则返回其外部类型， 如果是一个顶级类型， 则返回 null。
     * 如Map.Entry<String, Object>这个参数化类型返回的是Map类型(因为Map.Entry这个类型所在的类是Map)。
     */
    @Test
    public void getOwnerType() throws NoSuchFieldException {
        ParameterizedType parameterizedType = (ParameterizedType) aClass.getDeclaredField("listNumber").getGenericType();
        Type ownerType = parameterizedType.getOwnerType();
        if(ownerType ==null){
            //    private List<? extends Number> listNumber;
            System.out.println("此参数化类型 不是一个内部类，所以返回值为null");
        }else{
            //    private Map.Entry<String,Object> mapEntry ;
            System.out.println(ownerType.getTypeName());//java.util.Map
        }
    }

    /**
     * TODO getActualTypeArguments(): Type[] 该方法返回参数化类型<>中的实际参数类型，
     * 如 Map<String,Person>map 这个 ParameterizedType 返回的是 String 类,Person 类的全限定类名的 Type Array。
     * 注意: 该方法只返回最外层的<>中的类型，无论该<>内有多少个<>。
     *
     *     private List<? extends Number> listNumber;  实际参数类型为：WildcardType 通配符类型
     *           实际参数类型 <? extends java.lang.Number> 为：WildcardType
     *
     *     private Map.Entry<String,Object> mapEntry ; 实际参数类型有两个：都为Class类型
     *           实际参数类型 <java.lang.String> 为：Class
     *           实际参数类型 <java.lang.Object> 为：Class
     *
     *     private Map<String,?> map ; 实际参数类型有两个：String 为Class类型， ? 为 WildcardType 通配符类型。
     *           实际参数类型 <java.lang.String> 为：Class
     *           实际参数类型 <?> 为：WildcardType
     *
     *     private List<T> listT; 实际参数类型为：T TypeVariable  (类型变量)
     *           实际参数类型 <T> 为：TypeVariable
     */
    @Test
    public void getActualTypeArguments() throws NoSuchFieldException {
        ParameterizedType parameterizedType = (ParameterizedType) aClass.getDeclaredField("listT").getGenericType();
        //TODO 该方法返回参数化类型<>中的实际参数类型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            if(actualTypeArgument instanceof WildcardType){
                System.out.println("实际参数类型 <"+actualTypeArgument.getTypeName()+"> 为：WildcardType");
            }
            if(actualTypeArgument instanceof TypeVariable){
                System.out.println("实际参数类型 <"+actualTypeArgument.getTypeName()+"> 为：TypeVariable");
            }
            if(actualTypeArgument instanceof Class){
                System.out.println("实际参数类型 <"+actualTypeArgument.getTypeName()+"> 为：Class");
            }
        }
    }
}
