package javaBase.反射.reflect_class;

import javaBase.反射.Reflect_util;
import javaBase.反射.domain.annotations.AgeValidator;
import javaBase.反射.domain.annotations.Friend;
import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 *  •从 JDK5.0 开始,Java 增加了对元数据(MetaData)的支持,也就是Annotation(注释)
 *     •Annotation其实就是代码里的特殊标记,这些标记可以在编译,类加载, 运行时被读取,并执行相应的处理.通过使用Annotation,程序员可以在不改变原有逻辑的情况下,在源文件中嵌入一些补充信息.
 *     •Annotation 可以像修饰符一样被使用,可用于修饰包,类,构造器, 方法,成员变量, 参数,局部变量的声明,这些信息被保存在Annotation的 “name=value”对中.
 *     •Annotation能被用来为程序元素(类,方法,成员变量等)设置元数据
 */
//TODO 反射机制获取类中的注解：注解(Annotation)
public class Class_annotation_reflect {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * //TODO 获取类上的直接注解 和 继承的注解
     * 返回此元素上存在的所有注释(直接注解 和 继承的注解)。(如果该元素没有注释，则返回长度为0的数组。)
     * 这个方法的调用者可以随意修改返回的数组;它对返回给其他调用者的数组没有影响。
     *
     * TODO @Inherited 标注的注解，表示该注解能被子类继承。getAnnotations()方法就能获取到继承的注解。
     */
    @Test
    public void getAnnotations(){
        Annotation[] annotations = clazz.getAnnotations();
        /**
         * @NameMeta : @javaBase.反射.domain.NameMeta(value=无)
         * @AgeValidator : min = 0, max = 150
         */
        for (Annotation annotation : annotations) {
            Reflect_util.printAnnotation(annotation);
        }
    }

    /**
     * 返回直接出现在此元素上的所有注释(直接注解)。与该接口中的其他方法不同，该方法忽略继承的注释。
     * (如果该元素上没有直接的注释，则返回长度为0的数组。)
     * 这个方法的调用者可以随意修改返回的数组;
     * 它对返回给其他调用者的数组没有影响。
     *
     * getDeclaredAnnotations()是唯一忽略继承注解的方法。
     */
    @Test
    public void getDeclaredAnnotations(){
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        /**
         * @AgeValidator : min = 0, max = 150
         */
        for (Annotation annotation : annotations) {
            Reflect_util.printAnnotation(annotation);
        }
    }

    /**
     * TODO 判断这个类是否是一个注解类
     */
    @Test
    public void isAnnotation(){
        Annotation annotation = clazz.getAnnotation(AgeValidator.class);
        Class<? extends Annotation> aClass ;

        //这种方式得到的是 false
        aClass = annotation.getClass();
        System.out.println( aClass.isAnnotation());//false

        //这种方式得到的是true
        aClass = annotation.annotationType();
        System.out.println( aClass.isAnnotation());//true

    }

    //TODO 获取传入的注解对象，如果不存在，则返回null
    @Test
    public void getAnnotation(){
        AgeValidator annotation = (AgeValidator)clazz.getAnnotation(AgeValidator.class);
        System.out.println(annotation);//@javaBase.反射.domain.AgeValidator(max=150, min=0)

        //TODO @Friend 是重复注解，getAnnotation无法获取，必须使用getAnnotationsByType获取注解值
        Annotation annotation1 = clazz.getAnnotation(Friend.class);
        System.out.println(annotation1);//null
    }

    @Test
    //TODO 是否包含传入的注解类,效果与getAnnotation()!=null相同
    public void isAnnotationPresent(){
        //isAnnotationPresent(Class<? extends Annotation> annotationClass)
        boolean annotationPresent = clazz.isAnnotationPresent(AgeValidator.class);
        System.out.println(annotationPresent);//true
    }

    /**
     *TODO 返回传入的注解对象数组，与getAnnotation()的区别是检测传入的注解是否是重复元素
     */
    @Test
    public void getAnnotationsByType(){

        Annotation[] annotationsByType = clazz.getAnnotationsByType(Friend.class);
        /**
         * @javaBase.反射.domain.annotations.Friend(name=lisi, age=23)
         * @javaBase.反射.domain.annotations.Friend(name=张三2, age=32)
         */
        for (Annotation annotation1 : annotationsByType) {
            System.out.println(annotation1); //@javaBase.反射.domain.AgeValidator(max=150, min=0)
        }
    }


}
