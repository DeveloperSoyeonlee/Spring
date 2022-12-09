<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/login.jsp</h1>
	
	<fieldset>
		<legend>로그인</legend>
		<!-- action="/컨트롤러 주소/이동할주소" -->
		<form action="/member/login" method="POST" >
			아이디 　: <input type="text" name="userid"><br>
			비밀번호 : <input type="password" name="userpw"><br>
			
			
			<input type="image" value="로그인" src="https://w7.pngwing.com/pngs/803/213/png-transparent-login-drawing-art-museum-login-button-miscellaneous-building-plan-thumbnail.png"
					width="50">
		</form>	
			<input type="button" value="회원가입" onclick="location.href='/member/insert';">
			
	</fieldset>
	
</body>
</html>