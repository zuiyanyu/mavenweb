package javaBase.多线程.同步04.Condition条件锁Demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 /**
 Condition控制线程通信
 Condition接口描述了可能会与锁有关联的条件变量。这些变量在用法上与使用Object.wait()访问
 的隐式监视器类似，但提供了更强大的功能。需要特别指出的是，单个Lock可能与过个Condition
 对象关联。为了避免兼容性问题，Condition方法的名称与对应的Object版本中的不同。
 在Condition对象中，与wait、notify和notifyAll方法对应的分别是await、signal 和signalAll。
 Condition实例实质上被绑定到一个锁上，要为特定Lock实例获得Condition实例，请使用newCondition()方法。
 当多个线程进行通信的时候：
 // Condition 控制当前线程的通信。
 1. 每个通信线程间都有一个自己的Condition。
 2. 此Condition由同一把Lock锁生成的，即通信线程用的Condition被绑定到同一把锁上。
 3.每个线程用自己的Condition.await()进行休眠，和 在其他线程中 用自己的Condition.signal()只唤醒自己。

 最佳实例参见 “线程按序交替”。


 再谈消费者与生产者

 *
 生产者消费者问题 要确保两个规则：
 * 	一、 无论生产者 、消费者各有几个，必须保证 ：最终生产者生产的总产品量 == 最终消费者消费的总产品量。
 *         < 个人认为： 这是一个产能平衡问题。如果不平衡，就会出问题，比如生产者/消费者 休眠后没人唤醒。>
 *  二、使用线程实现生产者和消费者，一不小心就会出现虚假唤醒的问题。
 为了避免虚假唤醒问题，必须将wait()使用在循环中，而notifyAll/notify 用在循环外。
 *         < 个人认为：这是一个  按剩余资源量进行排队分配的机制。>
 *     (这是为了保证
 *    1.在产品允许生产的情况下，生产者逐个放行让其生产；当放行的生产者生产产品后使总产品达到上限，就不再放行生产者生产了。
 *     (产品总数量要实时的被循环监控者)，不再放行的就让其休眠，等需要生产产品的时候再被消费者唤醒，然后竞争谁能出去生产。
 *
 *    2.在产品允许消费的情况下，消费者逐个放行让其消费；当放行的消费者消费产品后使总产品达到下限，就不再放行消费者消费了。
 *    (产品总数量要实时的被循环监控者)，不再放行的就让其休眠，等有产品可消费的时候再被生产者唤醒，然后竞争谁能出去消费。
 *    )
 *
 *  三、Lock + Condition 控制线程通信
 *  1.Condition接口描述了可能会与锁有关联的条件变量。
 *  2.

 *
 *
 */
//TODO 实例2 生产者消费者:使用 Lock + Condition 控制线程通信
public class ProductorAndConsumer_Condition {
    public static void main(String[] args) {
        // 一个商店
        Perck_condition perck = new Perck_condition();
        //两个生产者
        new Thread(new Productor_condition(perck),"p-1").start();
        new Thread(new Productor_condition(perck),"p-2").start();
        //三个消费者
        new Thread(new Consumer_condition(perck),"c-1").start();
        new Thread(new Consumer_condition(perck),"c-2").start();
        new Thread(new Consumer_condition(perck),"c-3").start();
    }
}
class Perck_condition{
    private int products= 0;  //产品数量
    private Lock lock = new ReentrantLock() ; ;
    private Condition condition= lock.newCondition();

    //进货
    public  void get(){
        lock.lock(); //加锁
        try{
            while(products >= 60 ){  //为了避免虚假唤醒问题，必须将wait()使用在循环中
                System.out.println("货物满了");
                try {
                    condition.await();//this.wait(); //被唤醒后，代码仍从此处开始继续往下执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            products += 3; //两个生产者每次生产三个
            System.out.println(Thread.currentThread().getName()+" ："+ products);
            condition.signalAll();//this.notifyAll();  //唤醒所有等待的消费者有货了，可以消费了
        }finally{
            lock.unlock(); //释放锁
        }
    }

    //卖货
    public  void sale(){
        lock.lock();//加锁
        try{
            while(products <=0 ){
                System.out.println("缺货中");
                try {
                    condition.await();//this.wait(); //被唤醒后，代码仍从此处开始继续往下执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            products -= 4; //三个消费者每次消费两个
            System.out.println(Thread.currentThread().getName()+" ："+ products);
            condition.signalAll();//this.notifyAll();  //唤醒所有等待的生产者没有货了，可以生产商品了
        }finally{
            lock.unlock(); //释放锁
        }
    }
}
/*
 * 生产者：最终总产能 120产品
 */
class Productor_condition implements Runnable{
    private Perck_condition perck = null ;  //店铺

    public Productor_condition(Perck_condition perck){
        this.perck = perck ;
    }
    @Override
    public void run() {
        for(int i=0;i<20;i++){ //每个生产者每次生产3个产品，有2个生产者，生产了20轮，最终总产能 120产品
            this.perck.get(); //店铺从生产者手中进货
        }
    }
}
/*
 * 消费者 ：最终总消耗 120 产品
 */
class Consumer_condition implements Runnable{
    private Perck_condition perck = null ;  //店铺
    public Consumer_condition(Perck_condition perck){
        this.perck = perck ;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){// 每个消费者每次消耗4个产品，有3个消费者，总共消费了10轮，最终总消耗 120 产品
            perck.sale(); //店铺卖货给消费者
        }
    }
}