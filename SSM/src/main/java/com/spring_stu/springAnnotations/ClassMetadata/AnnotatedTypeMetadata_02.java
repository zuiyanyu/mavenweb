package com.spring_stu.springAnnotations.ClassMetadata;

import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class AnnotatedTypeMetadata_02 {
    public static void main(String[] args) {
        AnnotationMetadata metadata = AnnotationMetadata.introspect(AnnotatedTypeMetadata_02.class) ;
                // 底层返回值为：  new StandardAnnotationMetadata(AnnotatedTypeMetadata_02.class);
        // 演示AnnotatedTypeMetadata的效果
        System.out.println("==============AnnotatedTypeMetadata 获取类注解的元数据信息 ==============");
        AnnotatedTypeMetadata annotatedTypeMetadata = metadata;


        System.out.println("==============1 纵向 判断当前类是否有被某个注解标注===================");
        //TODO 纵向 判断注解类的元数据中是否有指定注解
        System.out.println(annotatedTypeMetadata.isAnnotated(Service.class.getName())); //true
        // 可以看到，类上只标注了@Service，但@Component也被认为被标注了，这是因为 @Service上标注了@Component注解。
        System.out.println(annotatedTypeMetadata.isAnnotated(Component.class.getName())); //true

        System.out.println("================2 获取当前类被标注的注解(即直接注解)的属性(一层深度)=================");
        //获取指定注解的属性： {value=AnnotatedTypeMetadata_Service}
        Map<String, Object> serviceAttributes = annotatedTypeMetadata.getAnnotationAttributes(Service.class.getName());
        System.out.println(serviceAttributes);
        // {value=AnnotatedTypeMetadata_Service}
        System.out.println(annotatedTypeMetadata.getAnnotationAttributes(Component.class.getName()));

        System.out.println("================3 获取当前类被标注的注解的属性(多层深度)=================");
        // 看看getAll的区别：value都是数组的形式
        //allAnnotationAttributes_Service:{value=[AnnotatedTypeMetadata_Service]}
        MultiValueMap<String, Object> allAnnotationAttributes_Service =
                annotatedTypeMetadata.getAllAnnotationAttributes(Service.class.getName());
        System.out.println("allAnnotationAttributes_Service:"+allAnnotationAttributes_Service);

        /**
         * @Service的定义：
         *    @Component
         *    public @interface Service{}
         * 看到 Service也是被标注了 @Component注解，而AnnotatedTypeMetadata_02被标注了 @Service和 @Component两个注解，所以
         * 这里的value返回值有两个，一个为空串
         * // {value=[, ]} --> 两个Component的value值都拿到了，只是都是空串而已
         */
        // allAnnotationAttributes_Component = {value=[AnnotatedTypeMetadata_Component, ]}
        MultiValueMap<String, Object> allAnnotationAttributes_Component =
                annotatedTypeMetadata.getAllAnnotationAttributes(Component.class.getName());
        //从这里可以看到有 多层深度的注解值。
        Set<Map.Entry<String, List<Object>>> entries = allAnnotationAttributes_Component.entrySet();
        //allAnnotationAttributes_Component = {value=[AnnotatedTypeMetadata_Component, ]}
        System.out.println("allAnnotationAttributes_Component = "+allAnnotationAttributes_Component);


    }
}
