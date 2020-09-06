package javaBase.泛型;

import javaBase.domain.Pair;

import java.io.Serializable;

/**
 * TODO 类型参数 （type parameters)
 * TODO 通配符类型 （wildcard type)
 * TODO 类型变量的限定（bound)            public static <T extends Coiparab1e> T min(T[] a)
 * TODO 类 型 擦 除  为了提高效率， 应该将标签（tagging) 接口（即没有方法的接口）放在边界列表的末尾。
 * TODO 翻译泛型表达式   当程序调用泛型方法时， 如果擦除返回类型， 编译器插入强制类型转换，比如对返回结果的强制类型转换。
 *      String middle2 = (String)getMiddle("a", "b", "c");
 * TODO 翻译泛型方法   类型擦除也会出现在泛型方法中
 *       public static <T extends Comparable> T min(T[] a)  是一个完整的方法族，而擦除类型之后， 只剩下一个方法：
 *       public static Comparable min(Comparable[] a)   类型参数 T 已经被擦除了， 只留下了限定类型 Comparable。
 *
 * TODO <T extends BoundingType〉表示 T 应该是绑定类型的子类型 （subtype)。 T 和绑定类型可以是类， 也可以是接口。
 * TODO 一个类型变量或通配符可以有多个限定， 例如：T extends Comparable & Serializable  限定类型用“ &” 分隔
 *      在 Java 的继承中， 可以根据需要拥有多个接口超类型， 但限定中至多有一个类。如果用一个类作为限定，它必须是限定列表中的第一个。
 *
 * TODO 类型变量使用大写形式，且比较短。在 Java 库中， 使用变量 E 表示集合的元素类型，
 * TODO K 和 V 分别表示表的关键字与值的类型。T ( 需要时还可以用临近的字母 U 和 S ) 表示“ 任意类型”。
 *================================================
 * 程序员可能想要将
 * ArrayList<Manager> 中的所有元素添加到 ArrayList<Employee> 中去。然而， 反过来就不行弟?章泛型程序?? 311
 * 了。如果只能允许前一个调用， 而不能允许后一个调用呢？ Java 语言的设计者发明了一个具
 * 有独创性的新概念，通配符类型 （wildcard type), 它解决了这个问题。
 *
 *================================================
 *原始类型用第一个限定的类型变量来替换， 如果没有给定限定就用 Object 替换。 例如，
 * 类 Pair<T> 中的类型变量没有显式的限定， 因此， 原始类型用 Object 替换 T。假定声明了一
 * 个不同的类型。
 * public class Interval <T extends Comparable & Serializable〉implements Serializable
 * {
 * private T lower;
 * private T upper;
 * public Interval (T first, T second)
 * {
 * if (first.compareTo(second) <= 0) { lower = first; upper = second; }
 * else { lower = second; upper = first; }
 * }
 * }
 * 原始类型 Interval 如下所示：
 * public class Interval implements Serializable
 * {
 * private Comparable lower;
 * private Coiparable upper;
 * public Interval (Coiparable first, Coiparable second) { . . . }
 * }
 * 切换限定： class Interval<T extends Serializable & Comparable>
 * 会发生什么。 如果这样做， 原始类型用 Serializable 替换 T, 而编译器在必要时要向
 * Comparable 插入强制类型转换。 为了提高效率， 应该将标签（tagging) 接口（即没有方
 * 法的接口）放在边界列表的末尾。
 *
 * ================================================
 *
 * TODO 泛型程序设计划分为 3 个能力级别:
 *    TODO 基本级别是: 仅仅使用泛型类—典型的是像ArrayList 这样的集合—不必考虑它们的工作方式与原因。
 *
 */
public class GenericInfo {
    public static void main(String[] args) {
        initGenericMethod();
    }

    //TODO 1. 泛型类
    public void initGenricClass(){
        //TODO 1.用具体的类型替换类型变量就可以实例化泛型类型
        Pair<String> stringPair = new Pair<>();

        //TODO 2.

    }

    /**
     *  TODO 2. 泛型方法
     * 这个方法是在普通类中定义的， 而不是在泛型类中定义的。然而，这是一个泛型方法，可以从尖括号和类型变量看出这一点
     * 注意，类型变量放在修饰符（这里是 public static) 的后面，返回类型的前面
     *
     * TODO 泛型方法可以定义在普通类中，也可以定义在泛型类中
     * TODO 当调用一个泛型方法时,在方法名前的尖括号中放人具体的类型：
     *      在这种情况（实际也是大多数情况）下，方法调用中可以省略 <String> 类型参数。编译
     *      器有足够的信息能够推断出所调用的方法。它用 names 的类型（即 String[ ]) 与泛型类型 T[ ]
     *      进行匹配并推断出 T 一定是 String。
     */
    public static <T> T getMiddle(T... names)
    {
        return names[names.length / 2];
    }
    public static void initGenericMethod(){
        //TODO 当调用一个泛型方法时,在方法名前的尖括号中放人具体的类型：
        String middle = GenericInfo.<String>getMiddle("a", "b", "c");

        //TODO 方法调用中可以省略 <String> 类型参数。编译器有足够的信息能够推断出所调用的方法。
        String middle2 = GenericInfo.getMiddle("a", "b", "c");

        Number middle1 = GenericInfo.getMiddle(3.14, 1729, 0);
        Serializable hello = GenericInfo.getMiddle("Hello", 0, null);
    }

}

