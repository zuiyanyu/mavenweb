package com.spring_stu.spring_scheduled.b_annotation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {

    @Scheduled(fixedDelay = 1000)
    public void fixedDelay(){
        System.out.println("=====================fixedDelay=====================");
    }

    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
        System.out.println("=====================fixedRate=====================");
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void cron(){
        System.out.println("=====================cron=====================");
    }
}
