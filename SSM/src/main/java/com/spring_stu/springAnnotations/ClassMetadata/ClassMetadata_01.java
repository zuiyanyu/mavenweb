package com.spring_stu.springAnnotations.ClassMetadata;

import com.mavenweb.domain.User;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 *TODO ClassMetadata AnnotatedTypeMetadata 可以理解为对 Class元数据 和 Annotation元数据 的抽象
 *
 *TODO  public class StandardClassMetadata implements ClassMetadata
 *TODO StandardClassMetadata 和 ClassMetadata 仅仅描述了Class元数据
 *
 *TODO  public interface AnnotationMetadata extends ClassMetadata, AnnotatedTypeMetadata
 *TODO  public class StandardAnnotationMetadata extends StandardClassMetadata implements AnnotationMetadata
 *TODO  AnnotationMetadata 和 StandardAnnotationMetadata 描述了 Class元数据 和 Annotation元数据
 */
public final class ClassMetadata_01  extends User implements Cloneable, Serializable {
    class innerClass{}
    public static void main(String[] args) {
        StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(ClassMetadata_01.class, true);

        // 演示ClassMetadata的效果
        System.out.println("==============ClassMetadata==============");
        ClassMetadata classMetadata = metadata;

        //com.spring_stu.springAnnotations.ClassMetadata.ClassMetadata_01
        System.out.println(classMetadata.getClassName());

        //null  如果自己是内部类此处就有值了
        System.out.println(classMetadata.getEnclosingClassName());

        //[com.spring_stu.springAnnotations.ClassMetadata.ClassMetadata_01$innerClass] 若木有内部类返回空数组[]
        String[] memberClassNames = classMetadata.getMemberClassNames(); //获取成员类 即内部类
        System.out.println("memberClassNames = "+Arrays.asList(memberClassNames));

        // 获取类的接口： java.lang.Cloneable,java.io.Serializable
        System.out.println(StringUtils.arrayToCommaDelimitedString(classMetadata.getInterfaceNames()));

        // true(只有Object这里是false)
        System.out.println(classMetadata.hasSuperClass());

        //获取父类名称 java.lang.Object
        System.out.println(classMetadata.getSuperClassName());

        // false（是否是注解类型的Class，这里显然是false）
        System.out.println(classMetadata.isAnnotation());

        // true  这里是final class
        System.out.println(classMetadata.isFinal());

        // true(top class或者static inner class，就是独立可new的)
        System.out.println(classMetadata.isIndependent());


    }
}
