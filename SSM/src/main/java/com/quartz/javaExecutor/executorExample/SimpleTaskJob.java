package com.quartz.javaExecutor.executorExample;

/**
 * 任务类
 */
public class SimpleTaskJob implements Runnable {

    private String taskName = null;

    public SimpleTaskJob(String taskName) {
        this.taskName = taskName;
    }

    public void run() {
        System.out.println("执行名为: " + taskName + " 的线程。 线程编号为: " + Thread.currentThread().getId() );
    }
}