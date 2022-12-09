<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/modify.jsp</h1>
	
	
	<fieldset>
		<legend>회원정보 수정 </legend>
	
		<form action="/member/modify" method="POST">
			아이디 : 　<input type="text" name="userid" value=${vo.userid } readonly> <br>
			비밀번호 :  <input type="password" name="userpw" placeholder="비밀번호를 입력하세요."> <br> <!-- 정보받아와서 처리하게 할것이므로 비워둠 -->
			이름 :  　　<input type="text" name="username" value=${vo.username }> <br>
			이메일 :  　<input type="email" name="useremail" value=${vo.useremail }> <br>
		
			<input type="submit" value="회원수정">
		</form>
	</fieldset>
	
</body>
</html>