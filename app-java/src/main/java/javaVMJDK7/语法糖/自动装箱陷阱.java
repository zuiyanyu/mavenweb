package javaVMJDK7.语法糖;

/**
 * 建议避免使用自动装箱和拆箱。
 */
public class 自动装箱陷阱 {
    public static void main(String[] args) {
        Integer a = 1 ;
        Integer b = 2 ;
        Integer c = 3 ;
        Integer d = 3 ;
        Integer e = 128 ;
        Integer f = 128 ;
        Integer h = 127 ;
        Long g =3L ;
        //TODO 小于等于127 的整数值会公用静态常量池中的数。
        System.out.println(c==d); //true
        // TODO 1. 大于127的数会重新在堆中分配内存；2. 包装类的"=="运算在不遇到算术运算的情况下不会自动拆箱。
        System.out.println(e==f); //false
        System.out.println(c==(a+b));//true
        //TODO 包装类的 equals()方法不会处理数据类型的关系。
        System.out.println(c.equals(a+b));//true
        System.out.println(g==(a+b));//true
        System.out.println(g.equals(a+b));//false
        System.out.println(f.equals(a+h));//true

    }
}
