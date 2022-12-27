package com.itwillbs.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberRestController {

		
		
		private static final Logger mylog = LoggerFactory.getLogger(MemberRestController.class);

		// 디비접근을 위한 서비스객체 주입
		@Inject
		private MemberService service;
		
		
		// 아이디정보를 전달받아서 서비스에서 해당아이디가 중복인지 여부 판단 : 데이터 조회
		@RequestMapping(value="/ckID", method=RequestMethod.GET)
		public String checkID(MemberVO vo,
				@ModelAttribute("userid") String userid) throws Exception{
			mylog.debug(" checkID() 호출 ");
			mylog.debug(vo+"");
			mylog.debug(userid+"");
			
			MemberVO checkVO = service.getMember(userid); // 마우스 올려보면 MemberVO로 리턴받음
			mylog.debug(checkVO+"");
			//service.getMember(vo.getUserid()); // 둘다가능
			
			if(checkVO == null) {
				// 디비에 정보가 없다 -> 해당아이디 사용 가능
				return "OK";
			
			}else {
				// 디비에 정보가 있다 -> 해당아이디 사용 불가
				return "NO";
			}
			
			
		}
}
