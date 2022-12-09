package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MysqlConnectTest {
	
	// 디비연결정보
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/springdb";
	private static final String ID="root";
	private static final String PW="1234";
	
	
	//디비연결 테스트
	//@Test
	public void 디비연결테스트() {
		System.out.println(" 디비연결테스트 실행! ");
		try {
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			System.out.println(" 드라이버 로드 성공! ");
			// 2. 디비연결
			Connection con = DriverManager.getConnection(URL, ID, PW);
			System.out.println(" 디비 연결 성공! ");
			System.out.println(con);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void 디비연결_테스트2() {
		System.out.println(" 디비연결테스트 실행! ");
		
		
		// try~ with구문(JDK1.7이후 사용가능)
		// finally 영역없이 자동으로 자원해제하는 구문,
		// 선언된 객체가 AutoClosable 인터페이스를 구현한 경우에만 사용가능하다
		
		try(Connection con = DriverManager.getConnection(URL, ID, PW);) {
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			System.out.println(" 드라이버 로드 성공! ");
			// 2. 디비연결
			System.out.println(" 디비 연결 성공! ");
			System.out.println(con);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
