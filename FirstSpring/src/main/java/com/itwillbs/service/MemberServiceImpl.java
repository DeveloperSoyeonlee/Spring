package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링이 해당 파일을 서비스 객체(bean)로 인식

@Service
public class MemberServiceImpl implements MemberService {
	// 컨트롤러 - 서비스 - DAO 사이의 연결하는 것이 주된 목적
	// 수행해야하는 동작 구현
	private static final Logger mylog = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// DAO 객체 필요함
	@Inject // root-context.xml에서 받아올 수 있음
	private MemberDAO dao;

	@Override
	public void memberJoin(MemberVO vo) {
		mylog.info(" 컨트롤러 호출 -> 서비스 -> DAO 호출");
		dao.insertMember(vo);
		
	}

	@Override
	public boolean memberLogin(MemberVO vo) {
		mylog.debug(" memberLogin(vo) 호출 ");
		
		MemberVO resultVO = dao.loginMember(vo);
		
		mylog.debug(" DAO 처리 결과 : " + resultVO);
		// null 	- false
		// not null - true
		
//		if(resultVO != null)
//			return true;
//		else 
//			return false;
		
		
		return resultVO !=null? true:false;
		
	}

	@Override
	public MemberVO getMember(String id) {
		mylog.debug(" getMember(id) 호출 ");
		MemberVO vo = dao.getMember(id);		
		return vo;
	}

	
	
	@Override
	public Integer updateMember(MemberVO uvo) {
		mylog.debug(" updateMember(uvo) 호출 ");
		return dao.updateMember(uvo);
	}

	
	@Override
	public void deleteMember(MemberVO dvo) {
		mylog.debug(" deleteMember(dvo) 호출 ");
		dao.deleteMemeber(dvo);
	}

	
	@Override
	public List<MemberVO> getMemberList(String id) {
		return dao.getMemberList(id);
		
	}

	
	
	
	
	
}
