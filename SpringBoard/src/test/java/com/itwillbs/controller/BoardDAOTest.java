package com.itwillbs.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class BoardDAOTest {
	
	// DAO 객체에 있는 디비 호출 메서드 실행
	
	
	private static final Logger mylog = LoggerFactory.getLogger(BoardDAOTest.class);
	
	// BoardDAO 객체 
	@Inject
	private BoardDAO dao;
	
	
//	@Test
	public void 디비연결시간정보() {
		mylog.debug(dao.getTime());
	}
	
	
//	@Test
	public void 페이징처리_게시판리스트() {
		try {
			for(BoardVO vo:dao.getListPage(1)) {
				 mylog.debug(vo.getBno()+" : "+vo.getTitle());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void cri테스트() {
		// 임시페이지처리 객체
		Criteria cri = new Criteria(); //1페이지 10개씩
//		cri.setPage(2); //만약 다른페이지를 보고싶다면 이렇게(2페이지에 10개씩 보겠다)
		cri.setPerPageNum(20); //  갯수도 조절 가능
		
		try {
			for(BoardVO vo : dao.getListPage(cri)) {
				mylog.debug(vo.getBno()+" : "+vo.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void pageVO_테스트() {
		
		Criteria cri = new Criteria(); //1페이지, 10개씩 출력
		
		// 정보 가져오면서 하단부 작업 같이
		
		PageVO vo = new PageVO();
		vo.setCri(cri);
//		vo.setTotalCount(totalCount);
		vo.setTotalCount(100);
		
		try {
//			dao.getListPage(cri);
		} catch (Exception e) {
			e.printStackTrace();
		} //
		
	}
	
	
	
	
	
	
	
}
