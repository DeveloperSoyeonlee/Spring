package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController3 {

	
	// 로그 출력 객체
	private static final Logger logger 
		= LoggerFactory.getLogger(SampleController3.class);
	
	// 객체 -> 뷰페이지로 전달해보기
	// /doD 주소를 호출했을 때 => itwillMember.jsp라는 페이지를 연결 후
	// 유저정보를 출력해보기
	
	// http://localhost:8080/web/doD
	// http://localhost:8080/web/doD?userid=itwill&userpw=55555
	@RequestMapping("/doD")
	public String doD(Model model,MemberVO vo) /*@ModelAttribute("userid") String userid)*/ {
		
		logger.info(" doD() 호출 -> itwillMember.jsp 연결"); //연결하려면 같은 이름으로 리턴해야함
		
		// 객체 정보를 전달 => Model 객체 
		// model.addAttribute(attributeName, attributeValue)
//		MemberVO vo = new MemberVO();
//		vo.setUserid("admin");
//		vo.setUserpw("1234");
//		vo.setUsername("관리자");
//		vo.setUseremail("admin@admin.com");
		
		// 모델 객체에 정보를 저장(연결된 뷰페이지에 전달)
//		model.addAttribute("vo", vo);
		model.addAttribute(vo);
		// => 어트리뷰트의 이름이 없는 경우, 전달되는 객체의 클래스명을 사용
		//    (첫글자를 소문자로 바꿈)
		
//		model.addAttribute("userid", userid);
		
		return "itwillMember";
	}
	
//	@RequestMapping("/MemberJoin")
//	public String MemberJoinAction() {
//		return"main";
//	}
//	
//	@RequestMapping("/Main")
//	public String MainAction() {
//		return"main";
//	}
//	
	
	
	
	
	
	
}
