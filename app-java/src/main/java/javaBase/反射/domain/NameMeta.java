package javaBase.反射.domain;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //运行时检验
@Inherited
@Target(value = {ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@interface NameMeta {
    String value() default "无";
}
