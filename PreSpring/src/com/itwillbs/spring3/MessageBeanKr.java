package com.itwillbs.spring3;

public class MessageBeanKr implements MessageBean{
	@Override
	public void sayHello(String name) {
		System.out.println(name+"님~ 안녕하세요!(KR)");
	}
}
