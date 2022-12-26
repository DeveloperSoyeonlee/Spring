package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger mylog = LoggerFactory.getLogger(BoardServiceImpl.class);
	
//	@Autowired
	@Inject
	private BoardDAO dao; 
	
	
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		mylog.debug(" insertBoard(BoardVO vo) 호출 -> DAO 동작 호출");
		
		dao.creatBoard(vo); // 이걸 불러야 이거 관련 행위를 할 수 잇따
		
		mylog.debug(" 글쓰기 완료 -> 컨트롤러 이동");
		
	}

	
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		mylog.debug(" getBoardListAll() 호출 -> DAO 동작 호출");
		
		return dao.getBoardListAll();
	}

	
	@Override
	public void updateViewcnt(Integer bno) throws Exception {
		mylog.debug(" updateViewcnt(Integer bno) 호출 -> DAO 호출( 조회수 1증가)");
		dao.updateViewcnt(bno);
		
	}


	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		mylog.debug(" getBoard(Integer bno) 호출");
		return dao.getBoard(bno);
	}


	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		mylog.debug("  updateBoard(BoardVO vo) 호출");
		return dao.updateBoard(vo);
	}


	@Override
	public void deleteBoard(Integer bno) throws Exception {
		dao.deleteBoard(bno);
	}


	@Override
	public List<BoardVO> getListPage(Criteria cri) throws Exception {
		return dao.getListPage(cri);
	}


	@Override
	public int totalCnt() throws Exception {
		return dao.totalCnt();
	}
	
	
	








	
	

	


}
