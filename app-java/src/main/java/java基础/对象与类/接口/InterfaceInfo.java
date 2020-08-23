package java基础.对象与类.接口;

import java.util.Date;

/**
 * TODO 1. 接口（ interface) 技术， 这种技术主要用来描述类具有什么功能，而并不给出每个功能的具体实现。
 * TODO 2. 一个类可以实现（implement)—个或多个接口， 并在需要接口的地方， 随时使用实现了相应接口的对象。
 * TODO 3. 接口不是类，而是对类的一组需求描述，这些类要遵从接口描述的统一格式进行定义。
 *
 * TODO 4. Arrays 类中的 *sort 方法承诺可以对对象数组进行排序 ， 但要求：对象所属的类必须实现了 Comparable 接口。
 * Comparable<T> 接口
 * 在调用 X.c0mpareT0(y) 的时候，这个
 * compareTo 方法必须确实比较两个对象的内容， 并返回比较的结果：
 *        当 x 小于 y 时， 返回一个负数；当 x 等于 y 时， 返回 0; 否则返回一个正数。（可以简单理解为 x-y 的值）
 *
 * TODO 5. Java SE8 :现在已经可以在接口中提供简单方法了, 当然， 这些方法不能引用实例域,接口没有实例.
 *          TODO 提供实例域和方法实现的任务应该由实现接口的那个类来完成
 * TODO 6. 接口中不能包含实例域或静态方法，但却可以包含常量(接口中的域将被自动设为 public static final)
 *          TODO 但是JDK8 允许接口中定义 静态方法
 *         原来做法：通常的做法都是将静态方法放在伴随类中。在标准库中， 你会看到成对出现的接口和实用工具类， 如 Collection/Collections 或 Path/Paths。
 *         现在就可以不用再另外提供一个 伴随类了。
 * TODO 7.  Java 程序设计语言有一个非常重要的内置接口，称为 Cloneable.
 * 如果某个类实现了这个 Cloneable 接口，Object 类中的 clone()方法就可以创建类对象的一个拷贝。
 *
 * TODO 8. 可以为接口方法提供一个默认实现
 *
 * TODO 9. 解决默认方法冲突
 * 如果先在一个接口中将一个方法定义为默认方法， 然后又在超类或另一个接口中定义了
 * 同样的方法， 会发生什么情况？ 诸如 Scala 和 C++ 等语言对于解决这种二义性有一些复杂的
 * 规则。幸运的是，Java 的相应规则要简单得多。规则如下：
 * 1 ) 超类优先。如果超类提供了一个具体方法，同名而且有相同参数类型的默认方法会被忽略。
 * 2 ) 接口冲突。 如果一个超接口提供了一个默认方法，另一个接口提供了一个同名而且参数类型
 *    （不论是否是默认参数）相同的方法， 必须覆盖这个方法来解决冲突。
 *
 * TODO 下面来看第二个规则。 考虑另一个包含 getName 方法的接口：
 * interface Named
 * {
 * default String getName() { return getClass().getName() + "_" + hashCodeO ; }
 * }
 * 如果有一个类同时实现了这两个接口会怎么样呢？
 * class Student implements Person, Named
 * { }
 * 类会继承 Person 和 Named 接口提供的两个不一致的 getName 方法。并不是从中选择一
 * 个，Java 编译器会报告一个错误，让程序员来解决这个二义性。只需要在 Student 类中提供
 * 一个 getName 方法。在这个方法中，可以选择两个冲突方法中的一个， 如下所示：
 * class Student implements Person, Named
 * {
 * public String getName() { return Person.super.getName() ; }
 * }
 * 现在假设 Named 接口没有为 getName 提供默认实现：
 * interface Named
 * {
 * String getNameO;
 * }
 * Student 类会从 Person 接口继承默认方法吗？ 这好像挺有道理， 不过，Java 设计者更强
 * 调一致性。两个接口如何冲突并不重要。 如果至少有一个接口提供了一个实现， 编译器就会
 * 报告错误， 而程序员就必须解决这个二义性。
 * 当然，如果两个接口都没有为共享方法提供默认实现， 那么就与 Java SE 8之前的
 * 情况一样，这里不存在冲突。 实现类可以有两个选择： 实现这个方法， 或者干脆不实现。
 * 如果是后一种情况， 这个类本身就是抽象的。
 *
 */
public interface InterfaceInfo {

    //JDK8: 默认方法。 此方法什么也不用做，只是子类不用必须实现。
    default void defaultMethod(){}

    //默认方法可以调用任何其他方法。
    default Date getDate2(){
       return  getDate();
    }


    //JDK8: 静态方法。
    static Date getDate(){
        return new Date() ;
    }
}
