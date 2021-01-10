package com.quartz.javaExecutor;

public class ClassA {
    public void getInfo(){
        Thread thread = Thread.currentThread();
        System.out.println(" ClassA.getInfo() Thread.currentThread() = "+thread);
    }
}
