package com.quartz.Schedule;

import com.quartz.jobs.PrintWordsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * 我们除了定义一个精确的任务执行时间，还需要排除指定的日期，例如法定节假日等，这个时候我们可以使用Quartz为我们提供的calendar接口，Quartz提供了多种实现类来满足应用的需求：
 *
 * Calendar名称 作用
 * WeeklyCalendar 用于排除星期中的一天或多天
 * MonthlyCalendar 用于排除月份中的数天
 * AnnualCalendar 用于排除年中的一天或多天
 * HolidayCalendar 用于排除节假日
 *
 * 代码演示了AnnualCalendar 实现类如何整合到我们的CronTrigger触发规则内，其他的与之类似
 */
public class MyScheduler_Calendar {
    public static void main(String[] args) throws Exception {
        //TODO 1. 创建Scheduler，并添加日历
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //TODO 设置要排除的执行日期
        //java.util.GregorianCalendar: 公历 日期  五一劳动节的日历
        java.util.Calendar laoDongJie =   new GregorianCalendar();
        laoDongJie.set(Calendar.MONTH,4);
        laoDongJie.set(Calendar.DATE,1);

        //今天  1月4号
        java.util.Calendar today =   new GregorianCalendar();
        today.set(Calendar.MONTH,Calendar.JANUARY);
        today.set(Calendar.DATE,4);

        Date time = today.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println("today = "+ dateFormat.format(time));

        //法定节日是以每年为周期的，所以使用AnnualCalendar
        AnnualCalendar holidays = new AnnualCalendar();
        holidays.setDayExcluded(laoDongJie,true);//true:排除节日 ；false:包含节日
        holidays.setDayExcluded(today,true);//true:排除节日 ；false:包含节日

        //向scheduler注册日历
        scheduler.addCalendar("holidays",holidays,false,false);

        //TODO 2. 创建JobDetail
        JobDetail jobDetail =  JobBuilder
                .newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1")
                .build();

        //TODO 3. 创建Trigger
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/2 * 16 * * ? 2021");
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .modifiedByCalendar("holidays")//底层创建trigger的时候：trig.setCalendarName(calendarName)
                .withSchedule(cronScheduleBuilder)
                .build();
        // .modifiedByCalendar("holidays")等价于下面两步的操作
        //  CronTriggerImpl cronTrigger =(CronTriggerImpl)trigger;
        //  cronTrigger.setCalendarName("holidays");

        //TODO 4. 使用Scheduler进行任务调度

        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");
    }
}
