package com.quartz.javaTimer.scheduler;

import com.quartz.javaTimer.job.SimpleTimerTask;
import com.quartz.javaTimer.originCode.Timer;
import com.quartz.javaTimer.originCode.TimerTask;


public class TimerRunner {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new SimpleTimerTask();
        //timer相当于 调度器 Scheduler  固定延时执行
//        timer.schedule(task,1000L,1000L);     //①在延迟1秒后，每5秒执行一次任务

        //TODO  固定频率并非是严格意义上的固定频率
        // 如果执行时间 > 任务固定周期的大小：当执行时间超过固定周期的大小，就会产生时间漂移（要注意）
        //如果执行时间 <= 任务固定周期的大小：那么就不会产生时间漂移
        timer.scheduleAtFixedRate(task,1000L,1000L);

        //取消任务对列中所有的未执行的任务，结束背景线程的运行。
//        timer.cancel();
//        //取消当前的任务
//        task.cancel();
    }
}
