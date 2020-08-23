package java基础.对象与类.lambda;

import java.util.Comparator;
import java.util.function.BiFunction;

/**
 * TODO 函数式接口
 *
 *
 * TODO 1. Java 中 已 经 有 很 多 封 装 代 码 块 的 接 口
 * TODO 2. 对于只有一个抽象方法的接口， 需要这种接口的对象时， 就可以提供一个 lambda 表达式。这种接口称为函数式接口 （functional interface )。
 * TODO 3. 函数式接口必须有且只有一个抽象方法 。
 * TODO 4. lambda 表达式可以传递到函数式接口
 * TODO 5. lambda 表达式可以转换为接口
 * TODO 6. 在 Java 中， 对 lambda 表达式所能做的也只是能转换为函数式接口。
 * TODO 7. Java API 在java.util.fimction 包中定义了很多非常通用的函数式接口。
 *         其中一个接口 BiFunction<T, U, R> 描述了参数类型为 T 和 U 而且返回类型为 R 的函数。
 *             java.util.function 包中有一个尤其有用的接口 Predicate（断定）:
 *             public interface Predicate<T>
 *             {
 *               boolean test(T t);// Additional default and static methods
 *             }
 *ArrayList 类有一个 removelf 方法， 它的参数就是一个 Predicate。这个接口专门用来传递
 * lambda 表达式。例如，下面的语句将从一个数组列表删除所有 null 值：
 * list.removelf(e -> e == null);
 */
public class Functional_Interface {
    public static void main(String[] args) {
        BiFunction<String,String,Integer> comp = (first, second) -> first.length() - second.length() ;

        Comparator<String> comp2 = (first, second) -> first.length() - second.length() ;
        int compare = comp2.compare("ab", "b");
        System.out.println(compare);
    }
}

@FunctionalInterface
interface  FunctionalInterfaceA{
    void consumerA();
//    String functionB();
}