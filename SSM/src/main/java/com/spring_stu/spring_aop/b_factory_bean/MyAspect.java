package com.spring_stu.spring_aop.b_factory_bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 切面类，含一个通知
 *
 * 切面类中确定通知，需要实现不同接口，接口就是规范，从而确定方法名称。
 *
 * 采用“环绕通知” MethodInterceptor
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
