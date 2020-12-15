package com.spring_stu.spring_event_listener.sendEmail;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * 事件监听器MailSenderListener负责监听MailSendEvent事件
 *
 */
@Component
public class MailSenderListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof MailSendEvent ){
            //对MailSendEvent事件进行处理
            MailSendEvent mse = (MailSendEvent)event;
            System.out.println("MailSendEvent:向"+mse.getTo()+"发送了一份邮件！");
        }else{
            System.out.println("其他事件："+event);
        }
    }
}
