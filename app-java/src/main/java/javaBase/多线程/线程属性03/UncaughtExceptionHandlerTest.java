package javaBase.多线程.线程属性03;

public class UncaughtExceptionHandlerTest implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("UncaughtExceptionHandlerTest ==============="+t.getName());
    }
}
class DefaultUncaughtExceptionHandlerTest2 implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("DefaultUncaughtExceptionHandlerTest2 ==============="+t.getName());
    }
}