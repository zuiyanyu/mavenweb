package com.quartz.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.listeners.JobListenerSupport;

public class MyJobListener extends JobListenerSupport {

    @Override
    public String getName() {
        return "myJobListener";
    }
    public void jobToBeExecuted(JobExecutionContext context) {
        JobKey key = context.getJobDetail().getKey();
        String jobName = key.getName();
        String jobGroup = key.getGroup();
        System.out.println("任务将被执行,任务名为name："+jobName + " 任务组为group："+ jobGroup);
    }

    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("任务被拒绝执行！");
    }

    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobKey key = context.getJobDetail().getKey();
        String jobName = key.getName();
        String jobGroup = key.getGroup();
        System.out.println("任务已经被执行完毕,任务名为name："+jobName + " 任务组为group："+ jobGroup);
    }
}
