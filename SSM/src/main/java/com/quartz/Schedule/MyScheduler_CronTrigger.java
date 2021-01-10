package com.quartz.Schedule;

import com.quartz.jobs.PrintWordsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class MyScheduler_CronTrigger {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //TODO 1. 创建Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //TODO 2. 创建JobDetail
        JobDetail jobDetail =  JobBuilder
                .newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1")
                .build();

        //TODO 3. 创建Trigger
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/1 41 15 * * ?");
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(cronScheduleBuilder)
                .build();
        //TODO 4. 使用Scheduler进行任务调度
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");
    }
}
