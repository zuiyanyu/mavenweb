package javaBase.反射.domain.annotations;


import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 声明重复注解，必须：
 * 1. @Target 中有 CONSTRUCTOR 值
 * 2. @FriendList 和 @Friend 的作用域必须相同。
 * 3. 都是RUNTIME运行范围
 *
 * •@Inherited:被它修饰的 Annotation将具有继承性.如果某个类使用了被@Inherited 修饰的Annotation, 则其子类将自动具有该注释
 */
@Retention(RUNTIME)
@Inherited
@Repeatable(Friend.FriendList.class)
@Target({CONSTRUCTOR,FIELD,TYPE,METHOD,PARAMETER})
public @interface Friend {
    String name() default "张三" ;
    int age() default 20 ;

    @Target({CONSTRUCTOR,FIELD,TYPE,METHOD,PARAMETER})
    @Retention(RUNTIME)
    @Inherited
    @interface FriendList{
        Friend[] value();
    }

}
