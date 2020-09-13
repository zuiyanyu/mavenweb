package javaBase.多线程.同步04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * TODO 有两种机制防止代码块受并发访问的干扰。
 * TODO 1. Java语言提供一个 synchronized 关键字达到同步目的，并且 Java SE 5.0 引入了 ReentrantLock 类。
 * TODO 2. synchronized 关键字自动提供一个锁以及相关的“ 条件”， 对于大多数需要显式锁的情况， 这是很便利的。
 * TODO 3. 锁和条件
 * TODO 4. java.util.concurrent 框架为这些基础机制提供独立的类
 *      TODO 4.1 用 ReentrantLock （可重入锁）保护代码块的基本结构如下：
 *
 *          TODO myLock.lock(); // a ReentrantLock object
 *          try
 *          {
 *              critical section
 *          }finally{
 *              TODO myLock.unlockO；// make sure the lock is unlocked even if an exception is thrown
 *          }
 *
 *      TODO 4.2 这一结构确保任何时刻只有一个线程进人临界区。一旦一个线程封锁了锁对象， 其他任何线程都无法通过 lock() 语句。
 *      TODO 4.3 当其他线程调用 lock 时，它们被阻塞， 直到第一个线程释放锁对象。
 *      TODO 4.4 把解锁操作括在 finally 子句之内是至关重要的。如果在临界区的代码抛出异常，锁必须被释放。 否则， 其他线程将永远阻塞。
 *
 *      TODO 4.5 如果使用锁， 就不能使用带资源的 try语句。首先， 解锁方法名不是close。
 *
 * TODO 4.对象实例锁
 * TODO 注意每一个 Bank 对象有自己的 ReentrantLock 对象。如果两个线程试图访问同一个Bank 对象， 那么锁以串行方式提供服务。
 * TODO 但是， 如果两个线程访问不同的 Bank 对象， 每一个线程得到不同的锁对象， 两个线程都不会发生阻塞。
 * TODO (线程在操纵不同的Bank 实例的时候， 线程之间不会相互影响)
 *
 * TODO 5. 锁是可重入的:   因为线程可以重复地获得已经持有的锁。 由于这一特性， 被一个锁保护的代码可以调用另一个使用相同的锁的方法。
 * TODO 6. 锁可重入的原理: 锁保持一个持有计数（holdcount ) 来跟踪对 lock 方法的嵌套调用。  线程在每一次调用 lock 都要调用 unlock 来释放锁。
 *         例如，TODO transfer 方法调用 getTotalBalance 方法， 这也会封锁 bankLock 对象，此时 bankLock对象的持有计数为 2。
 *               TODO 当 getTotalBalance 方法退出的时候， 持有计数变回 1 -> 当 transfer 方法退出的时候， 持有计数变为 0 -> 线程释放锁。
 *               TODO 通常， 可能想要保护需若干个操作来更新或检查共享对象的代码块。要确保这些操作完成后， 另一个线程才能使用相同对象。
 *
 * TODO 7. 警告：
 *          7.1 要留心临界区中的代码， 不要因为异常的抛出而跳出临界区。
 *          7.2 如果在临界区代码结束之前抛出了异常， finally 子句将释放锁，但会使对象可能处于一种受损状态。
 *
 *
 *TODO  java.util.concurrent.locks.Lock 5.0
 * • void lock( )
 *      获取这个锁；如果锁同时被另一个线程拥有则发生阻塞。
 * • void unlock( )
 *      释放这个锁。
 * TODO java,util.concurrent.locks.ReentrantLock 5.0
 * • ReentrantLock( )
 *      构建一个可以被用来保护临界区的可重入锁。
 * • ReentrantLock(boo1ean fair )
 *      构建一个带有公平策略的锁。一个公平锁偏爱等待时间最长的线程。
 *      但是，这一公平的保证将大大降低性能。所以， 默认情况下， 锁没有被强制为公平的。
 *
 *TODO 警告：  听起来公平锁更合理一些， 但是使用公平锁比使用常规锁要慢很多。 只有当你确
 *              实了解自己要做什么并且对于你要解决的问题有一个特定的理由必须使用公平锁的时候，
 *              才可以使用公平锁。 即使使用公平锁， 也无法确保线程调度器是公平的。 如果线程调度
 *              器选择忽略一个线程， 而该线程为了这个锁已经等待了很长时间， 那么就没有机会公平
 *              地处理这个锁了。
 */
public class 锁对象_02 {
    public static void main(String[] args) throws FileNotFoundException {

        try( FileInputStream fileInputStream = new FileInputStream("");){

        }catch (Exception e) {

        }finally {

        }
    }
}
