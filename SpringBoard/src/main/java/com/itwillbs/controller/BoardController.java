package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	
	private static final Logger mylog = LoggerFactory.getLogger(BoardController.class);

	
	// 서비스객체 주입
	@Inject
	private BoardService service;
	
	
	
	/**
	 *  게시판 글쓰기 
	 */
	// http://localhost:8080/board/regist
	
	// 게시판 글쓰기 GET : 입력할 수 있는 페이지 뷰
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void registGET() throws Exception {
		mylog.debug("/board/regist(GET) 호출 -> 페이지 이동 ");//void 리턴하기때문에 페이지 이동할 수 있도록 만들어 주는 것
	}
	
	
	
	// 게시판 글쓰기 POST  
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		mylog.debug("/board/regist(POST) 호출 -> 페이지 이동 ");
		mylog.debug(" GET방식의 데이터를 전달 -> DB 저장 -> 페이지이동");
		
		
		// 0. 한글처리(필터에서 이미 처리함 )(폼태그를 submit 으로 보내므로 깨진다)
		
		// 1. 전달된 정보 저장 (title, content, writer)
		// 객체 만들기 
		mylog.debug(vo.toString());
		
		
		// 2. 서비스통해서 -> DAO 접근(mapper통해 동작실행) -> 처리
		service.insertBoard(vo);

		mylog.debug(" 게시판 글쓰기 완료");
		
		// 3. 원하는 페이지로 이동(list페이지)
		
//		model.addAttribute("result", "createOK");
		rttr.addFlashAttribute("result", "createOK");
		
		
		return "redirect:/board/list";
