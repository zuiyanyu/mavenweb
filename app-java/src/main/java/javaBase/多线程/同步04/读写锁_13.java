package javaBase.多线程.同步04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TODO 1. java.util.concurrent.locks 包定义了两个锁类:ReentrantLock 类 和 ReentrantReadWriteLock 类。
 * TODO 2. 如果很多线程从一个数据结构读取数据而很少线程修改其中数据的话，ReentrantReadWriteLock 类会很有用。
 *          在这种情况下， 允许对读线程共享访问是合适的。
 *          当然， 写者线程依然必须是互斥访问的。
 *
 *TODO 3. 下面是使用读 / 写锁的必要步骤：
 *TODO 1 ) 构造一个 ReentrantReadWriteLock 对象：
 *       private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 *TODO 2 ) 抽取读锁和写锁：
 *      private Lock readLock = rwl.readLock() ;
 *      private Lock writeLock = rwl.writeLock();
 *TODO 3 ) 对所有的获取方法加读锁：
 *      public double getTotalBalance()
 *      {
 *          readLock.lock()；
 *          try { . . . }
 *          finally { readLock.unlock() ; }
 *       }
 *TODO 4 ) 对所有的修改方法加写锁：
 *      public void transfer(. . .)
 *      {
 *          writeLock.lock();
 *          try { . . . }
 *          finally { writeLock.unlock(); }
 *      }
 *
 *
 * TODO  java.util.concurrent.locks.ReentrantReadWriteLock 5.0
 * • Lock readLock( )
 *      TODO  得到一个可以被多个读操作共用的读锁， 但会排斥所有写操作。
 * • Lock writeLock( )
 *      TODO  得到一个写锁， 排斥所有其他的读操作和写操作。
 *
 *
 *
 */
public class 读写锁_13 {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private Lock readLock = rwl.readLock() ;
    private Lock writeLock = rwl.writeLock();

    public double getTotalBalance()
    {

        readLock.lock();
        try {
            System.out.println();
            return 1;
        }
        finally { readLock.unlock() ; }
    }

    public void transfer( )
    {
        writeLock.lock();
        try {
            System.out.println("写");
            double totalBalance = getTotalBalance();
            System.out.println(totalBalance);
        }
        finally { writeLock.unlock(); }
    }
}
