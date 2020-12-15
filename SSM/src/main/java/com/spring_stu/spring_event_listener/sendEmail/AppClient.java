package com.spring_stu.spring_event_listener.sendEmail;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppClient {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigClass.class);

        //开始发送邮件，并触邮件发事件的处理
        MailSender mailSender = applicationContext.getBean("mailSender", MailSender.class);
        mailSender.sendMail("张三");

        applicationContext.close();
    }
}
