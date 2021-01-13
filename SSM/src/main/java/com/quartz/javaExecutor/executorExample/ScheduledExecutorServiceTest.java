package com.quartz.javaExecutor.executorExample;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
 */
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