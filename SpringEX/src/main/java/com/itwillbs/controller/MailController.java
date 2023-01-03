package com.itwillbs.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.service.MailService;

//@EnableAsync : 비동기방식의 호출을 사용 가능

// http://localhost:8080/sendMail.will
@Controller
@EnableAsync
public class MailController {

		
	private static final Logger mylog = LoggerFactory.getLogger(MailController.class);

	
	// Mail 서비스 객체 생성 ( 객체의존주입통해 사용할 수 있도록)
	@Inject
	private MailService mailService;
	
	
	///sendMail.will 가상주소 호출시 가상주소를 제외한 뷰 페이지 연결
	@RequestMapping(value = "/sendMail.will" , method = RequestMethod.GET)
	public void sendMail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		mylog.debug(" sendMail() 호출 "); 
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
//		out.print(" 메일전송 성공! ");
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html><body>");
		sb.append(" <h1>안녕하세요</h1> ");
		sb.append(" <a href='http://www.itwillbs.co.kr'>");
		sb.append(" <img src='https://t1.daumcdn.net/liveboard/fashionn/c0b84e3d1024480e98a7bee860cc990d.JPG'>");
		sb.append(" </a>");		
		sb.append("</body></html>");
		
		String str ="";
		str += "<html><body>";
		str += " <h1>안녕하세요</h1> ";
		str += " <a href='http://www.itwillbs.co.kr'>";
		str += " <img src='https://t1.daumcdn.net/liveboard/fashionn/c0b84e3d1024480e98a7bee860cc990d.JPG'>";
		str += " </a>";
		str += "</body></html>";
		
		
		
		// 메일보내기
		//					받는주소			제목				  내용
//		mailService.senMail("developersoyeon@naver.com", "테스트제목입니다용", "테스트내용입니다용");
		mailService.senMail("developersoyeon@naver.com", "테스트제목입니다용", sb+"");
		

		// return "sendMail.will"; 그대로 호출 (리턴타입 String일 경우 )
	}

}
