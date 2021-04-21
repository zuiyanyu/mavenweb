package com.spring_stu.spring_PropertyEditor.string2enum;

import java.lang.annotation.*;

/**
 * 将打上注解的GenderEnum通过特定的字段转换为枚举
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface GenderEnumFormat {
    String value();
}
