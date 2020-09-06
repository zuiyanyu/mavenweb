package javaBase.domain;

import java.time.LocalDate;

/**
 * TODO 这个类进行类型擦除后变成
 * class Datelnterval extends Pair // after erasure
 * {
 *     public void setSecond(LocalDate second) {...}
 * }
 *
 * TODO 令人感到奇怪的是，存在另一个从 Pair 继承的 setSecond 方法,类型擦除后变为：
 * public void setSecond(Object second)
 *
 *TODO 这显然是一个不同的方法，因为它有一个不同类型的参数 Object, 而不是 LocalDate。然而，不应该不一样。
 *考虑下面的语句序列：
 * Datelnterval interval = new Datelnterval(. . .);
 * Pair<Loca1Date> pair = interval; // OK assignment to superclass
 * pair.setSecond(aDate);
 *TODO 这里， 希望对 setSecond 的调用具有多态性， 并调用最合适的那个方法。 由于 pair 引用Datelnterval 对象，所以应该调用 Datelnterval.setSecond。
 *TODO 问题在于类型擦除与多态发生了冲突。要解决这个问题， 就需要编译器在 Datelnterval 类中生成一个桥方法 （bridge method):
 *      public void setSecond(Object second) {  setSecond((Date) second);  }
 *要想了解它的工作过程， 请仔细地跟踪下列语句的执行：pair.setSecond(aDate)
 *
 *
 *
 *
 * TODO 总之，需要记住有关 Java 泛型转换的事实：
 *      •虚拟机中没有泛型，只有普通的类和方法。
 *      •所有的类型参数都用它们的限定类型替换。
 *      •桥方法被合成来保持多态。
 *      •为保持类型安全性，必要时插人强制类型转换。
 */
public class Datelnterval extends Pair<LocalDate> {
    public static void main(String[] args) {
        Datelnterval interval = new Datelnterval();
        Pair<LocalDate> pair = interval;

        LocalDate first = LocalDate.now();
        LocalDate second = LocalDate.of(2020,07,02);

        pair.setFirst(first);
        pair.setSecond(second);
        System.out.println(pair.getSecond());

        Pair<String>[] a = new Pair[10];
        a[0] = new Pair<String>();

        Object[] b = new Pair[10];
        b[0] = "abc";
        b[0] = new Pair<Integer>();





    }
    /**
     * 一个日期区间是一对 LocalDate 对象， 并且需要覆盖这个方法来确保第二个值永远不小于第一个值。
     * @param second
     */
    public void setSecond(LocalDate second){
        if (second.compareTo(getFirst()) >= 0) {
            System.out.println("===========a==============");
            super.setSecond(second);
        }else {
            System.out.println("===========b==============");
            LocalDate first = super.getFirst();
            super.setSecond(first);
            super.setFirst(second);
        }

    }
}
