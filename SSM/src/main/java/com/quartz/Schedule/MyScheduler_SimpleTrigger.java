package com.quartz.Schedule;

import com.quartz.jobs.PrintWordsJob;
import com.quartz.listeners.MyJobListener;
import com.quartz.listeners.MySchedulerListener;
import com.quartz.listeners.MyTriggerListener;
import com.quartz.originCode.factory.StdSchedulerFactory_quartz;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * 1. Scheduler就是一个JobDetail和Trigger的注册器。
 * 2. 一旦Scheduler被注册，那么，当Trigger被点火(即任务调度时间到了)的时候，Scheduler就要执行与Trigger相关联的任务。
 * 3. Scheduler实例是被SchedulerFactory创建的，一旦Scheduler实例被创建，就会被缓存起来供下次使用。
 * 4. Scheduler实例被新建的时候处于"stand-by"模式，所以每个Scheduler实例必须调用自己的start()方法，它才能真正开始工作。
 * 5. job是被客户端的程序创建的，创建的方式为：自己定义一个类，并实现org.quartz.Job接口，然后实现execute()方法就可以了。
 *    execute()方法里面的代码就是要job能实现的功能。
 * 6. JobDetail 也是被客户端程序创建的，JobDetail是为了定义作业的个体，而job只是定义作业实现的功能。
 * 比如:我有两个作业分别较 work01,work02, 但是他们都要执行同一个功能(功能在job中定义)，所以两个任务可以持有同一个Job。
 *
 * 7.通过 Scheduler的scheduleJob(JobDetail, Trigger)方法或者addJob(JobDetail, boolean)方法，能将JobDetail注册到Scheduler当中
 * 8.Trigger可以被定义为是一个任务执行的时间表，即一个任务何时被执行。
 * 对于任务每次执行时间是确定的,或者任务只执行一次的时候，常用SimpleTrigger作为任务的触发器 。
 * CronTrigger触发器，允许关联的任务可以基于day, day of week, day of month,month of year ，即基于cron表达式来设置触发时机。
 *
 *9.  JobDetail 和 Trigger 有一个name名和一个所属组，并且在一个Scheduler中: 所属组 + name名是唯一的。
 * 任务所属组是为了给执行的任务进行分类，任务名称是为了方便确定任务。
 *
 * 10. Schedule可以使用triggerJob(String jobName, String jobGroup)方法手动触发某一个任务执行。
 *
 *11. 可以端程序可以被监听器"listener" 替代，Quartz中的监听器有：
 * 1）JobListener接口 提供通知job任务执行的功能。
 * 2）TriggerListener接口 提供通知Trigger触发器执行的功能。
 * 3) SchedulerListener接口 提供通知Scheduler事件和异常。
 * ListenerManager将各种不同类型的监听器进行关联，来统一完成一个任务的监听功能。
 *
 * 12 通过setup/configuration 设置或配置，可以定制一个调度器实例。 具体请参考官方文档。
 * 13 一个trigger只能绑定一个jobtail, 但是一个jobtail可以被绑定到不同的trigger上。
 */
public class MyScheduler_SimpleTrigger {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //TODO 1、创建调度器Scheduler
        //SchedulerFactory 默认实例为： StdSchedulerFactory
        StdSchedulerFactory_quartz schedulerFactory = new StdSchedulerFactory_quartz();

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
                .withIntervalInSeconds(10)//每隔1s执行一次 默认值为 0，即不进行重复执行，任务只执行一次。
                .repeatForever() //trigger可重复执行次数为无限次,前提是当前时间在 trigger的[startTime,endTime]时间区间内
                .withRepeatCount(2);//trigger可重复执行次数为2次
        //开始构建trigger  实例为：SimpleTriggerImpl
        Trigger trigger = TriggerBuilder
                .newTrigger() //返回 new TriggerBuilder<Trigger>()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效 即设置任务生效日期为当期日期  默认生效时间为当前时间： new Date()
                //.endAt() //设置 trigger的失效日期 默认值为null 永不失效
                .withSchedule(simpleScheduleBuilder) //在build()中会默认赋值： SimpleScheduleBuilder.simpleSchedule()
                .build();//使用 this.scheduleBuilder 创建 一个trigger 实例(MutableTrigger)。并将上面的参数都设置进trigger实例

        //监听器管理器
        ListenerManager listenerManager = scheduler.getListenerManager();
        //添加任务调度监听器
        listenerManager.addSchedulerListener(new MySchedulerListener());
        //添加任务监听器
        listenerManager.addJobListener(new MyJobListener());
        listenerManager.addTriggerListener(new MyTriggerListener());
        //TODO 4、执行
        // jobDetail中记录着要工作的任务和一些任务的描述等
        // trigger中记录着trigger的名称、所属组，以及被调度器执行的开始时间，结束时间，开始结束时间内被调度的次数。
        Date date = ((StdScheduler)scheduler).scheduleJob(jobDetail, (SimpleTriggerImpl)trigger);
        System.out.println("--------scheduler start ! ------------"+date);
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");


    }
}
