package com.itwillbs.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DateSourceTest {

	/** 
	 * DataSource 객체 가져와서 디비연결 테스트
	 */
	
	
	private static final Logger mylog
			= LoggerFactory.getLogger(DateSourceTest.class);
	
	
	// 객체 주입
	@Inject
	private DataSource ds;
	
	@Test
	public void 디비연결테스트_dataSource() throws Exception {
		mylog.debug(ds+"");
		
		Connection con = ds.getConnection();
		
		mylog.debug(con+"");
	}
	
}
