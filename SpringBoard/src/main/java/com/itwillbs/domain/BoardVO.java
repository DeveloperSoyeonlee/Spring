package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {

	
	private Integer bno; //계속 가지고다니면서 페이지이동간 형변환이 생기는 아이는 integer(참조형)이 더 안전
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private int viewcnt; //단순카운팅 계산은 인트로계산
}
