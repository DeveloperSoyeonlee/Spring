<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>/member/info.jsp</h1>
		<!-- 비밀번호 제외 회원정보 출력 -->
		
		<h2>회원정보</h2>
		
		<h3> 아이디 : ${vo.userid }</h3>
		<h3> 이름 : ${vo.userpw }</h3>
		<h3> 이메일 : ${vo.useremail }</h3>
		<h3> 회원가입일 : ${vo.regdate }</h3>
		
		
		<a href="member/main"> 메인페이지로 ... </a>
		
</body>
</html>