package javaBase.反射;

import javaBase.反射.domain.annotations.AgeValidator;
import org.apache.commons.lang.StringUtils;

import javax.lang.model.type.TypeVariable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Reflect_util {
    public static void printMethod(Method method){
        StringBuilder sb = new StringBuilder();

        //声明方法的类
        String className = method.getDeclaringClass().getSimpleName();
        sb.append(className).append(": ");

        //获取方法权限
        int modifier_int = method.getModifiers();
        String modifier = Modifier.toString(modifier_int);
        if(StringUtils.isEmpty(modifier)){
            modifier = "default" ;
        }

        Class<?> returnType = method.getReturnType();
        String simpleName = returnType.getSimpleName();

        //获取方法名
        String name = method.getName();//method_public

        sb
            .append(modifier).append(" ")
            .append(simpleName).append(" ")
            .append(name).append(" ")
            .append("(")
        ;

        //获取方法入参
        Parameter[] parameters = method.getParameters();
        for(Parameter parameter : parameters){
            String pm_type = parameter.getType().getSimpleName();
            String pm_name = parameter.getName();
            sb.append(pm_type).append(" ").append(pm_name).append(",") ;
        }
        int i = sb.lastIndexOf(",");
        if(i>-1){
            sb.replace(i, i+1, "");
        }

        sb.append(")") ;

        System.out.println(sb.toString());
    }
    public static void printField(Field field){
        //字段名
        String fieldName = field.getName();

        //字段修饰符
        int modifier_int = field.getModifiers();
        String modifier = Modifier.toString(modifier_int);

        Class<?> type = field.getType();
        String typeName = type.getName();

        System.out.println(modifier+ " " + typeName + "  " + fieldName);
    }
    public static void printConstructor(Constructor constructor){
        Class declaringClass = constructor.getDeclaringClass();
        String className = declaringClass.getSimpleName();

        System.out.println(className+ ":" + constructor);
    }
    public static void printAnnotation(Annotation annotation){
         Class<? extends Annotation> aClass = annotation.annotationType();
//        boolean annotation1 = aClass.isAnnotation();
//        System.out.println(annotation1);

        if(annotation instanceof AgeValidator){
            AgeValidator ageValidator = (AgeValidator)annotation ;
            int max = ageValidator.max();
            int min = ageValidator.min();
            System.out.println("@AgeValidator : min = "+min +", max = "+max);
        }else {

            String name = aClass.getSimpleName();
            System.out.println("@"+name +" : " + annotation);
        }
    }

    //TODO 判断type 是哪一种泛型接口
    public static void printGeneriticType(Type type){
        String typeName = type.getTypeName();
        if(type instanceof ParameterizedType){
            System.out.println("ParameterizedType: " + typeName);
        }
        if(type instanceof GenericArrayType){
            System.out.println("GenericArrayType: " + typeName);
        }
        if(type instanceof TypeVariable){
            System.out.println("TypeVariable: " + typeName);
        }
        if(type instanceof GenericArrayType){
            System.out.println("GenericArrayType: " + typeName);
        }
    }
    //TODO 根据方法名获取方法
    public static Method getMethodByName(Class clazz , String methodName){
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if(methodName.equals(declaredMethod.getName())){
                return declaredMethod ;
            }
        }
        return null ;
    }
}
