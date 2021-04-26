package javaBase.多线程.线程属性03;

/**
 * TODO 线程优先级
 *
 * TODO 线程优先级是高度依赖于系统的。当虚拟机依赖于宿主机平台的线程实现机制时， Java 线程的优先级被映射到宿主机平台的优先级上，优先级个数也许更多，也许更少。
 * TODO Oracle 为 Linux 提供的 Java 虚拟机中， 线程的优先级被忽略，所有线程具有相同的优先级。
 *
 * 1. 每一个线程有一个优先级
 * 2. 默认情况下， 一个线程继承它的父线程的优先级。
 * 3. 可以用 setPriority 方法提高或降低任何一个线程的优先级。
 * 4. 可以将优先级设置为在 MIN_PRIORITY (在 Thread 类中定义为 1 ) 与 MAX_PRIORITY (定义为 10 ) 之间的任何值。NORM_PRIORITY 被定义为 5。
 *    MIN_PRIORITY(1)、  MAX_PRIORITY(10) 、NORM_PRIORITY(5)
 * 5.每当线程调度器有机会选择新线程时， 它首先选择具有较高优先级的线程。
 * 6.TODO 每当调度器决定运行一个新线程时， 首先会在具有高优先级的线程中进行选择。
 *   TODO 因此，如果有几个高优先级的线程没有进入非活动状态， 低优先级的线程可能永远也不能执行。
 *
 * 7.TODO 不要将程序构建为功能的正确性依赖于优先级。初级程序员常常过度使用线程优先级。
 *   TODO 如果确实要使用优先级， 应该避免初学者常犯的一个错误: 如果有几个高优先级的线程没有进入非活动状态， 低优先级的线程可能永远也不能执行。
 *   TODO 每当调度器决定运行一个新线程时， 首先会在具有高优先级的线程中进行选择，这样会使低优先级的线程完全饿死。
 *
 * • void setPriority(int newPriority)
 * 设置线程的优先级。优先级必须在 Thread.MIN_PRIORITY 与 Thread.MAX_PRIORITY之间。一般使用 Thread.NORMJ_RIORITY 优先级。
 * • static int MIN_PRIORITY
 * 线程的最小优先级。最小优先级的值为 1。
 * • static int N0RM_PRI0RITY
 * 线程的默认优先级。默认优先级为 5。
 *
 *
 *TODO 1. java.lang.Runnable 1.0
 *• void setPriority(int newPriority)
 *      设置线程的优先级。优先级必须在 Thread.MIN_PRIORITY 与 Thread.MAX_PRIORITY之间。一般使用 Thread.NORMJ»RIORITY 优先级。
 * • static int MIN_PRIORITY
 *      线程的最小优先级。最小优先级的值为 1。
 * • static int N0RM_PRI0RITY
 *      线程的默认优先级。默认优先级为 5。
 *•static int MAX_PRIORITY
 *      线程的最高优先级。最高优先级的值为 10。
 * •static void yield( )
 *      导致当前执行线程处于让步状态。（注意，这是一个静态方法。）
 *      如果有其他的可运行线程具有至少与此线程同样高的优先级，那么这些线程接下来会被调度。
 */
public class 线程优先级 {
}
