package javaBase.多线程.执行器_07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO 1. 执 行 器
 * TODO *** 1.1 构建一个新的线程是有一定代价的， 因为涉及与操作系统的交互。
 * TODO 1.2 如果程序中创建了大量的生命期很短的线程，应该使用线程池（ thread pool )。
 * TODO 1.3 一个线程池中包含许多准备运行的空闲线程。
 * TODO 1.4 将 Runnable 对象交给线程池，就会有一个线程调用 run 方法（有一个执行器执行run方法）。
 * TODO 1.5 当 run 方法退出时， 线程不会死亡，而是在池中准备为下一个请求提供服务。
 *
 * TODO *** 1.6 另一个使用线程池的理由是减少并发线程的数目。
 *  创建大量线程会大大降低性能甚至使虚拟机崩溃。
 *  如果有一个会创建许多线程的算法， 应该使用一个线程数“ 固定的” 线程池以限制并发线程的总数。
 *
 *
 *TODO 2. 执行器 （ Executor) 类有许多静态工厂方法用来构建线程池.
 * 执行者工厂方法
 * •  newCachedThreadPool
 *      必要时创建新线程；空闲线程会被保留 60 秒
 *
 * •  newFixedThreadPool
 *      该池包含固定数量的线程；空闲线程会一直被保留
 *
 * • newSingleThreadExecutor
 *      只有一个线程的 “ 池”， 该线程顺序执行每一个提交的任务（类似于Swing 事件分配线程）
 *
 * • newScheduledThreadPool
 *      用于预定执行而构建的固定线程池， 替代 java.util.Timer
 *
 * • newSingleThreadScheduledExecutor
 *      用于预定执行而构建的单线程 "池"
 *
 *
 *
 *
 *
 *
 *
 */
public class 执行器_01 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
