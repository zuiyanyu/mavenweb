package com.spring_stu.springAnnotations.ClassMetadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
@Service("AnnotatedTypeMetadata_Service")
public class AnnotationMetadata_03 {
    @Autowired
    public void getUser(){}

    @Resource
    public void getPerson(){}

    public static void main(String[] args) {
        StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(AnnotationMetadata_03.class);

        // 演示AnnotationMetadata子接口的效果（重要）
        System.out.println("==============AnnotationMetadata:获取类有哪些注解 ==============");
        AnnotationMetadata annotationMetadata = metadata;

        System.out.println("===================1=======================");
        //TODO 横向 获取当期类被哪些注解给标注了（一层深度）。
        //[org.springframework.stereotype.Service]
        System.out.println("getAnnotationTypes= "+annotationMetadata.getAnnotationTypes());

        //TODO 纵向 获取某个注解被哪些注解被标注了(会递归向上查询)  （meta就是获取注解上面的注解,会排除掉java.lang这些注解们）
        //TODO 比如：@Service 被@Component标注了， @Component被@Indexed标注，那么也认为 @Service被@Indexed标注了，所以获取结果如下：
        //[org.springframework.stereotype.Component, org.springframework.stereotype.Indexed]
        System.out.println("getMetaAnnotationTypes= "+ annotationMetadata.getMetaAnnotationTypes(Service.class.getName()));
        //[] TODO 查询的出发点必须是 此类上直接被标注的注解，因为本类没有 Component,所以是[]
        System.out.println(annotationMetadata.getMetaAnnotationTypes(Component.class.getName()));

        System.out.println("===================2 类层面=======================");
        //TODO 纵向 判断当前类是否被某个注解给标注 (这个方法 属于AnnotatedTypeMetadata的)
        System.out.println(annotationMetadata.isAnnotated(Service.class.getName()));//true
        System.out.println(annotationMetadata.isAnnotated(Indexed.class.getName()));//true
        //TODO 横向 判断是否被某个注解给标注
        //boolean hasAnnotation(String annotationName)   annotationName:直接注解的名称
        System.out.println(annotationMetadata.hasAnnotation(Service.class.getName())); // true
        System.out.println(annotationMetadata.hasAnnotation(Component.class.getName())); //false
        System.out.println(annotationMetadata.hasAnnotation(Indexed.class.getName())); // false
        System.out.println();
        //TODO 纵向 注意这一组的结果和上面相反，因为它看的是meta  注解的注解  判断直接注解的元注解中是否有这个指定的源注解
        // boolean hasMetaAnnotation(String metaAnnotationName) metaAnnotationName: 注解的注解名称
        System.out.println(annotationMetadata.hasMetaAnnotation(Service.class.getName())); //false
        System.out.println(annotationMetadata.hasMetaAnnotation(Component.class.getName())); // true
        System.out.println(annotationMetadata.hasMetaAnnotation(Indexed.class.getName())); // true

        System.out.println();
        System.out.println("=====================3. 类方法 =======================");
        //
        System.out.println(annotationMetadata.hasAnnotatedMethods(Autowired.class.getName())); // true
        annotationMetadata.getAnnotatedMethods(Autowired.class.getName()).forEach(methodMetadata -> {
            System.out.println("---------------------------------");
            System.out.println(methodMetadata.getClass()); // class org.springframework.core.type.StandardMethodMetadata
            System.out.println(methodMetadata.getMethodName()); // getPerson
            System.out.println(methodMetadata.getReturnTypeName()); // void
        });
    }
}
