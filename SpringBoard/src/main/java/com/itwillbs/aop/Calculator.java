package com.itwillbs.aop;


// 타겟 : 주기능(사칙연산 계산기)
public class Calculator {
	
	public void add(int a, int b) {
		System.out.println(" 결과(+) : " +(a+b));
	}
	public void sub(int a, int b) {
		System.out.println(" 결과(-) : " +(a-b));
	}
	public void mul(int a, int b) {
		System.out.println(" 결과(*) : " +(a*b));
	}
	public void div(int a, int b) {
		System.out.println(" 결과(/) : " +(a/b));
	}
}
