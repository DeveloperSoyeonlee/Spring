package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.persistence.MemberDAOImpl;
import com.itwillbs.service.MemberService;
import com.mysql.cj.Session;

/**
 * 	* 컨트롤러를 만들면서 우리가 결정해줘야할 사항이 있다
 *  
 *  - 컨트롤러별 공통 URI를 어떻게 사용할 것인지에 대해 
 *  - 각 주소(URI)별 호출방식 결정 (GET/POST 방식인지 미리 정해야함)
 *  	- GET ( 페이지 조회, 정보 입력페이지/ 디비의 정보를 셀렉해와서 조회)
 *  	- POST ( 글쓰기,삭제,수정 / 개발자가 작업을 수행하는 페이지 ALL )
 *  - 각 주소별 메서드 실행결과 처리 및 이동페이지 설정
 *  - 예외처리
 * 
 *
 */



@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger mylog
		= LoggerFactory.getLogger(MemberController.class);
	
	// 서비스 객체 주입
	@Inject
	private MemberService service;
	
	// http://localhost:8080/web/insertForm : 컨트롤러에 매핑이 없을 때 호출되는 주소
	// http://localhost:8080/web/member/insertForm : 컨트롤러에 매핑했을 때 경로는 
	
	// 톰캣 설정 후 
	// http://localhost:8080/member/insertForm
	// http://localhost:8080/member/insert
	
	// 회원가입 (insert) 메서드로 만들어주기
//	@RequestMapping(value = "insertForm", method = RequestMethod.GET)
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String joingGet() throws Exception {
		
		mylog.info("/member/insertForm -> 정보입력창(view) 이동 ");
		
		// 페이지이동( 할 필요은없음 void 이니까 이미 어디로갈지 정해져있다)
		// 컨트롤러 주소를 포함해서 보내야한다
		return "/member/insertForm";
	
	}
	
	
	// 회원가입 - 처리(insert)
