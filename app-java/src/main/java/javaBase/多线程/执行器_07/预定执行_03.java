package javaBase.多线程.执行器_07;

/**
 *
 * TODO 1.ScheduledExecutorService 接口具有为预定执行（Scheduled Execution ) 或 重复执行任务而设计的方法。
 * TODO 2. 它是一种允许使用线程池机制的java.util.Timer 的泛化。
 * TODO 3. Executors 类的 newScheduledThreadPool 和 newSingleThreadScheduledExecutor 方法将返回实现了ScheduledExecutorService 接口的对象。
 * TODO 3.1  可以预定 Runnable 或 Callable 在初始的延迟之后只运行一次。
 * TODO 3.2  也可以预定一个 Runnable对象周期性地运行。
 *
 *
 *
 */
public class 预定执行_03 {
    /**
     * TODO java.util.concurrent.Executors 5.0
     * • ScheduledExecutorService newScheduledThreadPool(int threads)
     *      返回一个线程池， 它使用给定的线程数来调度任务。
     * • ScheduledExecutorService newSingleThreadScheduledExecutor( )
     *      返回一个执行器， 它在一个单独线程中调度任务。
     *
     * TODO java.util.concurrent.ScheduledExecutorService 5.0
     * • ScheduledFuture<V> schedule(Cal 1able<V> task , long time, Timellnit unit)
     * • ScheduledFuture<?> schedule(Runnable task , long time , TimeUnit unit )
     *      预定在指定的时间之后执行任务。
     * • ScheduledFuture< ? > scheduleAtFixedRate ( Runnable task , long initialDelay , long period, TimeUnit unit )
     *      预定在初始的延迟结束后,周期性地运行给定的任务， 周期长度是 period。
     *      （如果上一个线程还在运行，到了调度周期，也会新开启一个调度任务并行运行）
     * • ScheduledFuture< ?> scheduleWithFixedDelay( Runnable task , long initialDelay , long delay, TimeUnit unit )
     *      预定在初始的延迟结束后,周期性地运行给定的任务， 在一次调用完成和下一次调用开始之间有长度为 delay 的延迟
     */
}
