<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/remove.jsp</h1>
	
	<fieldset>
		<legend>회원 삭제</legend>
		<!-- action="/컨트롤러 주소/이동할주소" -->
		<form action="/member/remove" method="POST" >
			<input type="hidden" name="userid" value-"${id }"><br>
			비밀번호 : <input type="password" name="userpw"><br>
			<input type="submit" value="회원탈퇴">
	</fieldset>
	
</body>
</html>