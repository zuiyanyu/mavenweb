package com.spring_stu.springAnnotations.annotationMetadata;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.stereotype.Service;

public class StandardAnnotationMetadataTest {
    @Service
    class A {

        @Bean
        void a() {

        }
    }

    @Test
    public void test1() {
        StandardAnnotationMetadata s
                = new StandardAnnotationMetadata(A.class);

        System.out.println(s.isIndependent());

        System.out.println(s.isAnnotated("org.springframework.stereotype.Service"));

        s.getMetaAnnotationTypes("org.springframework.stereotype.Service")
                .stream().forEach(x -> System.out.println(x));

        System.out.println(s.hasMetaAnnotation("org.springframework.stereotype.Component"));

    }

}
