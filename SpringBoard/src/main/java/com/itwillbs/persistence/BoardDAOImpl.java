package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	
	
	private static final Logger mylog = LoggerFactory.getLogger(BoardDAOImpl.class);
	// 디비연결 -> 직접생성하지않고 bean주입할거임 (root-context.xml으로부터)
	//			-> 가져올 수 있는 이유 : @Repository를 통해 얘를 하나의 객체로 인식하므로 (빈그래프에서 확인할 수 ㅇ)
	@Inject
	private SqlSession sqlSession; // 얘가 있어야 연결~해제 다 가능
	
	// mapper NAMESPACE 정보
	private static final String NAMESPACE
				="com.itwillbs.mapper.BoardMapper";
	
	
	@Override
	public String getTime() {
		
		return sqlSession.selectOne(NAMESPACE + ".getTime");
		
	}


	@Override
	public void creatBoard(BoardVO vo) throws Exception {

		mylog.debug(" creatBoard(BoardVO vo) -> mapper 동작 호출");
		sqlSession.insert(NAMESPACE + ".create", vo);
		
		mylog.debug(" 회원가입 완료 ! -> 다시 서비스에게 이 소식을 알려주자");
	}


	
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		mylog.debug(" getBoardListAll() -> sqlSession-mapper 호출");
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE + ".listAll");
		mylog.debug(boardList+""); // 이걸 이제 안적기로 하자.... 이거는 목록이 다나오는 거잖아.
		mylog.debug(" 게시판 글 개수 : " + boardList.size()+""); // => 이게 보통 선생님이 적는 방식
										  // 리스트 전체가 아니라 출력되는지만 확인하면 되는거므로
		
		return boardList;
	}


	
	@Override
	public void updateViewcnt(Integer bno) throws Exception {
		mylog.debug(" updateViewcnt(Integer bno) -> sqlSession객체통해 정보처리");
		
		sqlSession.update(NAMESPACE + ".updateViewcnt", bno);
	}


	
	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		mylog.debug(" getBoard(Integer bno) -> sqlSession-mapper 호출 ");
		
		BoardVO vo = sqlSession.selectOne(NAMESPACE + ".getBoard", bno);
		return vo ;
	}


	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		mylog.debug(" updateBoard(BoardVO vo" );
		return sqlSession.update(NAMESPACE+".updateBoard", vo);
		
	}


	@Override
	public void deleteBoard(Integer bno) throws Exception {
		mylog.debug(" deleteBoard(BoardVO vo" );
		sqlSession.delete(NAMESPACE+".deleteBoard",bno);
	}


	
	@Override
	public List<BoardVO> getListPage(Integer page) throws Exception {
		
		mylog.debug(" getListPage(Integer page) 페이징처리" );
		// 페이지 정보 계산
		if(page < 0) {
			page = 1;
		}
		
		// 디비에서 10개씩 조회
		// 페이지 1 일때 시작번호 0 / 2 - 10 / 3 - 20 / 4 - 30 / 5 - 40
		page = (page - 1) * 10;
		
		
		return sqlSession.selectList(NAMESPACE+".listPage",page);
	}


	@Override
	public List<BoardVO> getListPage(Criteria cri) throws Exception {
		mylog.debug(" getListPage(Criteria cri) 페이징처리" );
		return sqlSession.selectList(NAMESPACE+".listPage2", cri);
	}


	
	@Override
	public int totalCnt() throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countBoard");
	}


	


	
	
	
	
	
	

}
