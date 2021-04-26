package javaBase.多线程.同步器_08;

import java.util.concurrent.CountDownLatch;

/**
 * 一个倒计时门栓（ CountDownLatch) 让一个线程集等待直到计数变为 0。倒计时门栓是
 * 一次性的。一旦计数为 0, 就不能再重用了。
 * 一个有用的特例是计数值为 1 的门栓。实现一个只能通过一次的门。线程在门外等候直到另一个线程将计数器值置为0
 * 举例来讲:
 * 假定一个线程集需要一些初始的数据来完成工作。工作器线程被启动并在门外等候。
 * 另一个线程准备数据。 当数据准备好的时候， 调用 countDown, 所有工作器线程就可以继续运行了。
 * 然后， 可以使用第二个门栓检査什么时候所有工作器线程完成工作。用线程数初始化门栓。每个工作器线程在结束前将门栓计数减 1。
 * 另一个获取工作结果的线程在门外等待， 一旦所有工作器线程终止该线程继续运行。
 */
/*
CountDownLatch：倒计时(倒计数(线程的数据))阀门  即闭锁。
每次执行完一个线程，就递减一，减完后才继续执行下面的操作。
Java5.0在java.util.concurrent包中提供了许多种并发容器来改进同步容器的性能。
CountDownLatch一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
闭锁可以延迟线程的进度直到其到达终止状态，闭锁可以用来确保某些活动直到其他活动都完成才继续执行。
确保某个计算在其需要的所有资源都被初始化之后才继续执行
确保某个服务在其依赖的所有其他服务都已经启动之后才启动。
等待直到某个操作所有参与者都准备就绪再继续执行。

   CountDownLatch:闭锁 即完成某些运算时，只有所有的运算全部完成，当前运算才执行。
 * (Callable和FetureTask 的也能实现闭锁效果。)
 * 多个线程同时执行任务，对其进行计时，查看执行性能
 */
public class 倒计时门栓 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Long startTime = System.currentTimeMillis();
        for(int i= 0;i<5;i++){
            new Thread(new Count(latch)).start();
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间"+ (endTime - startTime));
    }
}
class Count implements Runnable{
    private CountDownLatch latch ;
    public Count(CountDownLatch latch){
        this.latch = latch ;
    }
    @Override
    public void run() {
        try{
            for(int i=0;i<10000;i+=2){
                System.out.println(i);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            latch.countDown();
        }
    }

}
