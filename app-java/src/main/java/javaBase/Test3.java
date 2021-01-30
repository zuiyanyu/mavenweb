package javaBase;

public class Test3 {
    public static String a = "a";
    {
        System.out.println("初始化clinit");
        a = "jjj";
    }

    public Test3(){
        System.out.println("初始化TES Test3");
    }
    public static void main(String[] args) {
        new Test3();
        int i = 5;
        i=  i++; // int tmp = i ;i = i + 1 ; i = tmp ;
//        System.out.println(i);
        System.out.println(Test3.a == test.a);
        System.out.println(i);

    }
}
