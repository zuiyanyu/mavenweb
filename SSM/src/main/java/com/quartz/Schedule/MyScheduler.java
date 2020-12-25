package com.quartz.Schedule;

import com.quartz.jobs.PrintWordsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyScheduler {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //TODO 1、创建调度器Scheduler
        //默认实例为： StdSchedulerFactory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        //scheduler 默认实例为 StdScheduler    StdScheduler implements Scheduler  用来调度任务
        Scheduler scheduler = schedulerFactory.getScheduler();

        //TODO 2、创建JobDetail实例，并与PrintWordsJob类绑定(PrintWordsJob就是要执行的Job,里边编写了Job执行内容)
        //jobDetail 就是Job，只是除了保存要执行的功能任务(PrintWordsJob中只保存job任务)以外,
        // 还能保存任务所属组、任务名称等job的其他详细信息。
        //默认创建的实例为 JobDetailImpl
        JobDetail jobDetail = JobBuilder
                .newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1")
                .build();



        // TODO 3、构建Trigger实例,每隔1s执行一次
        /**
         *  用来创建Trigger实例  SimpleScheduleBuilder extends ScheduleBuilder<SimpleTrigger>
         *  SimpleScheduleBuilder应该叫做 TriggerBuilder 。
         *
         *  ScheduleBuilder<T extends Trigger>的接口方法：
         *       protected abstract MutableTrigger build();
         *
         */
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()  //将trigger绑定到 schedule上
                .withIntervalInSeconds(1)//每隔1s执行一次 默认值为 0，即不进行重复执行，任务只执行一次。
                .repeatForever() //trigger可重复执行次数为无限次,前提是当前时间在 trigger的[startTime,endTime]时间区间内
                .withRepeatCount(2);//trigger可重复执行次数为2次
        //开始构建trigger
        Trigger trigger = TriggerBuilder
                .newTrigger() //返回 new TriggerBuilder<Trigger>()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效 即设置任务生效日期为当期日期  默认生效时间为当前时间： new Date()
                //.endAt() //设置 trigger的失效日期 默认值为null 永不失效
                .withSchedule(simpleScheduleBuilder) //在build()中会默认赋值： SimpleScheduleBuilder.simpleSchedule()
                .build();//使用 this.scheduleBuilder 创建 一个trigger 实例(MutableTrigger)。并将上面的参数都设置进trigger实例

        //4、执行
        // jobDetail中记录着要工作的任务和一些任务的描述等
        // trigger中记录着trigger的名称、所属组，以及被调度器执行的开始时间，结束时间，开始结束时间内被调度的次数。
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------"+date);
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");


    }
}
