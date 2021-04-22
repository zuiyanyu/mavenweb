package javaBase.反射.reflect_annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Inherited 标注的注解，表示能被标注类的子类继承。 即此注解能一直向下传递。
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CustomAnnotation {
    String value();
}
