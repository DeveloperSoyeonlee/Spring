package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardService {
	
	
	// 게시판 글쓰기
	public void insertBoard(BoardVO vo) throws Exception ;
	
	
	// 게시판 글 목록(ALL)
	public List<BoardVO> getBoardListAll() throws Exception ;
	
	
	// 글 조회수 1증가
	public void updateViewcnt(Integer bno) throws Exception;
	
	
	// 글번호(bno) 정보 조회 
	public BoardVO getBoard(Integer bno) throws Exception;
	
	
	// 글 정보 수정
	public Integer updateBoard(BoardVO vo) throws Exception;
	
	
	// 글 정보 삭제
	public void deleteBoard(Integer bno) throws Exception;
	
	
	// 글 정보 조회 
	public List<BoardVO> getListPage(Criteria cri) throws Exception;
	
	
	
	
}
