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
		<h1>result.jsp</h1>
		${map }
		
		<h2> 아이디 </h2> <!-- map이라는 형태로 전달했으니  -->
			<h3>${map.id}</h3>		
		<h2> 첨부파일 </h2>
		
		<c:forEach var="fileName" items="${map.fileList }">
			<h3><a href="/download?fileName=${fileName }">${fileName }</a></h3>
		</c:forEach>
		
		
</body>
</html>