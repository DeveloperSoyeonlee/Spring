package com.itwillbs.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice : 컨트롤러에서 발생하는 모든 예외를 처리하는 객체
@ControllerAdvice
public class CommonExceptionAdvice {
	// Advice : 보조기능을 구현한 객체 
	// 주기능 : 게시판만들기 수정삭제 등등
	// 보조기능 : 그 이외의 로그출력, 예외처리하기..
	
	
	private static final Logger mylog 
		= LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	//http://localhost:8080/board/read?bno=1
	//http://localhost:8080/board/read
//	@ExceptionHandler(Exception.class)
//	public String commonTest1(Exception e) {
//		mylog.debug(" 예외 발생 !! ");
//		mylog.debug(e.getMessage());
//		
//		return "test1";
//	}
	
	
	@ExceptionHandler(Exception.class)
	public String commonTest2(Exception e, Model model) {
		mylog.debug(" 예외 발생 !! ");
		mylog.debug(e.getMessage());
		model.addAttribute("err", e.getMessage());
		
		return "test1";
	}

	
	// 기존의 객체는 컨트롤러가 아니기 때문에, model 객체를 사용할 수 없었다
	// -> model 객체와 view페이지 정보를 한번에 전달가능한 객체 ModelAndView 사용하여 정보전달O
//	@ExceptionHandler(Exception.class)
//	public ModelAndView commonTest3(Exception e, Model model) {
//		mylog.debug(" 예외 발생 !! ");
//		mylog.debug(e.getMessage());
//
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/test1");
//		mav.addObject("err",e.getMessage());
//		
//		return mav;
//	}
	
	
}
