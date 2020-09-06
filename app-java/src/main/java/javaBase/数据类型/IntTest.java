package javaBase.数据类型;

import org.junit.Test;

public class IntTest {
    @Test
    public void IntTest1(){
        double i = 20.2 / 0 ;   // Infinity
        System.out.println(i);

//        int a = 2/0 ;  // java.lang.ArithmeticException: / by zero
//        System.out.println(a);

        int ii = Integer.parseInt("11", 10);
        System.out.println(ii);
    }
}
