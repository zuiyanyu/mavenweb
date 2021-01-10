package com.quartz.listeners;

import org.quartz.*;
import org.quartz.listeners.SchedulerListenerSupport;

/**
 * 方式1：implements SchedulerListener
 * 方式2：extends SchedulerListenerSupport
 */
public class MySchedulerListener extends SchedulerListenerSupport {
    @Override
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("MySchedulerListener任务调度器监听器：监听到一个任务被加入到任务调度队列中！");
        System.out.println("MySchedulerListener任务调度器监听器：任务名称name： "+jobDetail.getKey().getName()
                +"； 任务所属组group："+ jobDetail.getKey().getGroup());
    }

}
