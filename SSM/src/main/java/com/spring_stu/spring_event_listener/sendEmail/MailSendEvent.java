package com.spring_stu.spring_event_listener.sendEmail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 事件
 * 它直接扩展ApplicationContextEvent，事件对象除了source外，还有一个代表发送目的地的to属性。
 */
public class MailSendEvent extends ApplicationContextEvent {
    private String to ;
    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public MailSendEvent(ApplicationContext source,String to) {
        super(source);
        this.to = to ;
    }

    public String getTo() {
        return to;
    }
}
