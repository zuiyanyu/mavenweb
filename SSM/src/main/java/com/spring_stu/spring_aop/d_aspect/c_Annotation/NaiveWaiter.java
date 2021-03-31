package com.spring_stu.spring_aop.d_aspect.c_Annotation;

import org.springframework.stereotype.Component;

@Component
public class NaiveWaiter implements Waiter {

    @NeedTest(value = true)
    @Override
    public void greetTo(String clientName) {
        System.out.println("greetTo is running ... clientName = "+ clientName);
    }

    @Override
    public void serverTo(String clientName) {
        System.out.println("serverTo is running ... clientName = "+ clientName);

    }
}
