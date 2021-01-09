package javaBase.多线程;

public class ThreadMethod {
    /**TODO 1. java.lang.Runnable 1.0
     * • void run( )
     *      必须覆盖这个方法， 并在这个方法中提供所要执行的任务指令。
     * TODO 2.  java.Iang.Thread 1.0
     * • static void sleep(long minis)
     *       休眠给定的毫秒数。参数：millis 休眠的毫秒数
     * • ThreadCRunnable target )
     *      构造一个新线程， 用于调用给定目标的 nm() 方法。
     * • void start( )
     *      启动这个线程， 将引发调用 mn() 方法。这个方法将立即返回， 并且新线程将并发运行。
     * • void run( )
     *      调用关联 Runnable 的 run 方法。
     *• void interrupt()
     *      向线程发送中断请求。线程的中断状态将被设置为 true。如果目前该线程被一个 sleep
     *      调用阻塞，那么，InterruptedException 异常被抛出。
     * • static boolean interrupted()
     *      测试当前线程（即正在执行这一命令的线程）是否被中断。注意，这是一个静态方法。
     *      这一调用会产生副作用—它将当前线程的中断状态重置为 false。
     * • boolean islnterrupted()
     *      测试线程是否被终止。不像静态的中断方法，这一调用不改变线程的中断状态。
     * • static Thread currentThread()
     *      返回代表当前执行线程的 Thread 对象。
     *
     * •void join()
     *     等待终止指定的线程。
     * •void join(long mi11is)
     *     等待指定的线程死亡或者经过指定的毫秒数。
     * •Thread.State getState() 5.0
     *     得到这一线程的状态；NEW、RUNNABLE BLOCKED、 WAITING 、TIMED_WAITING 或 TERMINATED 之一。
     * •void stop()
     *     停止该线程。这一方法已过时。 禁用此方法
     * •void suspend()
     *     （挂起）暂停这一线程的执行。这一方法已过时。禁用此方法
     * • void resume()
     *     （恢复）恢复线程。这一方法仅仅在调用 suspend() 之后调用。这一方法已过时。
     *
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
     *
     *•void setDaemon( boolean isDaemon )
     *     标识该线程为守护线程或用户线程。这一方法必须在线程启动之前调用。
     *• static void setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler handler) 5.0
     *• static Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler()5.0
     *      设置或获取未捕获异常的默认处理器。
     *• void setUncaughtExceptionHandler(Thread •UncaughtExceptionHandler handler) 5.0
     *• Thread.UncaughtExceptionHandler getUncaughtExceptionHandler( ) 5.0
     *      设置或获取未捕获异常的处理器。如果没有安装处理器， 则将线程组对象作为处理器。
     *
     *
     *
     *TODO  java.util.concurrent.locks.Lock 5.0
     * • void lock( )
     *      获取这个锁；如果锁同时被另一个线程拥有则发生阻塞。
     * • void unlock( )
     *      释放这个锁。
     * • Condition newCondition( )
     *      返回一个与该锁相关的条件对象。
     *• boolean tryLock()
     *      尝试获得锁而没有发生阻塞；如果成功返回true。这个方法会抢夺可用的锁， 即使该锁
     *      有公平加锁策略， 即便其他线程已经等待很久也是如此。
     * • boolean tryLock(long time, TimeUnit unit)
     *      尝试获得锁，阻塞时间不会超过给定的值；如果成功返回 true 。
     * • void lockInterruptibly()
     *      获得锁， 但是会不确定地发生阻塞。 如果线程被中断， 抛出一个 InterruptedException异常。
     *
     *
     * TODO java,util.concurrent.locks.ReentrantLock 5.0
     * • ReentrantLock( )
     *      构建一个可以被用来保护临界区的可重入锁。
     * • ReentrantLock(boo1ean fair )
     *      构建一个带有公平策略的锁。一个公平锁偏爱等待时间最长的线程。
     *      但是，这一公平的保证将大大降低性能。所以， 默认情况下， 锁没有被强制为公平的。
     *
     *TODO  java.util.concurrent.locks.Condition 5.0
     * • void await( )
     *      将该线程放到条件的等待集中。
     * • void signalA11( )
     *       解除该条件的等待集中的所有线程的阻塞状态。
     * • void signal ( )
     *      从该条件的等待集中随机地选择一个线程， 解除其阻塞状态。
     *• boolean await( 1ong time , TimeUnit unit )
     *      进入该条件的等待集， 直到线程从等待集中移出或等待了指定的时间之后才解除阻塞
     *      如果因为等待时间到了而返回就返回 false, 否 则 返 回 true。
     * • void awaitUninterruptibly( )
     *      进入该条件的等待集， 直到线程从等待集移出才解除阻塞。 如果线程被中断， 该方法
     *      不会抛出 InterruptedException 异常。
     *
     * TODO  java.lang.Object 1.0
     * •void notifyAll( )
     *      解除那些在该对象上调用 wait 方法的线程的阻塞状态。该方法只能在同步方法或同步块内部调用。
     *      如果当前线程不是对象锁的持有者，该方法拋出一个 IllegalMonitorStateException异常
     *•void notify( )
     *      随机选择一个在该对象上调用 wait 方法的线程， 解除其阻塞状态。该方法只能在一个同步方法或同步块中调用。
     *      如果当前线程不是对象锁的持有者， 该方法抛出一个 IllegalMonitorStateException 异常
     *•void wait( )
     *      导致线程进人等待状态直到它被通知。该方法只能在一个同步方法中调用。
     *      如果当前线程不是对象锁的持有者，该方法拋出一个 IllegalMonitorStateException 异常。
     *
     *•void wait(long mi11is)
     *•void wait(long mi 11Is, int nanos )
     *      导致线程进入等待状态直到它被通知或者经过指定的时间。这些方法只能在一个同步方法中调用。
     *      如果当前线程不是对象锁的持有者该方法拋出一个 IllegalMonitorStateException异常。
     *      参数：  millis 毫秒数
     *              nanos 纳秒数，<1 000 000
     *
     *TODO java.lang.ThreadLocal<T>  1.2
     * • T get( )
     *      得到这个线程的当前值。如果是首次调用 get, 会调用 initialize 来得到这个值。
     * •protected initialize( )
     *      应覆盖这个方法来提供一个初始值。默认情况下，这个方法返回 null。
     * • void set( T t)
     *      为这个线程设置一个新值。
     * • void remove( )
     *      删除对应这个线程的值。
     * •static <S> ThreadLocal <S> withlnitial ( Suppl ier < ? extends S> supplier) 8
     *      创建一个线程局部变量， 其初始值通过调用给定的 supplier 生成。
     *
     *TODO java.util.concurrent.ThreadLocalRandom  7
     *•static ThreadLocalRandom current( )
     *      返回特定于当前线程的 Random 类实例。
     *
     *
     * TODO  java.util.concurrent.locks.ReentrantReadWriteLock 5.0
     * • Lock readLock( )
     *      得到一个可以被多个读操作共用的读锁， 但会排斥所有写操作。
     * • Lock writeLock( )
     *      得到一个写锁， 排斥所有其他的读操作和写操作。
     *
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
     *
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
