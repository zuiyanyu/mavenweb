package javaBase.多线程.执行器_07;

/**
 *TODO 1 .先来看一下表 14-2 中的 3 个方法。
 *
 * TODO 1.1  newCachedThreadPool  必要时创建新线程；空闲线程会被保留 60 秒
 * 方法构建了一个线程池,对于每个任务， 如果有空闲线程可用， 立即让它执行任务，
 * 如果没有可用的空闲线程， 则创建一个新线程。
 *
 * TODO 1.2 newFixedThreadPool  该池包含固定数量的线程；空闲线程会一直被保留
 *  newFixedThreadPool 方法构建一个具有固定大小的线程池。
 *  如果提交的任务数多于空闲的线程数， 那么把得不到服务的任务放置到队列中。当其他任务完成以后再运行它们。
 *
 * TODO 1.3 newSingleThreadExecutor  只有一个线程的 “ 池”， 该线程顺序执行每一个提交的任务（类似于Swing 事件分配线程）
 *  newSingleThreadExecutor 是一个退化了的大小为 1 的线程池： 由一个线程执行提交的任务， 一个接着一个。
 *----------------------------------------------------------------------------------------
 * TODO 2. 这 3 个方法返回实现了ExecutorService 接口的 ThreadPoolExecutor 类的对象
 * TODO 3. 可用下面的方法之一将一个 Runnable 对象或 Callable 对象提交给 ExecutorService:
 *      Future<?> submit(Runnable task)
 *      Future<T> submit(Runnable task, T result)
 *      Future<T> submit(Callable<T> task)
 * 该池会在方便的时候尽早执行提交的任务。调用 submit 时，会得到一个 Future 对象， 可用来查询该任务的状态。
 *
 * 第一个 submit 方法返回一个奇怪样子的 Future<?>。 可以使用这样一个对象来调用isDone、 cancel 或 isCancelled。但是， get 方法在完成的时候只是简单地返回 null。
 * 第二个版本的 Submit 也提交一个 Runnable， 并且 Future 的 get 方法在完成的时候返回指定的 result 对象。
 * 第三个版本的 Submit 提交一个 Callable, 并且返回的 Future 对象将在计算结果准备好的时候得到它。
 *
 *TODO 4. 当用完一个线程池的时候， 调用 shutdown。该方法启动该池的关闭序列。
 *      4.1 被关闭的执行器不再接受新的任务。
 *      4.2 当所有任务都完成以后，线程池中的线程死亡。
 *TODO 5. 另一种方法是调用 shutdownNow
 *      5.1 该池取消尚未开始的所有任务并试图中断正在运行的线程。
 *
 * TODO 6. 下面总结了在使用连接池时应该做的事：
 * 1 ) 调用 Executors 类中静态的方法 newCachedThreadPool 或 newFixedThreadPool。
 * 2 ) 调用 submit 提交 Runnable 或 Callable 对象。
 * 3 ) 如果想要取消一个任务， 或想提交 Callable 对象， 那就要保存好返回的 Future对象
 * 4 ) 当不再提交任何任务时，调用 shutdown。
 *
 *
 */
public class 线程池_02 {
    /**
     * TODO java.util.concurrent.Executors  5.0
     * • ExecutorService newCachedThreadPool()
     *      返回一个带缓存的线程池， 该池在必要的时候创建线程， 在线程空闲 60 秒之后终止线程。
     * • ExecutorService newFixedThreadPool(int threads)
     *      返回一个线程池， 该池中的线程数由参数指定。
     * • ExecutorService newSingleThreadExecutor()
     *      返回一个执行器， 它在一个单个的线程中依次执行各个任务。
     *
     * TODO java.util.concurrent.ExecutorService 5.0
     * • Future<T >  submit(Cal1able<T> task)
     * • Future< T > submit(Runnable task, T result)
     * • Future<?>   submit(Runnable task)
     *      提交指定的任务去执行。
     *      第一个 submit 方法返回一个奇怪样子的 Future<?>。 可以使用这样一个对象来调用isDone、 cancel 或 isCancelled。但是， get 方法在完成的时候只是简单地返回 null。
     *      第二个版本的 Submit 也提交一个 Runnable， 并且 Future 的 get 方法在完成的时候返回指定的 result 对象。
     *      第三个版本的 Submit 提交一个 Callable, 并且返回的 Future 对象将在计算结果准备好的时候得到它。
     *
     * • void shutdown()
     *      关闭服务， 会先完成已经提交的任务而不再接收新的任务。
     *      当所有任务都完成以后，线程池中的线程死亡。
     * • void shutdownNow
     *      该池取消尚未开始的所有任务并试图中断正在运行的线程。
     *
     * TODO java.util.concurrent.ThreadPoolExecutor 5.0
     * • int getLargestPoolSize()
     *      返回线程池在该执行器生命周期中的最大尺寸。
     */
}
