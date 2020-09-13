package javaBase.多线程.线程属性03;

/**
 * TODO 1. 线程组是一个可以统一管理的线程集合。
 * TODO 2. 默认情况下，创建的所有线程属于相同的线程组.
 * TODO 3. 我们可以建立其他的线程组（不建议）。现在引入了更好的特性用于线程集合的操作，所以建议不要在自己的程序中使用线程组。
 * TODO 4. ThreadGroup 类实现 Thread.UncaughtExceptionHandler 接口。
 *   TODO  它的 uncaughtException 方法做如下操作：
 *          1 ) 如果该线程组有父线程组， 那么父线程组的 uncaughtException 方法被调用。
 *          2 ) 否则， 如果 Thread.getDefaultExceptionHandler 方法返回一个非空的处理器， 则调用该处理器。
 *          3 ) 否则， 如果 Throwable 是 ThreadDeath 的一个实例， 什么都不做
 *          4 ) 否则， 线程的名字以及 Throwable 的栈轨迹被输出到 System.err 上。
 *
 * TODO java.lang.ThreadGroup 1.0
 * • void UncaughtException( Thread t, Throwable e)
 *      如果有父线程组， 调用父线程组的这一方法;
 *      或者， 如果 Thread 类有默认处理器，调用该处理器，
 *      否则， 输出栈轨迹到标准错误流上（但是， 如果 e 是一个 ThreadDeath对象， 栈轨迹是被禁用的。ThreadDeath 对象由 stop方法产生， 而该方法已经过时)。
 */
public class 线程组 {
}
