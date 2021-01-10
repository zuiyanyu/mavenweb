package com.quartz.javaTimer.job;

import com.quartz.javaTimer.TimeUtil;
import com.quartz.javaTimer.originCode.TimerTask;

import java.util.Date;

/**
 * TimerTask相当于 quartz的有状态任务StatefulJob  +  jobDetail + trigger,
 * TimerTask里面封装了要执行的任务(job)，任务的信息(jobDetail)，和任务的下次调度点(trigger)。
 *
 * TimerTask 实现了Runnable,所以 SimpleTimerTask是一个线程。
 */
public class SimpleTimerTask extends TimerTask {

    private int count = 0;
    @Override
    public void run() {
        System.out.println("execute task.");
        Date exeTime = new Date(scheduledExecutionTime());//①获取本次安排执行的时间点

        System.out.println("本次任务执行时间点为："+TimeUtil.getDate(exeTime));
        System.out.println("实际任务执行时间点为："+TimeUtil.getDate(System.currentTimeMillis()) );

        try {
            Thread.sleep(2000L);
            System.out.println("---延时2秒后---："+TimeUtil.getDate(System.currentTimeMillis()) );
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(++count >= 5){ cancel(); }    //②在任务执行5次后主动退出
    }
}
