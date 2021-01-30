package javaBase.多线程.同步04.Lock锁Demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 一、 用于解决多线程安全问题的方式
 *
 * synchronized :隐士锁 (同步交给jvm进行管理)
 * 1. 同步代码块
 * 2. 同步方法
 *
 * jdk1.5以后
 * 3. 同步锁 :显示锁  比隐士锁更加的灵活，但是会存在一定的风险(锁没关的风险)
 * 注意：是一个显示锁，需要通过lock() 方法上锁，必须在try-finally中通过unlock()释放锁
 *
 * 实例：解决多窗口卖票
 */
public class LockDemo01 {
    public static void main(String[] args) {
        SellTicks ticks = new SellTicks();

        new Thread(ticks,"window1").start();
        new Thread(ticks,"window2").start();
        new Thread(ticks,"window3").start();
        new Thread(ticks,"window4").start();

    }
}
class SellTicks implements Runnable{
    private int ticks = 100 ;
    private Lock lock = new ReentrantLock();
    @Override
    public void run(){
        while(true){
            lock.lock();
            try{
                if(ticks>0){
                    --ticks;
                    System.out.println(Thread.currentThread().getName()+"完成售票； "+
                            "剩余票数为："+ ticks);
                }else{
                    break ;
                }
            }finally{
                lock.unlock();
            }
        }
    }
}
