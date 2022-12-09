<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>itwillMember.jsp</h1>
	
	${vo } <hr>
	
	아이디 : ${vo.userid }<br>
	비밀번호 : ${vo.userpw }<br>
	이름 : ${vo.username }<br>
	이메일 : ${vo.useremail }<br>
	
	<hr><hr>
	${MemberVO } <!-- 대문자는 안됨 -->
	${memberVO } <!-- 소문자로 바꿔줘야함 -->
	
	<hr>
	${userid }
</body>
</html>