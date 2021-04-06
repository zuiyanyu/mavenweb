package com.spring_stu.springAnnotations.组件注册.ComponentScan_FilterType_02.filterType;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

/**
 * 自定义 @ComponentScan的Filter
 * 示例：
 * @ComponentScan(basePackages = "com.spring_stu.springAnnotations.组件注册.dsc",
 *             excludeFilters ={@ComponentScan.Filter(type =FilterType.CUSTOM ,value={CustomTypeFilter.class})}
 *            )
 * public class FilterConfigerClass(){
 *
 * }
 */
public class CustomTypeFilter implements TypeFilter {
    /**
     *  filter的过滤规则逻辑
     *
     * @param metadataReader  读取到的正在被扫描的类的信息
     * @param metadataReaderFactory  可以获取到其他任何类的类信息
     * @return  false - 符合规则，true-不符合规则
     * 对于 @ComponentScan的excludeFilters，会过滤掉符合规则的类。
     * 对于 @ComponentScan的includeFilters，会保留符合规则的类。
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //TODO 获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //annotationMetadata.getAnnotationTypes = [org.springframework.stereotype.Service]
        System.out.println("annotationMetadata.getAnnotationTypes = "+ annotationMetadata.getAnnotationTypes());
        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes(); //返回的值有多个，因为一个类有多个注解


        //TODO 获取当前正在扫描的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //classMetadata.getClassName = com.spring_stu.springAnnotations.组件注册.dsc.service.PersonServiceImpl
        System.out.println("classMetadata.getClassName = "+ classMetadata.getClassName());

        //TODO 获取当前类资源(类文件.class的信息)
        Resource resource = metadataReader.getResource();
        //resource.getFilename= PersonServiceImpl.class
        System.out.println("resource.getFilename= "+ resource.getFilename());

        //编写过滤规则  以per进行开头的类
        boolean result = resource.getFilename().startsWith("Per") ;
        System.out.println("result = " + result);
        return result;
    }
}
