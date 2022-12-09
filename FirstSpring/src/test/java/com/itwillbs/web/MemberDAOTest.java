package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)


public class MemberDAOTest {
	
		// DAO 객체 필요함
		@Inject
		private MemberDAO dao;
		// MemberDAO dao = new MemberDAO(); 의미
		
		
//		@Test
		public void 시간정보조회하기() {
			System.out.println(dao.getServerTime());
		}
	
		// 회원정보저장(Create)
		@Test
		public void createUser() {
			
			// 회원정보 생성(사용자 입력 정보)
			MemberVO vo = new MemberVO();
			vo.setUserid("itwill02");
			vo.setUserpw("1234");
			vo.setUsername("사용자02");
			vo.setUseremail("user02@user.com");
			
			dao.insertMember(vo); //insert멤버를 호출한다
			
		}
		
		
//		// 특정 회원의 모든 정보를 조회
//		@Test
		public void getUserInfo() {
			// DAO안에있는 메서드 호출하기
			MemberVO vo = dao.getMember("itwill01");
			
			System.out.println(" Test : " + vo);
			
		}
		
		// 로그인 처리 
//		@Test
		public void loginMember() {
			
			MemberVO vo = dao.loginMember("admin", "1234");
			
			if(vo != null) {
				System.out.println(" 로그인 성공! ");
			}else {
				System.out.println(" 로그인 실패! ");			
			}
			
			
			MemberVO inputVo = new MemberVO();
			inputVo.setUserid("admin");
			inputVo.setUserpw("123224");
			
			MemberVO vo2 = dao.loginMember(inputVo);
			
			if(vo2 != null) {
				System.out.println(" 로그인 성공! ");
			}else {
				System.out.println(" 로그인 실패! ");			
			}
			
				
			}
		
}
