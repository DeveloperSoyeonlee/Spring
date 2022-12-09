package com.itwillbs.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	
	// 로그 출력 객체
	private static final Logger logger 
		= LoggerFactory.getLogger(SampleController3.class);	

	// 동작 처리 이후에 페이지 이동(주소이동ㅇ ,not mean view)
	// 페이지 리다이렉트 사용
	
	// http://localhost:8080/web/doE
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr/*Model model*/) {
		logger.info(" EEEEEEEEEEEEEEEEEEEEEEEEE ");
		
//		rttr.addAttribute("msg", "ITWILL"); 	 // 기존의 Model 객체와 동일
												 // - URL에 표시 됨
												 // - F5(새로고침)시 데이터 유지
		
		rttr.addFlashAttribute("msg", "ITWILL"); // 전달 정보를 1회성으로 사용 가능
												 // - URL에 표시 안됨
												 // - F5(새로고침)시 데이터 사라짐
												 // - redirect 이동시만 사용가능
		
//		model.addAttribute("msg", "ITWILL");
		// => 주소줄에 파라미터 형태로 전달 /doF?msg=ITWILL
		
//		return"/doF"; // 리다이렉트가 아니라 just 뷰페이지 출력함
//		return "redirect:/doF"; // 리다이렉트 O
		return "forward:/doF?msg=hello"; // 리다이렉트 O
		
		
	}
	
	// http://localhost:8080/web/doF
	@RequestMapping("/doF")
	public String doF(@ModelAttribute("msg") String msg) {
		logger.info(" FFFFFFFFFFFFFFFFFFFFFFFFF ");
		logger.info("msg : " + msg);
		return"doF실행";
		
	}
}
