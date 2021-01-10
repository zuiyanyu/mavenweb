package com.quartz.javaExecutor.executorExample;

import com.quartz.javaExecutor.originCode.Executors;
import com.quartz.javaExecutor.originCode.ThreadPoolExecutor;

import java.util.concurrent.Executor;

//import java.util.concurrent.Executors;

/**
 * TODo 不能对任务进行调度，可执行任务：
 * TODo public interface Executor   直接执行任务，或直接new 一个线程执行任务
 * TODo public interface ExecutorService extends Executor  增加取消任务的功能
 * TODo public class ThreadPoolExecutor extends AbstractExecutorService  使用线程池执行 任务
 *     public abstract class AbstractExecutorService implements ExecutorService
 *
 * TODO 可以对任务进行调度，可执行任务：
 * TODO public interface ScheduledExecutorService extends ExecutorService 对任务进行固定延时或固定频率调度等。 (但是没有使用线程池对任务的执行数量进行限制)
 * TODO public class ScheduledThreadPoolExecutor extends ThreadPoolExecutor implements ScheduledExecutorService
 *      TODO 既能对任务进行定时调度，又能对任务使用线程池执行。
 */
public class ExecutorExample {
    private Executor executor = null; // 声明一个执行器，提供Setter方法

    private static int poolSize = 3; // 线程池中固定的线程数量

    /**
     * 用执行器执行多个任务
     */
    public void executeTasks() {
        for (int i = 0; i < 9; i++) {
            executor.execute(new SimpleTaskJob("任务" + i));
        }
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public static void main(String[] args) {
//        ExecutorExample example = new ExecutorExample();
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(poolSize);
//
//        example.setExecutor((ThreadPoolExecutor)fixedThreadPool);
//
//        example.executeTasks();
        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor)Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < 9; i++) {
            fixedThreadPool.execute(new SimpleTaskJob("任务" + i));
        }
    }
}


