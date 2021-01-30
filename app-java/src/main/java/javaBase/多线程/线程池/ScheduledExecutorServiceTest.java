package javaBase.多线程.线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        //1. 创建线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        //2. 为线程池中的线程分配任务
        ThreadPoolDemo td = new ThreadPoolDemo();
        for(int i=0;i<10;i++){
            pool.submit(td);
        }
        //3. 关闭线程池
        pool.shutdown();
    }
}
class ThreadPoolDemo implements Runnable{

    private int i = 0;

    @Override
    public void run() {
        while(i <= 100){
            System.out.println(Thread.currentThread().getName() + " : " + i++);
        }
    }

}