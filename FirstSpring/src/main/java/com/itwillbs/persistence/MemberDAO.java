package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {

	
	// 디비 실행해서 동작해보고싶다고 
	// 디비서버의 시간정보 조회
	public String getServerTime();
	
	
	// 회원가입
	public void insertMember(MemberVO vo); //멤버 vo타입의 객체를 가져다가 수행할 것임
		// 디비연결 - sql 작성 - 실행
	
	// 특정 회원정보 조회
	public MemberVO getMember(String userid);
	
	// 로그인처리 
	public MemberVO loginMember(String userid, String userpw);
	public MemberVO loginMember(MemberVO vo);
	
	// 회원정보 수정
	/*  - 보통 int로 할건데 스프링에서는 웬만하면 기본형안씀.
	 	- 왜냐 데이터안정성에 따른 문제때문에
	      6버전 이전의 아이들은 오토박싱을 안해주기때문에, 
	      기본형타임보다는 참조형으로 준비해주는게 더 좋다. (integer)
	 */
	public Integer updateMember (MemberVO uvo);
	
	
	
	// 회원정보 삭제
	public void deleteMemeber (MemberVO dvo); // 이 안에는 id,pw만 있으면됨
	
	
	// 회원목록 조회
	public List<MemberVO> getMemberList(String id);
		
	
	
	
}
