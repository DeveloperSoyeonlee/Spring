<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/main.jsp</h1>
	
	${sessionScope.id }님 환영합니다<br>
	${id }님 환영합니다<br>
	
	<input type="button" value="로그아웃" onclick="location.href='/member/logout';">
	
	
	<h3><a href="/member/info">회원 정보 조회(select)</a></h3>

	<h3><a href="/member/modify">회원 정보 수정(update)</a></h3>
	
	<h3><a href="/member/remove">회원 정보 삭제(delete)</a></h3>
	
	
<%--<c:if test="${id == 'admin' }"> <!-- id : 로그인했을때 세션에 저장한 아이디 --> --%>
	<c:if test="${id.equals('admin') }"> <!-- == 이나 equals 중 아무거나 사용하면 된다 -->
	<h3><a href="/member/list">회원 목록 조회(List)</a></h3>
	</c:if>
	
	
	
</body>
</html>