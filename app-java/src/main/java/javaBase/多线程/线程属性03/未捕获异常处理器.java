package javaBase.多线程.线程属性03;

/**
 * TODO 1. 线程的 run方法不能抛出任何受查异常， 但是，非受査异常会导致线程终止。在这种情况下，线程就死亡了。
 * TODO 2. 不需要任何 catch 子句来处理可以被传播的异常。在线程死亡之前， 异常被传递到一个用于未捕获异常的处理器。
 *      该处理器必须属于一个实现 Thread.UncaughtExceptionHandler 接口的类。这个接口只有—个方法。
 *      void uncaughtException(Thread t, Throwable e)
 *
 * TODO 3.可以用 setUncaughtExceptionHandler 方法为任何线程安装一个处理器。
 * TODO 4.也可以用 Thread 类的静态方法 setDefaultUncaughtExceptionHandler 为所有线程安装一个默认的处理器。
 * TODO 5.替换处理器可以使用日志 API 发送未捕获异常的报告到日志文件。
 * TODO 6.如果不安装默认的处理器， 默认的处理器为空。
 * TODO 7.但是， 如果不为独立的线程安装处理器，此时的处理器就是该线程的 ThreadGroup 对象。
 *
 *
 * TODO java.Iang.Thread.UncaughtExceptionHandler 5.0
 * • void UncaughtException(Thread t, Throwable e)
 *       当一个线程因未捕获异常而终止， 按规定要将客户报告记录到日志中。
 *       参数：t 由于未捕获异常而终止的线程未捕获的异常对象
 *
 */
public class 未捕获异常处理器 {
    public static void main(String[] args) {
        //TODO 设置线程默认的 处理器
        Thread.setDefaultUncaughtExceptionHandler(new DefaultUncaughtExceptionHandlerTest2());

        Runnable r = () -> {
            int i = 1 / 0;
        } ;
        Thread thread1 = new Thread(r);

        //TODO 为这个线程设置自己的处理器
        thread1.setUncaughtExceptionHandler(new UncaughtExceptionHandlerTest());
        thread1.start();

        //TODO  果不为独立的线程安装处理器，此时的处理器就是该线程的 ThreadGroup 对象。



    }
}
