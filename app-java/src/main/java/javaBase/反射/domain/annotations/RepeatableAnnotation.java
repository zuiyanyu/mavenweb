package javaBase.反射.domain.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RetentionPolicy.RUNTIME) //运行时检验
@Inherited
@Repeatable(RepeatableAnnotation.List22.class) //必须有 CONSTRUCTOR 才能进行重复注解的声明
@Target(value = {METHOD,FIELD,TYPE,ANNOTATION_TYPE,CONSTRUCTOR,TYPE_USE,PARAMETER,})  //作用在方法上
public @interface RepeatableAnnotation {

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Inherited
    @Documented
    @interface List22 {
        RepeatableAnnotation[] value();
    }
}
