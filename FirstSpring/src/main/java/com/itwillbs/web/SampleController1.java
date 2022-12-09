package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // : 해당 클래스가 컨트롤러 역할을 수행
			// 	 필요한 설정들에 대한 동작 끝남
@RequestMapping("/test/*")
public class SampleController1 {

	// 로그 출력 객체
	private static final Logger logger 
		= LoggerFactory.getLogger(SampleController1.class);
	
	// * 메서드의 리턴타입이 void 인 경우 호출주소.jsp 뷰 연결함 
	
	// http://localhost:8080/web/doA
	// http://localhost:8080/web/test/doA
	@RequestMapping(value = "/doA")
	public void doA() {
		logger.info("/doA 호출 -> doA 실행"); 
		
	}
	
	// /doB 주소 호출되는 doB() 메서드
	// http://localhost:8080/web/doB
	@RequestMapping(value = "/doB", method = RequestMethod.GET)
//	@GetMapping(value="/doB")
	public void doB() {
		logger.info("/doB 호출 -> doB 실행");
	}
	
	
}
