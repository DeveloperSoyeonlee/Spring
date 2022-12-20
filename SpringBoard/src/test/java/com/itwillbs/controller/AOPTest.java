package com.itwillbs.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.aop.Calculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/AOPTest2.xml" }
		)
public class AOPTest {
	
	// @Qualifier("bean이름") : 주입 받기위해서 해당 이름을 가진 객체를 참조 
	// 							일반객체는 객체를 인식하기 어렵다. 
	//							so @Qualifier를 사용해서 객체를 못찾을때 힌트로 사용한다.

	// @Controller @Repository @Service : 객체를 자동으로 인식기능 포함
	
	@Qualifier("proxyCalc")
	@Inject
	private Calculator calculator;
	
	
	private static final Logger mylog = LoggerFactory.getLogger(AOPTest.class);
	
	@Test
	public void test1() {
		mylog.debug(calculator+"");
		calculator.add(1000, 2000);
	}
	
}
