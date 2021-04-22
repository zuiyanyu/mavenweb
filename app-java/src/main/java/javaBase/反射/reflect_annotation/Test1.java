package javaBase.反射.reflect_annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Test1 {
    public static void main(String[] args) {
        Test3 test = new Test3();

        for (Annotation annotation : test.getClass().getAnnotations()) {
            System.out.println("Class getAnnotations: " + annotation);
        }

        for (Annotation annotation : test.getClass().getDeclaredAnnotations()) {
            System.out.println("Class getDeclaredAnnotations: " + annotation);
        }

        for (Field field : test.getClass().getFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                System.out.println("Field getAnnotations: " + annotation);
            }

            for (Annotation annotation : field.getDeclaredAnnotations()) {
                System.out.println("Field getDeclaredAnnotations: " + annotation);
            }
        }
    }
}

@CustomAnnotation("Class")
class Test2 {
    @CustomAnnotation("Field") public String testString;
}
class Test3 extends Test2 { }