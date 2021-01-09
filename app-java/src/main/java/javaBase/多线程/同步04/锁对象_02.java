package javaBase.多线程.同步04;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO 有两种机制防止代码块受并发访问的干扰。
 * TODO 1. Java语言提供一个 synchronized 关键字达到同步目的，并且 Java SE 5.0 引入了 ReentrantLock 类。
 * TODO 2. synchronized 关键字自动提供一个锁以及相关的“ 条件”， 对于大多数需要显式锁的情况， 这是很便利的。
 *
 * TODO Synchronized与ReentrantLock区别
 * TODO 相似点：
 * 这两种同步方式有很多相似之处，它们都是加锁方式同步，而且都是阻塞式的同步，也就是说当如果一个线程获得了对象锁，
 * 进入了同步块，其他访问该同步块的线程都必须阻塞在同步块外面等待，而进行线程阻塞和唤醒的代价是比较高的
 * （操作系统需要在用户态与内核态之间来回切换，代价很高，不过可以通过对锁优化进行改善）。
 *
 * TODO 功能区别：
 * 这两种方式最大区别就是对于Synchronized来说，它是java语言的关键字，是原生语法层面的互斥，需要jvm实现。
 * 而ReentrantLock它是JDK 1.5之后提供的API层面的互斥锁，需要lock()和unlock()方法配合try/finally语句块来完成
 *
 * 便利性：
 * 很明显Synchronized的使用比较方便简洁，并且由编译器去保证锁的加锁和释放，而ReenTrantLock需要手工声明来加锁和释放锁，
 * 为了避免忘记手工释放锁造成死锁，所以最好在finally中声明释放锁。
 *
 * 锁的细粒度和灵活度：很明显ReentrantLock优于Synchronized
 *
 * 性能的区别：
 * 在Synchronized优化以前，synchronized的性能是比ReenTrantLock差很多的，但是自从Synchronized引入了偏向锁，轻量级锁（自旋锁）后，
 * 两者的性能就差不多了，在两种方法都可用的情况下，官方甚至建议使用synchronized，
 * 其实synchronized的优化我感觉就借鉴了ReenTrantLock中的CAS技术。都是试图在用户态就把加锁问题解决，避免进入内核态的线程阻塞。
 *
 *
 * TODO 3. 锁和条件
 * TODO 4. java.util.concurrent 框架为这些基础机制提供独立的类
 *      TODO 4.1 用 ReentrantLock （可重入锁）保护代码块的基本结构如下：
 *
 *          TODO myLock.lock(); // a ReentrantLock object
 *          try
 *          {
 *              critical section
 *          }finally{
 *              TODO myLock.unlock()；// make sure the lock is unlocked even if an exception is thrown
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
 class Demo{
     private static final ReentrantLock lock = new ReentrantLock() ;

     //TODO 可重入锁演示
     //方法1 ：使用了lock锁
     public static void method_01(){
         lock.lock();
         try{
             //TODO 1. 执行自己的代码逻辑
             System.out.println("-----执行method_01()方法--------");
             //TODO 2. 被一个锁保护的代码可以调用另一个使用相同的锁的方法
             method_02();
         }finally {
            lock.unlock();
         }
     }

     //TODO 方法2 ：使用了相同的lock锁
     public static void method_02(){
         lock.lock();
         try{
             //执行代码逻辑
             System.out.println("-----执行method_02()方法--------");
         }finally{
             lock.unlock();
         }
     }
}
 * TODO 6. 锁可重入的原理: 锁保持一个持有计数（holdcount ) 来跟踪对 lock 方法的嵌套调用。  线程在每一次调用 lock 都要调用 unlock 来释放锁。
 *         例如，TODO transfer 方法调用 getTotalBalance 方法， 这也会封锁 bankLock 对象，此时 bankLock对象的持有计数为 2。
 *               TODO 当 getTotalBalance 方法退出的时候， 持有计数变回 1 -> 当 transfer 方法退出的时候， 持有计数变为 0 -> 线程释放锁。
 *               TODO 通常， 可能想要保护需若干个操作来更新或检查共享对象的代码块。要确保这些操作完成后， 另一个线程才能使用相同对象。
 *
 * TODO 7. 警告：
 *          7.1 要留心临界区中的代码， 不要因为异常的抛出而跳出临界区。
 *          7.2 如果在临界区代码结束之前抛出了异常， finally 子句将释放锁，但会使对象可能处于一种受损状态（即对象中的数据不再准确了，别损坏了）。
 *
 *
 *TODO  java.util.concurrent.locks.Lock 5.0  是一个接口
 * • void lock( )
 *      获取这个锁；如果锁同时被另一个线程拥有则发生阻塞。
 * • void unlock( )
 *      释放这个锁。
 * TODO java,util.concurrent.locks.ReentrantLock 5.0  是Lock接口的实现类
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
    private static final ReentrantLock lock = new ReentrantLock() ;

    //TODO 可重入锁演示
    //TODO 锁是可重入的:   因为线程可以重复地获得已经持有的锁。 由于这一特性， 被一个锁保护的代码可以调用另一个使用相同的锁的方法。
    //方法1 ：使用了lock锁
    public static void method_01(){
        lock.lock();
        try{
            //TODO 1. 执行自己的代码逻辑
            System.out.println("-----执行method_01()方法--------");
            //TODO 2. 被一个锁保护的代码可以调用另一个使用相同的锁的方法
            method_02();
        }finally {
            lock.unlock();
        }
    }

    //TODO 方法2 ：使用了相同的lock锁
    public static void method_02(){
        lock.lock();
        try{
            //执行代码逻辑
            System.out.println("-----执行method_02()方法--------");
        }finally{
            lock.unlock();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        method_01();
//        try( FileInputStream fileInputStream = new FileInputStream("");){
//
//        }catch (Exception e) {
//
//        }finally {
//
//        }
    }
}
