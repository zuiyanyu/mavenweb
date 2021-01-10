package com.quartz.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.listeners.TriggerListenerSupport;

public class MyTriggerListener extends TriggerListenerSupport {
    @Override
    public String getName() {
        return "MyTriggerListener";
    }
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        TriggerKey triggerKey = trigger.getKey();
        JobKey jobKey = trigger.getJobKey();
        System.out.println("绑定到任务["+jobKey.getGroup()+":"+jobKey.getName()+"]的trigger["+
                triggerKey.getGroup()+":"+triggerKey.getName()+"]被点火了！");
    }

    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    public void triggerMisfired(Trigger trigger) {
        System.out.println("trigger无法被点火！");
    }

}