//		return "list"; //둘 사이의 주소 불일치가 발생한다
		
	}

	
	
	/**
	 * 목록 부르기 list (페이징 처리X)
	 */
	//http://localhost:8080/board/list?result=createOK
	//http://localhost:8080/board/list
	
	
	@RequestMapping (value="/list", method=RequestMethod.GET)
	public void listGET(HttpSession session, Model model, @ModelAttribute("result") String result) throws Exception {
		
		//1. 전달받는 정보는 없음X
		mylog.debug(" 전달정보 : " + result);
		
		// 세션객체 - 글 조회수 체크 정보
		session.setAttribute("updateCheck", true);
		
		//2. 서비스 -> DAO 게시판 리스트 가져오기
		List<BoardVO> boardList = service.getBoardListAll();
		
		
		//3. 연결되어 있는 뷰페이지로 정보 전달 (Model 객체 생성)
		model.addAttribute("boardList", boardList);
		
		
		//4. 페이지 이동(/board/list.jsp)
		// 이동할 필요 없다. 메서드 리턴타입이 void 기 때문에 자동으로 해당이름에 해당하는 jsp뷰를 찾게되어있다
		
	}
	
	
	/**
	 * 페이징 처리 후 list
	 */
	
	//http://localhost:8080/board/listPage
	//http://localhost:8080/board/listPage?page=2
	//http://localhost:8080/board/listPage?perPageNum=30
	//http://localhost:8080/board/listPage?page=2&perPageNum=20
	@RequestMapping (value="/listPage", method=RequestMethod.GET)
	public String listPageGET(Criteria cri, HttpSession session, Model model) throws Exception {
		
		//1. 전달받는 정보는 없음X
//		mylog.debug(" 전달정보 : " + result);
		
		// 세션객체 - 글 조회수 체크 정보
		session.setAttribute("updateCheck", true);
		
		// 페이징처리 객체 (직접생성 = 강한결합 -> 스프링에서는 No .스프링프레임워크에 맞춰서 바꿔야함)
//		Criteria cri = new Criteria();
		
		//2. 서비스 -> DAO 게시판 리스트 가져오기
		List<BoardVO> boardList = service.getListPage(cri);
		
		// 페이징처리 하단부 정보 준비 -> 뷰페이지로 전달
		PageVO pvo = new PageVO();
		pvo.setCri(cri);
//		pvo.setTotalCount(769);
		mylog.debug("totalCnt : "+service.totalCnt());
		pvo.setTotalCount(service.totalCnt()); // 현재 작성되어있는 글전체 개수 가져오기
		
		// model객체로 넘기기
		model.addAttribute("pvo", pvo);
		
		
		//3. 연결되어 있는 뷰페이지로 정보 전달 (Model 객체 생성)
		model.addAttribute("boardList", boardList);
		
		//4. 페이지 이동(/board/list.jsp)
		return "/board/list";
		
	}


	
	/**
	 * 게시판 본문 보기
	 */
	//http://localhost:8080/board/read?bno=1
	
	// @ModelAttribute : request.getParameter() 동작 - > Model 객체에 저장 (1:n관계)
	// @RequestParam   : request.getParameter() 동작 수행 (1:1 관계)
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readGET(Model model, @RequestParam("bno") int bno, HttpSession session) throws Exception{
//	public void readGET(@ModelAttribute("bno") int bno) throws Exception{
//	public void readGET(int bno) throws Exception{
		
		//1. 전달정보 (bno)
		mylog.debug("전달정보 (bno) : " + bno);
		
		
		//2. 조회수 1증가
		//			- list에서 글을 눌렀을 때에만 read 증가
		//			- 새로고침했을 때에는 증가X
		
		// 세션객체 사용 
		boolean isUpdateCheck =(boolean)session.getAttribute("updateCheck");
		mylog.debug(" 조회수 증가 상태 : " + isUpdateCheck);
		if(isUpdateCheck) { //true
			// 서비스 -> DAO 동작 호출
			//	조회수 1증가 list에서 글을 눌렀을 때에만 read 증가
			service.updateViewcnt(bno);
			mylog.debug(" 조회수 1증가 ");
			// 상태변경(true-> false)
			session.setAttribute("updateCheck", !isUpdateCheck); //기존의 상태의 반대(false라고적어도 상관은없으나)
			
		}
		
		//3. 서비스통해 -> DAO(디비)에서 특정 글번호에 해당하는 정보 가져오기
		BoardVO vo = service.getBoard(bno);
		
		//4. 연결된 뷰 페이지로 정보 전달(model)
		model.addAttribute("vo",vo);

//		model.addAttribute("vo",service.getBoard(bno)); // 간결하게 적기위해 이렇게 코드 적으면된다
//		model.addAttribute(service.getBoard(bno)); // 간결하게 적기위해 이렇게 코드 적으면된다
		
		
			
		}
	/**
	 * 수정
	 */
	
	// 수정GET
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(BoardVO vo,int bno, Model model) throws Exception {
//	public void modifyGET(Model model, @RequestParam("bno") int bno) throws Exception {
		
		
		// 1. 파라미터 저장(bno)
		mylog.debug(" 전달정보(bno) 저장 : " + bno); 
		
		// 2. 서비스 - DAO(글 조회)
		// 3. model 객체 사용 -> 원하는 페이지로 이동
		model.addAttribute("vo", service.getBoard(bno));
		// 4. /board/modify.jsp 페이지로 이동
		
	}
	
	
	// 수정POST
	@RequestMapping (value ="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		// 1. 전달된 정보(수정할 정보) 저장
		mylog.debug(vo+"");
		// 2. 서비스 - DAO : 정보 수정 메서드 호출
		Integer result = service.updateBoard(vo);
		
		if(result > 0) {
			// 3. "수정완료"라는 메세지정보 전달
			rttr.addAttribute("result", "modOK");
		}
		
		// 4. 페이지 이동 (/board/list)
		
		
		return "redirect:/board/list";
	}
	
	
	
	/**
	 * 글 삭제
	 */
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePOST(int bno, RedirectAttributes rttr) throws Exception {
		
		// 1. 전달정보 저장(bno)
		mylog.debug("전달정보 : " + bno + "");
		// 2. 서비스-DAO 연결해서 : 게시판글 삭제 메서드 호출
		service.deleteBoard(bno);
		
		
		// 3. "삭제완료" 정보를 list 페이지로 전달
		rttr.addAttribute("result","delOK");
		// 4. 게시판 리스트로 이동(/board/list)
		
		return "redirect:/board/list";
	}
	
	
	
	/**
	 * 
	 */
	
	
}














