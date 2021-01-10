package com.quartz.jobs;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description: 打印任意内容
 */
public class PrintWordsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintWordsJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));

    }
}
