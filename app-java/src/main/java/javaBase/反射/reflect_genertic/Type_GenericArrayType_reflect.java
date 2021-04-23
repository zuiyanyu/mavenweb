package javaBase.反射.reflect_genertic;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * GenericArrayType：泛型数组类型：(组成数组的元素中有泛型则实现了该接口)
 * 它的组成元素是 ParameterizedType 或 TypeVariable 类型。
 *    比如：元素是 ParameterizedType 类型   List<String>[] ;
 *   	   元素是 TypeVariable 类型    T[] ;
 *
 * getGenericComponentType(): Type:
 * 返回组成泛型数组的实际参数化类型，如List[] 则返回 List。
 * 注意：无论从左向右有几个[]并列，这个方法仅仅脱去最右边的[]之后剩下的内容就作为这个方法的返回值。
 */
public class Type_GenericArrayType_reflect<T> {
    //泛型数组类型
    private T[] value;            //组成元素是 TypeVariable 类型
    private List<String>[] list;  //组成元素是 ParameterizedType 类型

    //不是泛型数组类型
    private Integer[] integers ;//组成元素是Class,不是ParameterizedType 或 TypeVariable 类型
    private List<String> singleList;
    private T singleValue;

    /**
     * TODO getGenericComponentType: 获取 泛型数组的组成元素的的泛型类型
     * Field: value; instanceof GenericArrayType: true
     * Field: value; getGenericComponentType() : T
     * ----------------------------
     * Field: list; instanceof GenericArrayType: true
     * Field: list; getGenericComponentType() : java.util.List<java.lang.String>
     * ----------------------------
     * Field: integers; instanceof GenericArrayType: false
     * ----------------------------
     * Field: singleList; instanceof GenericArrayType: false
     * ----------------------------
     * Field: singleValue; instanceof GenericArrayType: false
     * ----------------------------
     */
    @Test
    public void getGenericComponentType() {
        Field[] fields = Type_GenericArrayType_reflect.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Type fieldGenericType = field.getGenericType();
            //输出当前变量是否为GenericArrayType类型
            System.out.println("Field: " + field.getName()
                    + "; instanceof GenericArrayType: "
                    + (fieldGenericType instanceof GenericArrayType));

            if (field.getGenericType() instanceof GenericArrayType) {
                //如果是GenericArrayType，则输出泛型数组的组成元素的的泛型类型
                System.out.println("Field: "+ field.getName()
                        + "; getGenericComponentType() : "
                        + (((GenericArrayType)fieldGenericType ).getGenericComponentType()));
            }
            System.out.println("----------------------------");

        }
    }
}
