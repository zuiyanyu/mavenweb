package javaBase.反射.reflect_genertic;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * TODO WildcardType: 通配符类型，表示通配符类型，比如 <?>, <? Extends Number>等
 * TODO 通配符类型 一定是以 ParameterizedType类型的泛型参数形式存在的 比如：List<？>  List <? Extends Number>
 * getLowerBounds(): Type[]: 得到下边界的数组
 * getUpperBounds(): Type[]: 得到上边界的type数组
 * 注：如果没有指定上边界，则默认为Object，如果没有指定下边界，则默认为空
 */
public class Type_WildcardType_reflect<T> {
    public void info(List<? extends OutputStream> numberList,
                     List<? super InputStream> upperList,
                     List<Integer> list,
                     T name,
                     InputStream inputStream) {

    }

    public Method getMethodByName(String methodName){
        Method[] declaredMethods = Type_WildcardType_reflect.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if(methodName.equals(declaredMethod.getName())){
                return declaredMethod ;
            }
        }
        return null ;
    }
    @Test
    public void testWildcardType() throws NoSuchMethodException {
        //获取info方法
        Method method =getMethodByName("info");

        //获取方法的所有参数类型
        Type[] types = method.getGenericParameterTypes();

        for (Type paramsType : types) {
            System.out.println("-----------------------");
            System.out.println("type: " + paramsType.toString());

            //如果不是参数化类型则直接continue，执行下一个循环条件
            if (!(paramsType instanceof ParameterizedType)) {
                System.out.println("参数类型不是 ParameterizedType 类型");
                continue;
            }
            //将当前类型强转为参数化类型并获取其实际参数类型(即含有通配符的泛型类型)  示例中用的都是一个泛型
            Type type = ((ParameterizedType) paramsType).getActualTypeArguments()[0];

            //输出其是否为通配符类型
            System.out.println("type instanceof WildcardType : " + (type instanceof WildcardType));

            // 获取通配符类型的 上下边界信息
            //List<Integer> list 是ParameterizedType类型，但是实际参数不是通配符类型。
            if (type instanceof WildcardType) {
                System.out.println("获取下边界：");
                getLowerBounds((WildcardType) type);

                System.out.println("获取上边界：");
                getUpperBounds((WildcardType) type);
            }


        }
    }

    //TODO 获取下边界
    public void getLowerBounds(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        int i = 1;
        for (Type lowerBound : lowerBounds) {
            System.out.println("lowerBound  " + i + ": " + lowerBound.getTypeName());
            i++;
        }
    }

    //TODO 获取上边界
    public void getUpperBounds(WildcardType wildcardType) {
        Type[] upperBounds = wildcardType.getUpperBounds();
        int i = 1;
        for (Type upperBound : upperBounds) {
            System.out.println("upperBound  " + i + ": " + upperBound.getTypeName());
            i++;
        }
    }

}
