package com.quartz.Schedule;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

/**
 * quartz.properties 的配置：
 * Default Properties file for use by StdSchedulerFactory
 * to create a Quartz Scheduler Instance, if a different
 * properties file is not explicitly specified.
 * org.quartz.scheduler.instanceName = DefaultQuartzScheduler
 * org.quartz.scheduler.rmi.export = false
 * org.quartz.scheduler.rmi.proxy = false
 * org.quartz.scheduler.wrapJobExecutionInUserTransaction = false
 * org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
 * org.quartz.threadPool.threadCount = 10
 * org.quartz.threadPool.threadPriority = 5
 * org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread = true
 * org.quartz.jobStore.misfireThreshold = 60000
 * org.quartz.jobStore.class =org.quartz.simpl.RAMJobStore
 *
 * quartz的配置文件中org.quartz.scheduler.instanceName = DefaultQuartzScheduler是配置的系统帮我们生成的scheduler的对象，
     1) 当我们在程序中 scheduler = schedulerFactory.getScheduler();获取多个scheduler时，其实我们引用的是同一个对象，
     2) 如果我们想要shutdown其中的某个scheduler时，其他的都会被shutdown！！
 *
 * 怎么才能获取多个独立的scheduler呢
 */
public class MultScheduler {
    public static void main(String[] args) throws SchedulerException {

        //TODO 1.Scheduler的单例引用：使用默认的配置文件创建的schedule工厂对象，获取的Scheduler是同一个实例引用。
        // 使用默认的配置文件 创建schedule工厂对象
        SchedulerFactory schedulerFactory_01=new StdSchedulerFactory();
        //可以看到，获取的是同一个Scheduler实例引用
        Scheduler scheduler1 = schedulerFactory_01.getScheduler();
        Scheduler scheduler2 = schedulerFactory_01.getScheduler();
        System.out.println("scheduler1==scheduler2结果："+(scheduler1==scheduler2));//true
        System.out.println("scheduler1.getSchedulerName()："+scheduler1.getSchedulerName());//DefaultQuartzScheduler
        System.out.println("scheduler2.getSchedulerName()："+scheduler2.getSchedulerName());//DefaultQuartzScheduler

        System.out.println("====================================================");
        //TODO 2. Scheduler的多例创建：
        //使用自定义配置属性进行创建另外的一个schedule工厂对象
        SchedulerFactory schedulerFactory_02  =new StdSchedulerFactory();

        //自定义配置文件值
        Properties properties=new Properties();
        properties.put("org.quartz.scheduler.instanceName", "Scheduler");
        //pro.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount", "10");

       //Quartz支持配置文件初始化的
        StdSchedulerFactory stdSchedulerFactory_tmp = (StdSchedulerFactory)schedulerFactory_02;
        stdSchedulerFactory_tmp.initialize(properties);

        //获取一个新的非默认的org.quartz.scheduler.instanceName
        Scheduler scheduler3 = schedulerFactory_02.getScheduler();
        Scheduler scheduler4 = schedulerFactory_02.getScheduler();
        System.out.println("scheduler3==scheduler4结果："+(scheduler3==scheduler4)); //true
        System.out.println("scheduler3.getSchedulerName()："+scheduler3.getSchedulerName());//Scheduler
        System.out.println("scheduler4.getSchedulerName()："+scheduler4.getSchedulerName());//Scheduler
        System.out.println("====================================================");

        System.out.println("scheduler1==scheduler4结果："+(scheduler1==scheduler4)); //false

        //同理，通过实现StdSchedulerFactory的多例，从而实现Scheduler的多例。
    }
}
