package com.itwillbs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// advice : 보조기능 (로깅(출력))
public class LoggingAdvice implements MethodInterceptor{

	// 주기능 + 보조기능 호출 메서드
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		System.out.println(" 주기능(메서드) 호출 전 LoggingAdvice");
		System.out.println(invocation.getMethod()+" 호출 전");
		
		Object obj = invocation.proceed(); // 주기능의 메서드(동작)을 호출
		
		System.out.println(" 주기능(메서드) 호출 후 LoggingAdvice");
		System.out.println(invocation.getMethod()+" 호출 후");
		
		return obj;
		
	}

	
}
