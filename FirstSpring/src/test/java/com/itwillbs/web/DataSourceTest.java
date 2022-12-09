package com.itwillbs.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
// => 이파일을 실행할때 스프링Junit으로 실행하겠다.(스프링테스트 동작)
//@ContextConfiguration(
//		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }
//		)
// => 이파일을 실행할때 필요한 설정파일을 등록


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }
		)
public class DataSourceTest {

	// 디비연결정보(DataSource) 필요함 -> 의존관계
	// BeanFactory factory = new XmlBeanFactory(root-context.xml);
	
	//@Inject : 의존성 주입(DI)을 수행 가능하다.
	
	// root-context.xml 파일에 DriverManagerDataSource타입의 객체 dataSource
	//private DataSource ds2 = new DriverManagerDataSource();
	
	//	@Inject
	//	private DriverManagerDataSource ds1;
	
	//@Inject
	@Autowired
	private DataSource ds;
	
	@Test
	public void DataSource테스트() {
		//System.out.println(" 실행! ");
		//디비연결 테스트
		System.out.println(ds);
		
		try {
			Connection con = ds.getConnection();
			System.out.println(" 디비 연결 성공! ");
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
