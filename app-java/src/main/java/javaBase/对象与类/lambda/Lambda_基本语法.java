package javaBase.对象与类.lambda;

/**
 * TODO 1. 绍而表达式，这是一种表示可以在将来某个时间点执行的代码块的简洁方法。
 * TODO 2. 使用 lambda 表达式， 可以用一种精巧而简洁的方式表示使用回调或变量行为的代码。
 *
 * TODO ==================== 第一部分：lambda 的基本语法 =====================
 * TODO 3. Java 中的一种 lambda 表达式形式：参数， 箭头(->) 以及一个表达式
 * TODO 如果代码要完成的计算无法放在一个表达式中，就可以像写方法一样，把这些代码放在 {} 中，并包含显式的 return 语句。
 *          (String first, String second) ->{
 *                if (first.lengthO < second.lengthO) return -1;
 *                else if (first.lengthO > second.lengthO) return 1;
 *                else return 0;
 *           }
 *TODO 即使 lambda 表达式没有参数， 仍然要提供空括号，就像无参数方法一样：
 *     ()-> { for (inti = 100;i >= 0;i ) System.out.println(i); }
 *
 * TODO lambda 表达式可以传递到函数式接口, lambda 表达式可以转换为接口
 * TODO 如果可以推导出一个 lambda 表达式的参数类型，则可以忽略其类型。例如：
 *     Comparator<String> comp= (first, second) -> first.lengthO - second.lengthO;
 *                            // Same as (String first, String second)
 *
 *
 * TODO 如果方法只有一 参数， 而且这个参数的类型可以推导得出，那么甚至还可以省略小括号：
 *       ActionListener listener = event -> System.out.println("The time is " + new Date()");
 *                                         // Instead of (event) -> . . . or (ActionEvent event) -> . . .
 *
 * TODO 无需指定 lambda 表达式的返回类型。lambda 表达式的返回类型总是会由上下文推导得出。例如，下面的表达式
 *      (String first, String second) -> first.lengthO - second.lengthO
 *
 * TODO 如果一个 lambda 表达式只在某些分支返回一个值， 而在另外一些分支不返回值，这是不合法的。 例如，
 *     （int x)-> { if (x >= 0) return 1; } 就不合法。
 *
 *
 */
public class Lambda_基本语法 {
}
