package javaBase.多线程.同步04.Condition条件锁Demo;



//TODO Synchronized + Object.wait()+Object.notifyAll() 控制线程通信
public class ProductorAndConsume_01 {
    public static void main(String[] args) {


        // 一个商店
        Perck perck = new Perck();
        //两个生产者
        new Thread(new Productor(perck),"p-1").start();
        new Thread(new Productor(perck),"p-2").start();
        //三个消费者
        new Thread(new Consumer(perck),"c-1").start();
        new Thread(new Consumer(perck),"c-2").start();
        new Thread(new Consumer(perck),"c-3").start();
    }
}
class Perck{
    private int products= 0;

    //进货
    public synchronized void get(){
        while(products >= 60 ){  //为了避免虚假唤醒问题，必须将wait()使用在循环中
            System.out.println("货物满了");
            try {
                this.wait(); //被唤醒后，代码仍从此处开始继续往下执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products += 3; //两个生产者每次生产三个
        System.out.println(Thread.currentThread().getName()+" ："+ products);
        this.notifyAll();  //唤醒所有等待的消费者有货了，可以消费了
    }
    //卖货
    public synchronized void sale(){
        while(products <=0 ){
            System.out.println("缺货中");
            try {
                this.wait(); //被唤醒后，代码仍从此处开始继续往下执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products -= 4; //三个消费者每次消费两个
        System.out.println(Thread.currentThread().getName()+" ："+ products);
        this.notifyAll();  //唤醒所有等待的生产者没有货了，可以生产商品了

    }
}
/*
 * 生产者：最终总产能 120产品
 */
class Productor implements Runnable{
    private Perck perck = null ;  //店铺

    public Productor(Perck perck){
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
class Consumer implements Runnable{
    private Perck perck = null ;  //店铺
    public Consumer(Perck perck){
        this.perck = perck ;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){// 每个消费者每次消耗4个产品，有3个消费者，总共消费了10轮，最终总消耗 120 产品
            perck.sale(); //店铺卖货给消费者
        }
    }

}
