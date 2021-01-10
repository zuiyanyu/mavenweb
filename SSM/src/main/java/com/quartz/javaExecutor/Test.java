package com.quartz.javaExecutor;

public class Test {
    public static void main(String[] args) {
        System.out.println("main() :"+Thread.currentThread());
        //仍然是主线程在运行 ClassA实例的 getInfo()方法。
        new ClassA().getInfo();
    }
}
