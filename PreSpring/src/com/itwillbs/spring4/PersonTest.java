package com.itwillbs.spring4;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {

	public static void main(String[] args) {
		
		// 스프링(beans.xml)에서 제공되는 객체를 주입받아서 사용
		
		// 객체 p 사용해서 사용자 정보 출력
		// (홍길동,20)
		
		// 약한결합을 사용하여 스프링(springBeans.xml)에서 객체를 주입받기
		BeanFactory factory 
		 = new XmlBeanFactory(new FileSystemResource("springBeans.xml"));
		
		Person p1 = factory.getBean("p",Person.class);
		p1.showMyInfo("홍길동", 20);
		
		p1.showMyInfo();
		
		Person p2 = factory.getBean("p2",Person.class);
		p2.showMyInfo();
		
		
		
		
		
		

	}

}
