package javaBase.多线程.执行器_07;

/**
 * TODO 1. 你已经了解了如何将一个执行器服务作为线程池使用， 以提高执行任务的效率。
 * TODO    有时， 使用执行器有更有实际意义的原因， 控制一组相关任务。
 *          例如， 可以在执行器中使用 shutdownNow 方法取消所有的任务。
 * TODO 2. invokeAny 方法提交所有对象到一个 Callable 对象的集合中， 并返回某个已经完成了的任务的结果。
 * 无法知道返回的究竟是哪个任务的结果， 也许是最先完成的那个任务的结果。
 *
 * TODO 应用场景： 对于搜索问题， 如果你愿意接受任何一种解决方案的话，你就可以使用这个方法。
 *  例如:
 *  假定你需要对一个大整数进行因数分解计算来解码 RSA 密码。可以提交很多任务， 每一个任
 * 务使用不同范围内的数来进行分解。只要其中一个任务得到了答案， 计算就可以停止了。
 *
 * TODO 3. invokeAll 方法提交所有对象到一个 Callable 对象的集合中，并返回一个 Future 对象的列表，代表所有任务的解决方案。
 *  <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
 *  *          throws InterruptedException;
 *
 *          当计算结果可获得时， 可以像下面这样对结果进行处理：
 *          List<Callab1e<T>> tasks = . . .;
 *          List<Future<T>>results = executor.invokeAll(tasks):
 *          for(Future<T> result : results){
 *              processFurther(result.get());
 *          }
 *TODO 3.1 这个方法的缺点是如果第一个任务恰巧花去了很多时间， 则可能不得不进行等待。
 *TODO 3.2 将结果按可获得的顺序保存起来更有实际意义。可以用 ExecutorCompletionService 来进行排列。
 *
 * TODO 4.  ExecutorCompletionService
 * TODO 4.1 用常规的方法获得一个执行器。然后， 构建一个 ExecutorCompletionService， 提交任务给完成服务（completion service。)
 * TODO 4.2 该服务管理 Future 对象的阻塞队列，其中包含已经提交的任务的执行结果 （当这些结果成为可用时。)
 * 这样一来，相比前面的计算， 一个更有效的组织形式如下：
 * ExecutorCompletionService<T> service = new ExecutorCompletionService<>(executor);
 * for (Callable<T> task : tasks){
 *     service.submit(task);
 * }
 * for (int i = 0; i < tasks.size()；i++){
 *     processFurther(service.take().get())；
 * }
 *
 *
 *
 */
public class 控制任务组_04 {
/**
 * TODO  java.util.concurrent.ExecutorService
 * • T invokeAny(Co11ection<Cal1able<T>> tasks )
 * • T invokeAny(Col1ection<Cal1able<T>> tasks , long timeout , TimeUnit unit )
 *      执行给定的任务， 返回其中一个任务的结果。
 *      第二个方法若发生超时， 抛出一个Timeout Exception 异常。
 * • List<Future<T>> invokeAll ( Col1ection<Cal1able<T>> tasks )
 * • List<Future<T>> invokeAll ( Col1ection<Cal1able<T>> tasks , long timeout , TimeUnit unit )
 *      执行给定的任务， 返回所有任务的结果。
 *      第二个方法若发生超时， 拋出一个 TimecmtException 异常。
 * TODO  java.util.concurrent.ExecutorCompletionService<V>5.0
 * • ExecutorCompletionService( Executor e )
 *      构建一个执行器完成服务来收集给定执行器的结果。
 * • Future<V> submit( Cal1able<V> task )
 * • Future<V> submit( Runnable task , V result )
 *      提交一个任务给底层的执行器。
 * • Future<V> take()
 *      移除下一个已完成的结果， 如果没有任何已完成的结果可用则阻塞。
 * • Future<V> pol1 ()
 * • Future<V> pol1( 1ong time , TimeUnit unit )
 *      移除下一个已完成的结果， 如果没有任何已完成结果可用则返回 null。
 *      第二个方法将等待给定的时间。
 */
}
