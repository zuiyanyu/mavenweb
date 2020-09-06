package javaBase.对象与类.断言;

import org.junit.Assert;

/**
 * TODO 关键字 assert
 *
 * TODO 1. 断言的好处
 * 1.1  if (x < 0) throw new 111egalArgumentException("x < 0");
 *      这段代码会一直保留在程序中， 即使测试完毕也不会自动地删除。如果在程序中含
 *      有大量的这种检查，程序运行起来会相当慢。
 *
 * 1.2  断言机制允许在测试期间向代码中插入一些检査语句。当代码发布时，这些插人的检测
 *      语句将会被自动地移走
 *
 *TODO 2. Java 语言引人了关键字 assert。这个关键字有两种形式：
 * assert 条件；
 * 和
 * assert 条件：表达式；
 * TODO 这两种形式都会对条件进行检测， 如果结果为 false, 则抛出一个 AssertionError 异常。
 * TODO 在第二种形式中，表达式将被传人 AssertionError 的构造器， 并转换成一个消息字符串。
 *TODO  assert 2 > i:"2必须大于i" ;
 * //Exception in thread "main" java.lang.AssertionError: 2必须大于i
 *
 *TODO 3.示例：
 * TODO 要想断言 x 是一个非负数值， 只需要简单地使用下面这条语句
 * TODO assert x >= 0;
 * TODO 或者将 x 的实际值传递给 AssertionError 对象， 从而可以在后面显示出来。
 * TODO assert x >= 0 : x;
 *
 * TODO 4.启用和禁用断言
 * TODO 4.1 在默认情况下， 断言被禁用。可以在运行程序时用 -enableassertions 或 -ea 选项启用
 * TODO 4.2 需要注意的是， 在启用或禁用断言时不必重新编译程序。启用或禁用断言是类加载器( class loader) 的功能。
 * TODO     当断言被禁用时， 类加载器将跳过断言代码， 因此，不会降低程序运行的速度。
 *
 * 也可以在某个类或整个包中使用断言， 例如：
 * java -ea:MyClass -eaiconi.inycompany.inylib.. , MyApp
 * 这条命令将开启 MyClass 类以及在 com.mycompany.mylib 包和它的子包中的所有类的断
 * 言。选项 -ea 将开启默认包中的所有类的断言。
 * 也可以用选项 -disableassertions 或 -da 禁用某个特定类和包的断言：
 * java -ea:... -da:MyClass MyApp
 * 有些类不是由类加载器加载， 而是直接由虚拟机加载。可以使用这些开关有选择地启用
 * 或禁用那些类中的断言。
 * 然而， 启用和禁用所有断言的 -ea 和 -da 开关不能应用到那些没有类加载器的“ 系统类”
 * 上。对于这些系统类来说， 需要使用-enablesystemassertions/-esa 开关启用断言。
 * 在程序中也可以控制类加载器的断言状态。
 *
 * TODO 5. 什么时候应该选择使用断言呢？ 请记住下面几点：
 * TODO     •断言失败是致命的、 不可恢复的错误。
 * TODO     •断言检查只用于开发和测阶段. 断言只应该用于在测试阶段确定程序内部的错误位置
 */
public class AssertTest {
    int i = 0 ;
    public static void main(String[] args) {
        int i = 3 ;
        //TODO 需要打开断言功能，即 VM options 需要设置参数才能有效：-ea  启用断言 （设置为 -da表示禁用断言）
        assert 2 > i:"2必须大于i" ;//Exception in thread "main" java.lang.AssertionError: 2必须大于i

        System.out.println(i);

        //TODO 直接使用，不需要打开断言功能，会默认打开
        Assert.assertTrue("2必须大于i",2>i); //Exception in thread "main" java.lang.AssertionError: 2必须大于i
    }
}
