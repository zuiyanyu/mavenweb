package javaBase.多线程.同步04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 1. 线程在调用 lock 方法来获得另一个线程所持有的锁的时候，很可能发生阻塞。应该更加谨慎地申请锁。
 * TODO 2. tryLock 方法试图申请一个锁， 在成功获得锁后返回 true, 否则， 立即返回false, 而且线程可以立即离开去做其他事情。
 *         if (myLock.tryLock()) {
 *             // now the thread owns the lock
 *             try { . . . }
 *             finally { myLock.unlock(); }
 *         } else{
 *             // do something else
 *         }
 *TODO 3. 可以调用 tryLock 时，使用超时参数，像这样：
 *       if (myLock.tryLock(100, TimeUnit.MILLISECONDS)){...}
 *       TimeUnit 是一 枚举类型，可以取的值包括 SECONDS、MILLISECONDS, MICROSECONDS和 NANOSECONDS。
 *
 *TODO 4. lock 方法不能被中断。如果一个线程在等待获得一个锁时被中断，中断线程在获得锁之前一直处于阻塞状态。
 *         TODO 如果出现死锁， 那么，lock 方法就无法终止。
 *         TODO 然而， 如果调用带有用超时参数的 tryLock, 那么如果线程在等待期间被中断，将抛出InterruptedException 异常。
 *              这是一个非常有用的特性，因为允许程序打破死锁。
 *
 *TODO 5. 也可以调用 locklnterruptibly 方法。它就相当于一个超时设为无限的 tryLock 方法。
 *        myLock.lockInterruptibly();
 *
 *      在等待一个条件时， 也可以提供一个超时：
 *      myCondition.await(100, TineUnit.MILLISECONDS))
 * TODO  如果一个线程被另一个线程通过调用 signalAll 或 signal 激活， 或者超时时限已达到，或者线程被中断， 那么 await 方法将返回。
 * TODO  如果等待的线程被中断， await 方法将抛出一个 InterruptedException 异常。
 * TODO  在你希望出现这种情况时线程继续等待（可能不太合理，) 可以使用 awaitUninterruptibly 方法代替 await。
 *
 *
 */
public class 锁测试与超时_12 {
    private static final Lock myLock = new ReentrantLock() ;
    public static void main(String[] args) throws InterruptedException {
        if (myLock.tryLock(100, TimeUnit.MILLISECONDS)){
            try{

                //do something
            } finally {
                myLock.unlock();
            }
        }

    }
}