//	@RequestMapping(value = "insertPro", method = RequestMethod.POST)
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String joinPOST(MemberVO vo) throws Exception {
		mylog.info("/member/insertPro -> DB 정보 저장");
		
		// 한글처리
		
		// 전달정보 저장 (둘 중 하나 사용하면 됨)
		mylog.info(vo+"");
		mylog.info(vo.toString());
		
		
		// DB 저장 -> DAO 객체 생성 - 회원가입메서드 호출
//		MemberDAO dao = new MemberDAOImpl(); (x)
//		dao.insertMember(vo); (x) 
		
		// => 직접 생성하지않고 서비스를 통해 DAO 호출
		service.memberJoin(vo);
		
		// 로그인페이지 이동
		return "redirect:/member/login";
	}
	
	// http://localhost:8080/member/login
	// 로그인 ()
	@GetMapping( value = "/login")
	public  void loginGET() throws Exception {
		mylog.debug(" loginGET() 호출 "); 
		//연결된 뷰페이지 구현
	}
	
	
	
	// 로그인 ()
	@PostMapping( value = "/login")
	public String loginPOST(MemberVO vo, HttpSession session /*, @RequestParam("userid") String userid*/) throws Exception {
		mylog.debug(" loginPOST() 호출 "); 

		// 전달정보 저장(userid,userpw)
		mylog.debug(" 로그인정보 : "+vo);
		
		
		// 서비스 - DAO (로그인체크동작 처리할 수 있도록 만들어줌)
		boolean loginStatus = service.memberLogin(vo);
		mylog.debug(" 로그인 상태 : " + loginStatus);
		
		
		// 로그인 여부에 따라서 페이지 이동
		// 성공 - main 페이지
		// 실패 - login페이지
		String resultURL="";
		if(loginStatus) {
			//return "redirect:/member/main";
			resultURL = "redirect:/member/main";
			session.setAttribute("id", vo.getUserid());
		} else {
			//return "redirect:/member/login";
			resultURL = "redirect:/member/login";
		}
		
		return resultURL;
	
	}
	
	
	// http://localhost:8080/member/main
	// 메인페이지 구현 
	@RequestMapping( value = "/main", method=RequestMethod.GET)
	public String mainGET(HttpSession session) throws Exception {
		 // 전달정보 저장
		mylog.info(" mainGET() 호출 "); 
		
		if(session.getAttribute("id") == null ) {
			mylog.debug("아이디 정보 없음");
			return "redirect:/member/login";
		}
		
		// 연결된 뷰페이지 호출
		return "/member/main";
			
	}
	
	
	
	/**
	 * 로그아웃
	 */
	@RequestMapping( value = "/logout", method=RequestMethod.GET)
	public String logoutGET(HttpSession session) throws Exception {
		
		// 세션초기화
		session.invalidate();
		
		// 페이지이동(로그인페이지)
		return "redirect:/member/login";
		
	}
	
	
	/**
	 * 회원정보 조회
	 */
	@RequestMapping( value = "/info", method=RequestMethod.GET)
	public void infoGet(HttpSession session, Model model) throws Exception {
		mylog.debug(" infoGET() 호출");
		
		// 1) 전달정보 받기
		//    없지만 세션정보(즉 id) 필요하다
		String id = (String)session.getAttribute("id");
		
		
		// 2) DB가서 회원정보 가져오기
		MemberVO vo = service.getMember(id); // 를 통해 정보를 가져올거다 
		mylog.debug(" 회원 정보 가져오기 성공" + vo);
		
		// 3) DB정보를 뷰페이지로 전달(request -> Model)
		model.addAttribute("vo", vo);
		
//		model.addAttribute(service.getMember(id));
		
		//    (기존에선 request.setAttribute해서 넘겼는데 여기선 model객체로 넘길것)
		// 4) view 페이지 이동
		
	}
	
	/**
	 * 회원정보 수정
	 * 
	 * 1) 기존의 정보를 화면에 출력
	 * 2) 수정된 정보를 DB에 변경
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String  modifyGET(HttpSession session, Model model) throws Exception {
		mylog.debug(" modifyGET() 호출 "); 
		
		// 기존의 insertFom.jsp 참고해서 수정 뷰페이지 생성
		String id = (String) session.getAttribute("id");
		
		// 서비스통해 회원정보 조회
		// model 객체 사용하여 정보 전달
		model.addAttribute("vo", service.getMember(id)); // 해당정보를 가져와서 바로 모델을 뷰페이지로 넘긴다
		
		// 뷰페이지 이동
		
		return "/member/modify";
	
	// 1) 기존의 정보를 화면에 출력
	
	// 2) 수정된 정보를 DB에 변경 (info의 동작과 거의 유사하다)
	}

	
	@RequestMapping( value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(MemberVO vo) throws Exception {
		// 1) 한글처리 => 필터가 알아서 해줄 것
		// 2) 전달정보(파라미터데이터) 저장 (폼 - POST : 수정정보)
		mylog.debug(vo+"");
		
		// 3) 서비스에서 DB의 회원정보 수정하는 동작을 수행
		Integer result = service.updateMember(vo);
		String uri="";
		
		// 4) 페이지 이동 - 수정여부에 따라 다르게 이동시키기
		if(result == 1) {
			// 4-1) 수정성공 (메인페이지)
			uri="redirect:/member/main";
		} else {
			// 4-2) 수정실패 (수정페이지)
			uri="redirect:/member/modify";
		}
		return uri;
	}
	
	
	/**
	 * 회원정보 삭제
	 * 
	 * 1) 삭제 비밀번호 입력
	 * 2) 데이터삭제
	 */

	// 1) 삭제 비밀번호 입력
	@RequestMapping( value = "/remove", method = RequestMethod.GET)
	public void removeGET() throws Exception {
		mylog.info(" .removeGET() 호출");
		
		// 연결된 뷰로 바로 이동하기
	}
	
	
	
	// 2) 데이터삭제
	//				- 히든으로 받아온 아이디를 저장해야함 (파라미터or모델or멤버vo객체에 생성해서 알아서 가져오게 해도됨)
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String removePOST(MemberVO vo, HttpSession session) throws Exception {
		
		// 전달된 파라미터값 저장
		
		
		// 서비스 - DAO 회원탈퇴라는 동작을 수행하는 메서드 불러오기
		service.deleteMember(vo);
			
		// 세션정보 초기화(로그인정보)
		session.invalidate();
		// 페이지 이동
	
		return "redirect:/member/main";
	}
	
	
	
	/**
	 * 회원 전체 목록 조회
	 */
	@RequestMapping( value = "/list", method = RequestMethod.GET)
	public String listGET(HttpSession session, Model model ) throws Exception {
						// session으로 id값만 가져오기
		// 관리자 로그인 제어 
		String id = (String)session.getAttribute("id");
		if(id == null || !id.equals("admin")) {
			return "redirect:/member/login";
		}
		
		// 서비스 - DAO 회원목록 가져오기
		List<MemberVO> memberList = service.getMemberList(id);
		
		// Model 객체 사용해서 정보 전달하기
		model.addAttribute("memberList", memberList);
		// model.addAttribute(memberList); // 이렇게 햐면안됨 이름 적어줘야함
		
		// 뷰페이지 이동해서 출력
		
		return "/member/list";
	}
}











