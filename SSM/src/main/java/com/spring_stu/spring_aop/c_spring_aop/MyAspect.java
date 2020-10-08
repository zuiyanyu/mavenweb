package com.spring_stu.spring_aop.c_spring_aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 切面类，含一个通知
 *
 * 方式3： Spring AOP全自动
 * 从Spring容器中获取目标类，如果配置aop，spring将自动生成代理。
 */
public class MyAspect implements MethodInterceptor {

	//采用“环绕通知”
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		System.out.println("鸡首");

		//手动执行目标方法
		Object retResult = methodInvocation.proceed();

		System.out.println("牛后");
		return retResult;
	}
}
