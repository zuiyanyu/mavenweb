package javaBase.对象与类.lambda;

/**
 *  TODO ==================== 第二部分：lambda 中的方法引用 =====================
 *  TODO 有时， 可能已经有现成的方法可以完成你想要传递到其他代码的某个动作
 *  假设你想对字符串排序， 而不考虑字母的大小写。可以传递以下方法
 * 表达式：
 * Arrays.sort(strings，String::conpareToIgnoreCase)  等价于：Arrays.sort(strings，(String X,String Y)->X.conpareToIgnoreCase(Y))
 * TODO  要用:: 操作符分隔方法名与对象或类名。主要有 3 种情况：
 * •object::instanceMethod
 * •Class::staticMethod
 * •Class::instanceMethod
 * 在前 2 种情况中， 方法引用等价于提供方法参数的 lambda 表达式。前面已经提到，
 * System.out::println 等价于 x -> System.out.println(x) 类似地，Math::pow 等价于（x，y) ->
 * Math.pow(x, y)
 * 对于第 3 种情况， 第 1 个参数会成为方法的目标。例如，String::compareToIgnoreCase 等
 * 同于 (x, y)-> x.compareToIgnoreCase(y) ;
 *
 *
 * TODO 如果有多个同名的重栽方法， 编译器就会尝试从上下文中找出你指的那一个方法。
 * 例如
 * ， Math.max 方法有两个版本， 一个用于整数， 另一个用于 double 值。选择哪一个版
 * 本取决于 Math::max 转换为哪个函数式接口的方法参数。 类似于 lambda 表达式， 方法引
 * 用不能独立存在，总是会转换为函数式接口的实例。
 *
 * TODO 可以在方法引用中使用 this 参数。 例如，this::equals 等同于 x-> this.equals(x)。 使用
 * TODO super 也是合法的。下面的方法表达式: super::instanceMethod
 * 使用  super 作为目标，会调用给定方法的超类版本。
 * 为了展示这一点，下面给出一个假想的例子：
 * class Greeter
 * {
 *       public void greet()
 *      {
 *        System.out.println("Hello, world!")；
 *      }
 * }
 * class TimedCreeter extends Greeter
 *  {
 *       public void greet(){
 *           Timer t = new Timer(1000, super::greet) ;
 *           t.start();
 *       }
 * }
 * TimedGreeter.greet 方法开始执行时， 会构造一个 Timer, 它会在每次定时器滴答时执行
 * super::greet 方法。这个方法会调用超类的 greet 方法。
 */
public class Lambda_方法引用 {
    public static void main(String[] args) {
        AB objA = new AB();
        int age = 9 ;

//        lambdaMethod(a,age,(x,y)->a.addAge(y));
        //TODO String::compareToIgnoreCase 等同于 (x, y)-> x.compareToIgnoreCase(y)
        // R function(P1 p1,P2 p2);=> int addAge(int  age) =>int objA.addAge(int)
        Integer integer = lambdaMethod(objA, age, AB::addAge);
        System.out.println(integer);
    }
    public static <P1,P2,R> R lambdaMethod(P1 p1,P2 p2,LambdaMethod<P1,P2,R> lam){
        return lam.function(p1,p2);
    }
}
@FunctionalInterface
interface LambdaMethod<P1,P2,R> {
    R function(P1 p1,P2 p2);

//    int add(Object obj);
    boolean equals(Object obj);

    int age = 3 ;
    default int add(int b){return this.age+1;}
}
class AB {
    int age =1;

    public  int addAge(int  age){
       return this.age +  age ;
    }

}
