package javaBase.多线程.Callable与Future_06;

/**
 * TODO 1. Runnable 封装一个异步运行的任务，可以把它想象成为一个没有参数和返回值的异步方法。
 * TODO 2. Callable 与 Runnable 类似， 但是有返回值.
 * TODO 3. Callable 接口是一个参数化的类型， 只有一个方法 call() :
 *       @FunctionalInterface
 *       public interface Callable<V> {
 *            V call() throws Exception;
 *       }
 *      类型参数是返回值的类型。
 *      例如， Callable<Integer> 表示一个最终返回 Integer 对象的异步计算。
 *
 * TODO 4.Future 保存异步计算的结果。
 *        可以启动一个计算，将 Future 对象交给某个线程，然后忘掉它。
 *        Future 对象的所有者在结果计算好之后就可以获得它。
 *TODO 5. Future 接口具有下面的方法：
 *        public interface Future<V>
 *        {
 *           V get() throws . .
 *           V get(long timeout, TimeUnit unit) throws . .
 *           void cancel(boolean maylnterrupt);
 *           boolean isCancelled();
 *           boolean isDone();
 *         }
 * TODO 5.1 第一个 get 方法的调用被阻塞， 直到计算完成。
 * TODO 5.2 如果在计算完成之前， 第二个方法的调用超时，拋出一个 TimeoutException 异常。
 * TODO 如果运行该计算的线程被中断， 两个方法都将拋出 IntermptedException。
 * TODO 如果计算已经完成， 那么 get 方法立即返回。
 *
 * TODO 5.3 如果计算还在进行，isDone 方法返回 false; 如果完成了， 则返回 true。
 * TODO 5.4 可以用 cancel 方法取消该计算。如果计算还没有开始，它被取消且不再开始。
 * TODO     如果计算处于运行之中，那么如果 maylnterrupt 参数为 true, 它就被中断。
 *
 * TODO 5.5 FutureTask 包装器是一种非常便利的机制， 可将 Callable转换成 Future 和 Runnable,它同时实现二者的接口。
 * 例如：
 * Callable<Integer> myComputation = . . .;
 * FutureTask<Integer> task = new FutureTask<Integer>(myConiputation);
 * Thread t = new Thread(task); // it's a Runnable
 * t.start()；
 * ...
 * Integer result = task.get()；// it's a Future
 *
 *
 *
 */
public class Callable与Future_01 {
    /**
     * TODO java.util.concurrent.Callable<V> 5.0
     * • V call()
     *      运行一个将产生结果的任务。
     * TODO java.util.concurrent.Future<V> 5.0
     * • V get()
     * • V get(long time, TimeUnit unit)
     *      获取结果， 如果没有结果可用， 则阻塞直到真正得到结果超过指定的时间为止。 如果
     *      不成功， 第二个方法会拋出 TimeoutException 异常。
     * • boolean cancel(boolean maylnterrupt)
     *      尝试取消这一任务的运行。 如果任务已经开始， 并且 maylnterrupt 参数值为 true， 它
     *      就会被中断。 如果成功执行了取消操作， 返回 true。
     *• boolean isCancelled()
     *      如果任务在完成前被取消了， 则返回 true。
     * • boolean isDone()
     *      如果任务结束，无论是正常结束、 中途取消或发生异常， 都返回 true。
     *
     * TODO java.util.concurrent.FutureTask<V> 5.0
     * • FutureTask(Cal1able< V > task)
     * • FutureTask(Runnable task, V result)
     *      构造一个既是 Future<V> 又是 Runnable 的对象。
     */
}
