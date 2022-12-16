package com.itwillbs.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	@Test
	public void 디비연결시간정보() {
		mylog.debug(dao.getTime());
	}
}
