<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>itwill.jsp</h1>
	
	JSP(param) : <%=request.getParameter("msg") %><hr>
	JSP(attribute) : <%=request.getAttribute("msg") %><hr>
	
	EL : ${msg }<hr>
	EL(r) : ${requestScope.msg }<hr>
	EL(s) : ${sessionScope.msg }<hr>
	EL(p) : ${pageScope.msg }<hr>
	EL(a) : ${applicationScope.msg }<hr>
	EL : ${param.msg }<hr>
	
	<hr>
	${msg }<br>
	${tel }<br>
	
	
	
</body>
</html>