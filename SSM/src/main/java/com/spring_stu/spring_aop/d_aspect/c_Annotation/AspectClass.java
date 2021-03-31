package com.spring_stu.spring_aop.d_aspect.c_Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectClass {

    @Pointcut("@annotation(com.spring_stu.spring_aop.d_aspect.c_Annotation.NeedTest)")
    public void needTestPoint(){

    };
    //获取注解的对象
    @Around("needTestPoint() && @annotation(needTest)")
    public Object needTest(ProceedingJoinPoint joinPoint,NeedTest needTest) throws Throwable {
        System.out.println("@NeedTest ：方法执行前");
        System.out.println("NeedTest.value = "+needTest.value());
        Object resout = joinPoint.proceed();
        System.out.println("@NeedTest ：方法执行后");

        return resout;
    }
//    @Around("needTestPoint()")
//    public Object needTest2(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("@NeedTest ：方法执行前");
//        Object resout = joinPoint.proceed();
//        System.out.println("@NeedTest ：方法执行后");
//
//        return resout;
//    }
//    @AfterReturning("@annotation(com.spring_stu.spring_aop.d_aspect.c_Annotation.NeedTest)")
//    public void needTest() {
//        System.out.println("needTest() executed,some logic is here");
//    }
}

