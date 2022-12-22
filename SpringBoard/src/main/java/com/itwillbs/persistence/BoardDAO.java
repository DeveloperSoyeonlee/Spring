package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {

	
	// 서버시간정보 조회
	public String getTime();
	
	
	// 게시판 글쓰기
	public void creatBoard(BoardVO vo) throws Exception;
	
	
	// 게시판 목록(ALL)
	public List<BoardVO> getBoardListAll() throws Exception;
	
	
	// 글 조회수 1증가
	public void updateViewcnt(Integer bno) throws Exception;
	
	
	// 글번호(bno)를 사용한 정보 조회
	public BoardVO getBoard(Integer bno) throws Exception;
	
	
	// 글 정보 수정하기
	/**
	 * 글정보 수정하는 메서드
	 * @param vo
	 * @return 수정여부 0,1
	 * @throws Exception
	 */
	public Integer updateBoard(BoardVO vo) throws Exception;
	
	
	// 글 정보 삭제하기
	public void deleteBoard(Integer bno) throws Exception;
	
	
	// 페이징처리 구현된 리스트 조회 (리스트 리턴)
	public List<BoardVO> getListPage(Integer page) throws Exception;
	
	public List<BoardVO> getListPage(Criteria cri) throws Exception;

	
	
	
}
