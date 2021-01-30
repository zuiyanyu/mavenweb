package javaBase.多线程.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * 一、线程池：提供了一个线程对列，队列中保存着所有等待状态的线程。避免了创建与销毁的额外开销，提高了响应的速度。
 * 二、线程池的体系结构
 *    java.util.current.executor 接口：负责线程的使用与调度的根接口
 *      |--**ExecutorService 子接口：线程池的主要接口。
 *         |--ThreadPoolExecutor 线程池的实现类
 *         |--ScheduledExecutorService 子接口：负责线程的调度
 *            |--ScheduledThreadPoolExecutor ：继承了ThreadPoolExecutor，（可以当线程池）
 *                           并实现了ScheduledExecutorService(可以对线程进行调度)
 *三、工具类 ：Executors (static静态方法)
 *ExecutorService newFixedThreadPool() ：创建固定大小的线程池
 *ExecutorService newCachedThreadPool(): 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 *ExecutorService newSingleThreadExecutor():创建单个线程池。线程池中只有一个线程。
 *
 *ScheduledExecutorService newScheduledThreadPool() :创建固定大小的线程池，可以延迟或定时的执行任务。
 *
 *
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //获取线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        //存放所有结果，统一处理
        List<Future<Integer>> list = new ArrayList<>();
        //分配任务
        for (int i = 0; i < 10; i++) {
            Future<Integer> result = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int sum = 0;
                    for (int i = 0; i <= 100; i++) {
                        sum += i;
                    }
                    System.out.println(Thread.currentThread().getName() + "========");
                    return sum;
                }
            }, 3, TimeUnit.SECONDS); //线程任务三秒后再执行。

            System.out.println(result.get()); //这里起到阻塞主线程的作用；如果没加result.get()，三秒后同时执行完十个线程；
            //list.add(result);//存结果
        }
        //for(Future<Integer> t: list){
//			System.out.println(t.get());
        //}
        //关闭线程池
        pool.shutdown();
    }
}
