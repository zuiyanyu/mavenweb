package javaBase.反射.domain.annotations;

import java.lang.annotation.*;

/**
 *  •从 JDK5.0 开始,Java 增加了对元数据(MetaData)的支持,也就是Annotation(注释)
 *     •Annotation其实就是代码里的特殊标记,这些标记可以在编译,类加载, 运行时被读取,并执行相应的处理.通过使用Annotation,程序员可以在不改变原有逻辑的情况下,在源文件中嵌入一些补充信息.
 *     •Annotation 可以像修饰符一样被使用,可用于修饰包,类,构造器, 方法,成员变量, 参数,局部变量的声明,这些信息被保存在Annotation的 “name=value”对中.
 *     •Annotation能被用来为程序元素(类,方法,成员变量等)设置元数据
 * 基本的 Annotation
 *
 * •使用 Annotation时要在其前面增加@符号,并把该Annotation 当成一个修饰符使用.用于修饰它支持的程序元素
 * •三个基本的Annotation:
 *     –@Override:限定重写父类方法,该注释只能用于方法
 *     –@Deprecated:用于表示某个程序元素(类,方法等)已过时
 *     –@SuppressWarnings:抑制编译器警告.
 * 自定义 Annotation
 *
 *     •定义新的 Annotation类型使用@interface关键字
 *     •Annotation 的成员变量在Annotation 定义中以无参数方法的形式来声明.其方法名和返回值定义了该成员的名字和类型.
 *     •可以在定义Annotation的成员变量时为其指定初始值,指定成员变量的初始值可使用default关键字
 *     •没有成员定义的Annotation称为标记;包含成员变量的Annotation称为元数据Annotation
 */
//自定义一个注解：检查年龄范围
@Retention(RetentionPolicy.RUNTIME) //运行时检验
//@Inherited
@Target(value = {ElementType.METHOD,ElementType.FIELD,ElementType.TYPE,ElementType.PARAMETER})  //作用在方法上
public @interface AgeValidator {
    int min() default 0;
    int max() default 150;
}
