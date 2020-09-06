package javaBase.对象与类.内部类;

/**
 * TODO 静态内部类
 * TODO 1.有时候， 使用内部类只是为了把一个类隐藏在另外一个类的内部，并不需要内部类引用外围类对象。
 * 为此，可以将内部类声明为 static, 以便取消产生的引用。  （只是解决命名冲突 和 表达归属关系）
 *
 * TODO 2. 只有内部类可以声明为 static。
 * TODO 3. 静态内部类的对象除了没有对生成它的外围类对象的引用特权外， 与其他所冇内部类完全一样。
 *
 * TODO 4. 静态方法是一种不能向自身类对象实施操作的方法。换句话说，没有隐式的参数。可以认为静态方法是没有 this 参数的方法
 * （ 在一个非静态的方法中， this参数表示这个方法的隐式参数） 但是，静态方法可以访问自身类中的静态域
 *
 * TODO 5. 在下面2种情下使用静态方法：
 * • 一 方法不需要访问对象状态，其所需参数都是通过显式参数提供（例如： Math.pow ).
 * • 一个方法只需要访问类的静态域（例如：Employee.getNextldh
 *
 * TODO 6. 在内部类不需要访问外围类对象的时候， 应该使用静态内部类
 * TODO 7. 与常规内部类不同，静态内部类可以有静态域和方法
 * TODO 8. 声明在接口中的内部类自动成为 static 和 public 类
 */
public class InnerClass_5 {
    private int a ;
    public static void main(String[] args) {
            double[] d = new double [20] ;
            for (int i = 0; i < d.length; i++){
                d[i] = 100 * Math.random() ;
            }
            ArrayAlg.Pair p = ArrayAlg.minmax(d) ;
            System .out.println("min = " + p.getFirst()) ;
            System .out.println("max = " + p.getSecond());
    }

}
class ArrayAlg{

    /**
     * 只遍历数组一次， 并能够同时计算出最小值和最大值， 可以大大地提高效率
     * 然而， 这个方法必须返冋两个数值， 为此， 可以定义一个包含两个值的类 Pair
     * @param values
     * @return  minmax 方法可以返回一个 Pair 类型的对象。
     */
    public  static  ArrayAlg.Pair minmax(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double v : values)
        {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        /**
         * TODO  Pair 如果没有声明为 static,这里就报错：'ArrayAlg.this' cannot be referenced from a static context
         * TODO  原因如下：
         * TODO  1. 静态方法是一种不能向对象实施操作的方法。换句话说，没有隐式的参数。可以认为静态方法是没有 this 参数的方法
         * TODO    （ 在一个非静态的方法中， this参数表示这个方法的隐式参数） 但是，静态方法可以访问自身类中的静态域
         * TODO  2. 当ArrayAlg.Pair不是静态方法时候, return new ArrayAlg.Pair(min, max); 完整写法是：
         * TODO      return ArrayAlg.this.new ArrayAlg.Pair(min, max);
         * TODO     ArrayAlg.this 是对象的引用，但是minmax()静态方法 没有ArrayAlg.this 这个隐式参数，所以报错。
         * TODO  3. 所以 ArrayAlg.Pair必须为静态内部类，取消对 ArrayAlg.this 的使用。
         */
        return new ArrayAlg.Pair(min, max);
    }

    /**
     * 当然， Pair 是一个十分大众化的名字。在大型项目中， 除了定义包含一对字符串的 Pair
     * 类之外， 其他程序员也很可能使用这个名字。这样就会产生名字冲突。 解决这个问题的办法
     * 是将 Pair 定义为 ArrayAlg 的内部公有类。此后， 通过 ArrayAlg.Pair 访问它：
     * ArrayAlg.Pair p = ArrayAlg.minmax(d) ;
     */
    static class Pair
    {
        private double first;
        private double second;
        public Pair(double f , double s)
        {
            first = f;
            second = s;
        }
        public double getFirst(){ return first; }
        public double getSecond(){ return second; }
    }
}