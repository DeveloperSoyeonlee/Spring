package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class MemberVO {
	// VO(Value Object) ( 데이터를 읽을 수만 있게 만들어 쓰는 객체 readonly 느낌이 난다.)
	// 					( DTO는 가져다가 수정도하고 로직에 사용할 수 있게 바꿀 수 있는 아이 )
	
	  /* VO(Value Object) 
    - DTO의 역할과 유사하지만 다른 개념!
    - VO는 특정 데이터를 저장해서 읽을 수만 있게 만들어 줌!, DTO는 전달하는 게 주 목적!
    */
	
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	
	private Timestamp regdate;
	private Timestamp updatedate;
	
}
