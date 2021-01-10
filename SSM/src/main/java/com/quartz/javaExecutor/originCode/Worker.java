package com.quartz.javaExecutor.originCode;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Worker extends AbstractQueuedSynchronizer
        implements Runnable
{
    /**
     * This class will never be serialized, but we provide a
     * serialVersionUID to suppress a javac warning.
     */
    private static final long serialVersionUID = 6138294804551838833L;

    /** Thread this worker is running in.  Null if factory fails. */
    //这个工作器正在运行的线程。如果工厂失败，则为Null。
    // 每个工作器都持有一个线程来运行任务。  thread就代表正在运行的worker
    final Thread thread;

    /** Initial task to run.  Possibly null. */
    //给此工作器初始化一个任务去运行。 可能为null(没有可运行的任务)
    Runnable firstTask;

    /** Per-thread task counter */
    //线程任务计数器   即每个任务线程完成的任务量
    volatile long completedTasks;

    /**
     * Creates with given first task and thread from ThreadFactory.
     * 使用给定的任务task，和 ThreadFactory创建的任务线程 来创建一个工作器。
     * @param firstTask 第一个要运行的任务(没任务的时候给null值)
     */
    Worker(Runnable firstTask) {
        setState(-1); // inhibit interrupts until runWorker 禁止中断直到 runWorker()运行完毕
        this.firstTask = firstTask;

        /** 从线程工程中创建一个thread ,thread中封装的是 worker，而不是要执行的任务。
         * thread是一个非守护线程，运行完毕就会销毁，worker也会随着销毁。
         * TODO 有任务的时候，创建worker运行任务，没任务的时候，就销毁worker
         *  getThreadFactory().newThread(this) :
         *  public Thread newThread(Runnable r) {
         *             Thread t = new Thread(group, r,
         *                                   namePrefix + threadNumber.getAndIncrement(),
         *                                   0);
         *             if (t.isDaemon())
         *                 t.setDaemon(false);
         *             if (t.getPriority() != Thread.NORM_PRIORITY)
         *                 t.setPriority(Thread.NORM_PRIORITY);
         *             return t;
         *         }
         */
        this.thread = null ;// this.thread = getThreadFactory().newThread(this);
    }

    /** Delegates main run loop to outer runWorker
     * 将主运行循环委托给外部runWorker
     */
    public void run() {
        //TODO 本线程运行的逻辑在外部类中实现
        //runWorker(this);//ThreadPoolExecutor.runWorker(this);
    }

    // Lock methods
    //
    // The value 0 represents the unlocked state.
    // The value 1 represents the locked state.

    protected boolean isHeldExclusively() {
        return getState() != 0;
    }

    protected boolean tryAcquire(int unused) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock()        { acquire(1); }
    public boolean tryLock()  { return tryAcquire(1); }
    public void unlock()      { release(1); }
    public boolean isLocked() { return isHeldExclusively(); }

    void interruptIfStarted() {
        Thread t;
        if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
            try {
                t.interrupt();
            } catch (SecurityException ignore) {
            }
        }
    }
}
