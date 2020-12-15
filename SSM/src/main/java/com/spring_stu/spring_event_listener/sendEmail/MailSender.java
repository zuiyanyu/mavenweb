package com.spring_stu.spring_event_listener.sendEmail;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 事件源，普通的bean实例
 * 拥有发布事件的能力，必须实现ApplicationContextAware接口
 */
@Component
public class MailSender implements ApplicationContextAware {
    ApplicationContext applicationContext ;

    //ApplicationContextAware 的接口方法，以便容器启动时候注入容器实例。
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;
    }
    public void sendMail(String to){
        System.out.println("MailSender：模拟发送邮件...");
        MailSendEvent mse = new MailSendEvent(this.applicationContext, to);
        //向容器中的所有事件监听器发送事件
        this.applicationContext.publishEvent(mse);
    }
}
