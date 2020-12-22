package javaBase.多线程.threadlacal;

public class ThreadLocalTest {
    //static ThreadLocal<String> localVar = new ThreadLocal<>();
    static ThreadLocal<String> localVar = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "初始值";
        }
    };

    //在多线程中，公用的业务处理逻辑
    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
    }

    public static void main(String[] args) {
        String v = "ZjWSyg7/89nSXlIcAFvZvA==";
        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                localVar.set("localVar1");
                //进行业务逻辑的处理：调用打印方法
                print("thread1");
                //清除本地内存中的本地变量
                localVar.remove();
                //打印本地变量
                System.out.println("after remove localVar1: " + localVar.get());
            }
        });

        Thread t2  = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                localVar.set("localVar2");
                //调用打印方法
                print("thread2");
                //清除本地内存中的本地变量
                localVar.remove();
                //打印本地变量
                System.out.println("after remove localVar2: " + localVar.get());
            }
        });

        t1.start();
        t2.start();
    }
}
