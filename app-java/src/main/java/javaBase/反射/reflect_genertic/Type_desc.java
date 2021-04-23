package javaBase.反射.reflect_genertic;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//TODO Type是Java语言中所有类型的公共父接口。
//TODO Type的五个子类：Class、ParameterizedType、 TypeVariable、GenericArrayType、WildcardType。

//TODO 我们先来分清类型 ParameterizedType 、TypeVariable、GenericArrayType、WildcardType 之间的区别
public class Type_desc<K extends Number, T> {
    //TODO ParameterizedType:参数化类型
    private HashMap<String, Object> map;
    private HashSet<String> set;
    private List<String> list;
    private List<T> listList;
    private Class<?> clz;

    //不是ParameterizedType
    private Integer i;
    private String str;

    //TODO TypeVariable：类型变量
    //K有指定了上边界Number
    K key;
    //T没有指定上边界，其默认上边界为Object
    T value;

    //TODO GenericArrayType：泛型数组类型
    //泛型数组类型
    private T[] value2;
    private List<String>[] list2;

    //不是泛型数组类型
    private List<String> singleList;
    private T singleValue;

    //TODO WildcardType: 通配符类型  表示通配符类型，比如 <?>, <? Extends Number>等
    //TODO WildcardType 不能独立存在，必须以参数化类型的参数形式存在。
    List<? extends OutputStream> numberList ;
    Map<? extends OutputStream,? extends Object> map_wildcardType ;
    Map<String,? extends Object> map_wildcardType2 ;
    List<? super InputStream> upperList;
    List<Integer> list3 ;
    //
    InputStream inputStream ;



    /**
     * //TODO 判断以上的字段都是哪些类型 ParameterizedType 、TypeVariable、GenericArrayType、WildcardType
     *
     * ParameterizedType::map : java.util.HashMap<java.lang.String, java.lang.Object>
     * -------------------------------------------
     * ParameterizedType::set : java.util.HashSet<java.lang.String>
     * -------------------------------------------
     * ParameterizedType::list : java.util.List<java.lang.String>
     * -------------------------------------------
     * TODO List<T> 是 ParameterizedType 类型 ， <> 中的 T是TypeVariable类型。
     * TypeVariable ==== T
     * ParameterizedType::TypeVariable::listList : java.util.List<T>
     * -------------------------------------------
     * TODO Class<?> 是 ParameterizedType 类型 ， <> 中的 ?是 WildcardType 类型
     * WildcardType ==== ?
     * ParameterizedType::WildcardType::clz : java.lang.Class<?>
     * -------------------------------------------
     * Class::i : java.lang.Integer
     * -------------------------------------------
     * Class::str : java.lang.String
     * -------------------------------------------
     * //TODO K key ; 就是类型变量
     * TypeVariable::key : K
     * -------------------------------------------
     * TypeVariable::value : T
     * -------------------------------------------
     * GenericArrayType::value2 : T[]
     * -------------------------------------------
     * GenericArrayType::list2 : java.util.List<java.lang.String>[]
     * -------------------------------------------
     * ParameterizedType::singleList : java.util.List<java.lang.String>
     * -------------------------------------------
     * TypeVariable::singleValue : T
     * -------------------------------------------
     * WildcardType ==== ? extends java.io.OutputStream
     * ParameterizedType::WildcardType::numberList : java.util.List<? extends java.io.OutputStream>
     * -------------------------------------------
     * TODO Map<? extends OutputStream,? extends Object>  是 ParameterizedType 类型 ，
     * TODO <> 中的 ? extends OutputStream 是 WildcardType 类型
     * TODO <> 中的 ? extends Object 也是 WildcardType 类型
     * WildcardType ==== ? extends java.io.OutputStream
     * WildcardType ==== ?
     * ParameterizedType::WildcardType::WildcardType::map_wildcardType : java.util.Map<? extends java.io.OutputStream, ?>
     * -------------------------------------------
     * WildcardType ==== ?
     * ParameterizedType::WildcardType::map_wildcardType2 : java.util.Map<java.lang.String, ?>
     * -------------------------------------------
     * WildcardType ==== ? super java.io.InputStream
     * ParameterizedType::WildcardType::upperList : java.util.List<? super java.io.InputStream>
     * -------------------------------------------
     * ParameterizedType::list3 : java.util.List<java.lang.Integer>
     * -------------------------------------------
     * Class::inputStream : java.io.InputStream
     * -------------------------------------------
     */
    @Test
    public void selectType(){
        Class aClass = Type_desc.class ;
        StringBuilder sb = new StringBuilder();

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            sb.setLength(0);

            //获取字段的泛型类型
            Type genericType = field.getGenericType();

            if(genericType instanceof Class){
                sb.append("Class").append("::") ;
            }

            if(genericType instanceof ParameterizedType){
                sb.append("ParameterizedType").append("::") ;

                //当类型是 参数化类型的时候，其参数才可能是
                //TODO 参数化类型的参数 可以是 WildcardType、TypeVariable 等类型
                Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    //TODO WildcardTyp必须是以 参数化类型的参数形式存在。
                    if(actualTypeArgument  instanceof WildcardType){
                        sb.append("WildcardType").append("::") ;
                        System.out.println("WildcardType ==== "+actualTypeArgument.getTypeName());
                    }
                    //TODO 参数化类型的参数 是TypeVariable 的情况。
                    if(actualTypeArgument instanceof TypeVariable){
                        System.out.println("TypeVariable ==== "+actualTypeArgument.getTypeName());
                        sb.append("TypeVariable").append("::") ;
                    }
                    //TODO 参数化类型的参数 是其他类型的参数的情况。
                }
            }
            if(genericType instanceof TypeVariable){
                sb.append("TypeVariable").append("::") ;
            }
            if(genericType instanceof GenericArrayType){
                sb.append("GenericArrayType").append("::") ;
            }
            //TODO WildcardType 不能独立存在，必须以参数化类型的参数形式存在。
            if(genericType  instanceof WildcardType){
                sb.append("WildcardType").append("::") ;
                System.out.println("genericType is a WildcardType ==== ");
            }

            //获取字段声明的变量名
            String fieldName = field.getName();

            //获取字段泛型的使用形式 fieldType = java.util.List<? extends java.lang.Number>
            String fieldType = genericType.getTypeName();
            sb.append(fieldName).append(" : ").append(fieldType) ;

            System.out.println(sb.toString());
            System.out.println("-------------------------------------------");

        }
    }
}
