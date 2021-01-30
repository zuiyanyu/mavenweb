package javaBase.多线程.线程池;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
 * 接口 ExecutorService 的方法
 void	shutdown()  启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
 List<Runnable>	shutdownNow()试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
  <T> Future<T>  submit(Callable<T> task)  提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。
 Future<?>	submit(Runnable task) 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。

public interface ScheduledExecutorService  extends ExecutorService
<V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit)
          创建并执行在给定延迟后启用的 ScheduledFuture。
 ScheduledFuture<?>	schedule(Runnable command, long delay, TimeUnit unit)
          创建并执行在给定延迟后启用的一次性操作。
 */
public class ThreadPoolTest {
    // Callable
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> result = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws EOFException {
                    int sum = 0;
                    for (int i = 0; i <= 100; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
            //TODO
            list.add(result);
        }
        pool.shutdown();  //等待线程完成任务后，线程池才对线程进行回收。
        //pool.shutdownNow(); //线程池立刻对分配出去的线程进行回收，即使线程还没执行完任务。并且回收后不再另外接受分配的任务。
        list.forEach(t -> {
            try {
                System.out.println(t.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
