package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 스프링에서 해당파일을 DAO파일로 인식시키는 용도

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	
	
	private static final Logger mylog = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// 디비 연결정보 필요함 => 의존관계 주입
	
	
	
	@Inject
	private SqlSession sqlSession;
	
	//mapper의 namespace정보 저장
	private static final String NAMESPACE="com.itwillbs.mapper.MemberMapper";
	

	@Override
	public String getServerTime() {
		// 디비연결 
		// sql 작성 & pstmt // xml 파일에서 만들고 왔기때문에 여기서 쿼리작성안해도됨
		// ??
		// sql 실행
		// 데이터처리
		String time
		= sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		return time;
	}
	@Inject
	private MemberDAO dao;

	@Override
	public void insertMember(MemberVO vo) {
//		System.out.println("DAO : "+vo);
		mylog.info(" 서비스 -> DAO -> mapper");
		// 디비연결 - sql작성 - 실행
		// 디비연결은 이미 객체생성되어있어서 실행만하면됨 
		// sql는 mapper에서 할 것임
		
		sqlSession.insert(NAMESPACE + ".createMember", vo);
//		System.out.println(" DAOImpl : 회원가입 성공! ");
		mylog.info(" mapper -> DAO -> 서비스 ");
		
	}


	@Override
	public MemberVO getMember(String userid) {
		
		MemberVO vo = sqlSession.selectOne(NAMESPACE + ".getMember", userid);
		
		System.out.println(" DAO : " + vo + " 실행이 되는지 체크하는 용도");
		return vo;
	}


	@Override
	public MemberVO loginMember(String userid, String userpw) {
//		sqlSession.selectOne(statement,userid,user); (x)
//방법1	sqlSession.selectOne(statement,vo); (o) vo객체 생성해서 set호출해서 저장가능
		
//방법2 VO 객체 안에 전달된 정보를 한번에 전달 불가능한 경우!!?
		// -> 관련없는 데이터를 1개 이상 전달하는 경우(like join)
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		//paramMap.put("mapper에 매핑될 이름", userid);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		MemberVO vo 
			= sqlSession.selectOne(NAMESPACE + ".loginMember", paramMap); // 파람맵이라는 맵의 객체를 통째로 준다.
											// 관련없는 애들을 보낼때만 사용하기 좋다. 관련잇을때는 그냥 vo로 담아서보내는게 최고임
		
		return vo;
	}


	@Override
	public MemberVO loginMember(MemberVO vo) {
		
		return sqlSession.selectOne(NAMESPACE + ".loginMember",vo);
	}


	@Override
	public Integer updateMember(MemberVO uvo) {
		mylog.info(" updateMember() 호출");
		
		//mapper 호출 -> DB실행
		
		
		return sqlSession.update(NAMESPACE + ".updateMember", uvo); //namespace라는 매퍼주소에서 update멤버라는 메서드 불러오기 ~
		
		// update를 해서 그 실행결과를 리턴해주겠다 = 몇개를 업데이트 했는지 보여주겠다. = 0아니면 1일것
		
	}


	@Override
	public void deleteMemeber(MemberVO dvo) {
		mylog.info(" deleteMemeber() 호출");
		
		sqlSession.delete(NAMESPACE+".deleteMember", dvo);
		// 리턴할게 없으므로 단순히 실행만해서 동작할 수 있게 함
	}


	@Override
	public List<MemberVO> getMemberList(String id) {
		
		// mapper 에서 VO형태로 리턴된 데이터를 지동으로 List형태로 변경시켜주는 메서드
//		return sqlSession.selectList(NAMESPACE + ".getMemberList", id);
		return sqlSession.selectList(NAMESPACE + ".getMemberList", id);
		
	}
	
	
	
	
	
	
	
	
	

}
