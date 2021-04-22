package javaBase.反射.domain.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //运行时检验
@Inherited
@Target(value = {ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
public @interface NameMeta {
    String value() default "无";
}
