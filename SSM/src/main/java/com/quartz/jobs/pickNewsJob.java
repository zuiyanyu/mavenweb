package com.quartz.jobs;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class pickNewsJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("在"+sdf.format(new Date())+"扒取新闻");
    }

    /**
     * 我们不想执行新的任务，只想纯粹地恢复之前异常中断任务
     *
     * @throws SchedulerException
     */
    public static void main(String[] args) throws SchedulerException  {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        StdScheduler scheduler = (StdScheduler)schedulerFactory.getScheduler();


        //TODO 从 QRTZ_SIMPLE_TRIGGERS 表中获取的 triggerGroupNames 和  triggerKeys
        // ①获取调度器中所有的触发器组
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
        for (String triggerGroupName : triggerGroupNames) {

            //根据triggerGroupName 获取 里面的所有trigger对应的triggerKeys
            GroupMatcher groupMatcher =  GroupMatcher.triggerGroupEquals(triggerGroupName);
            Set<TriggerKey>  triggerKeys = scheduler.getTriggerKeys(groupMatcher);
            System.out.println("============triggerGroupName("+triggerGroupName+")================");

            //将triggerGroupName 下面的所有未完成的trigger重新进行恢复运行
            for (TriggerKey triggerKey : triggerKeys) {
                //triggerKey = DEFAULT.trigger4  ; triggerKey = triggerGroup.trigger4
                 System.out.println("triggerKey = "+triggerKey);

                Trigger trigger = scheduler.getTrigger(triggerKey);
                //根据名称判断 选择性的恢复某些trigger
                if (trigger instanceof SimpleTrigger && trigger.toString().equals("triggerGroup.trigger4")) {
                    //恢复运行
                    scheduler.rescheduleJob(triggerKey,trigger);
                }
                scheduler.rescheduleJob(triggerKey,trigger);
            }
            scheduler.start();

        }
    }

    /**
     * 执行新的任务，并恢复上次中断没执行完的任务继续执行。
     *
     * @throws SchedulerException
     */
    public static void main3(String[] args) throws SchedulerException  {
        JobDetail jobDetail = JobBuilder.newJob(pickNewsJob.class)
                .withIdentity("job7", "jgroup1").build();
        SimpleTrigger simpleTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger4","triggerGroup")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 5))
                .startNow()
                .build();

        //创建scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
    }
}
