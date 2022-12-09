package com.itwillbs.spring;

public class HelloApp {
	
	public static void main(String[] args) {
		//MessageBean - sayHello() 실행
		
		// MessageBean객체 생성 => why? 필요하니까!
		//  MessageBean객체 의존하고있다.(없으면 안됨)
		// HelloApp - MessageBean 
		//        의존관계
		
		// MessageBean객체 생성
		MessageBean mb = new MessageBean(); //강한결합
		mb.sayHello("홍길동");
		
		
		
		
		
	}

}
