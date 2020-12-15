package com.spring_stu.spring_aop.a_proxy.b_cglib;

import com.spring_stu.spring_aop.a_proxy.MyAspect;
import com.spring_stu.service.serviceImpl.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class MyBeanFactory {
	
	public static UserServiceImpl createService(){
		//1 目标类
		final UserServiceImpl userServiceImpl = new UserServiceImpl();
		//2切面类
		final MyAspect myAspect = new MyAspect();
		// 3.代理类 ，采用cglib，底层创建目标类的子类
		//3.1 核心类
		Enhancer enhancer = new Enhancer();
		//3.2 确定父类
		enhancer.setSuperclass(userServiceImpl.getClass());
		/* 3.3 设置回调函数 , MethodInterceptor接口 等效 jdk InvocationHandler接口
		 * 	intercept() 等效 jdk  invoke()
		 * 		参数1、参数2、参数3：以invoke一样
		 * 		参数4：methodProxy 方法的代理
		 * 		
		 * 
		 */
		enhancer.setCallback(new MethodInterceptor(){

			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				
				//前
				myAspect.before();
				
				//执行目标类的方法
				Object obj = method.invoke(userServiceImpl, args);
				// * 执行代理类的父类 ，执行目标类 （目标类和代理类 父子关系）
				methodProxy.invokeSuper(proxy, args);//等价于 method.invoke(userServiceImpl,args) ;
				//method.invoke(userServiceImpl,args) ;
				//后
				myAspect.after();
				
				return obj;
			}
		});
		//3.4 创建代理
		UserServiceImpl proxService = (UserServiceImpl) enhancer.create();
		
		return proxService;
	}

}
