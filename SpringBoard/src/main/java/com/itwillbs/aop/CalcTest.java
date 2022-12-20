package com.itwillbs.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTest {
	
	public static void main(String[] args) {
		
		// 계산기 객체 직접 생성 (강한 결합)
		Calculator cal = new Calculator();
		
		cal.add(1000, 2000);
		cal.div(200, 4);
		System.out.println("-----------------------------------");
		
		
		// AOP가 적용되어있는 객체를 사용해서 구현
		ApplicationContext ctx
			= new ClassPathXmlApplicationContext("AOPTest.xml");
		
		Calculator cal2 = (Calculator)ctx.getBean("proxyCalc");
		
		cal2.add(200, 300);
		
	}

}
