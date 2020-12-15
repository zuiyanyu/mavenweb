package com.spring_stu.spring_aop.d_aspect.b_anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 使用注解实现 aop
 * @Aspect 声明切面，修饰切面类，从而获得 通知。
 *
 * 通知
 * 	@Before 前置
 * 	@AfterReturning 后置
 * 	@Around 环绕
 * 	@AfterThrowing 抛出异常
 * 	@After 最终
 *
 * 切入点
 * 	@PointCut ，修饰方法 private void xxx(){}  之后通过“方法名”获得切入点引用
 */
@Component
@Aspect
public class MyAspect {
	
	//切入点当前有效
//	@Before("execution(* com.itheima.d_aspect.b_anno.UserServiceImpl.*(..))")
	public void myBefore(JoinPoint joinPoint){
		System.out.println("前置通知 ： " + joinPoint.getSignature().getName());
	}
	
	//声明公共切入点
	@Pointcut("execution(* com.spring_stu.spring_aop.d_aspect.b_anno.UserServiceImpl.*(..))")
	private void myPointCut(){
		System.out.println("private void myPointCut()");
	}
	
//	@AfterReturning(value="myPointCut()" ,returning="ret")
	public void myAfterReturning(JoinPoint joinPoint,Object ret){
		System.out.println("后置通知 ： " + joinPoint.getSignature().getName() + " , -->" + ret);
	}
	
	@Around(value = "myPointCut()")
	public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("前"+joinPoint.getSignature().getName());
		//手动执行目标方法
		Object obj = joinPoint.proceed();
		
		System.out.println("后");
		System.out.println("--------------");
		return obj;
	}
	
//	@AfterThrowing(value="execution(* com.itheima.d_aspect.b_anno.UserServiceImpl.*(..))" ,throwing="e")
	public void myAfterThrowing(JoinPoint joinPoint,Throwable e){
		System.out.println("抛出异常通知 ： " + e.getMessage());
	}
	
//	@After("myPointCut()")
//	public void myAfter(JoinPoint joinPoint){
//		System.out.println("最终通知");
//		System.out.println("--------------");
//	}

}
