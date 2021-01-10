package com.quartz.Schedule;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

public class JDBCJobStoreRunner {
    public static void main(String[] args) throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //①获取调度器中所有的触发器组
         List<String> triggerGroups = scheduler.getTriggerGroupNames();
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
        for (String triggerGroup : triggerGroups) {

        }
    }
}
