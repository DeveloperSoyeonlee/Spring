<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>
</head>
<body>
	<h1>/member/insertForm.jsp</h1>
	
	
	<!-- 아이디 사용 여부 체크 : 키보드 입력시 해당아이디를 사용가능한지 판단(화면에 표시) -->
	<fieldset>
		<legend>회원가입</legend>
	
		<form action="insert" method="POST">
			아이디 : 　<input type="text" name="userid" id="userid">
					   <div id="userIDdiv"></div> <br>
			비밀번호 :  <input type="password" name="userpw"> <br>
			이름 :  　　<input type="text" name="username"> <br>
			이메일 :  　<input type="email" name="useremail"> <br>
		
			<input type="submit" value="회원가입">
		</form>
	</fieldset>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#userid").keyup(function(){
// 				alert("이벤트!");
				// 키보드 입력시마다, 입력된 아이디 정보가 사용가능한지 확인(DB)
				
// 			$("#userIDdiv").append("@");
// 			$("#userIDdiv").text("@"); // 
// 			$("#userIDdiv").html("<h2>@</h2>"); // html: 태그형태로 전달하면 태그가 적용된 형태로 출력가능
// 			$("#userIDdiv").html("<font color='red'> @ </font>"); // html: 태그형태로 전달하면 태그가 적용된 형태로 출력가능
			
			if(5>$("#userid").val().length || $("#userid").val().length > 10 ){
				
				// 문제가 있을 때
				$("#userIDdiv").html("<font color='red'> 정상적인 아이디를 입력하세요 </font>"); 
				
			} else{
				// 문제 없을 때 (5~10자리일 때)
				// 해당 아이디 정보가 디비에 있는지 체크
				//GET 방식 - /members/ckID+데이터
				$.ajax({
					type:"GET",
					url:"/members/ckID",
					data:{userid:$("#userid").val()},
					success:function(data){
						alert("성공");
// 						alert(data); // ok or no 라고 출력됨

						if(data == "OK"){
							$("#userIDdiv").html("<font color='red'> 정상적인 아이디 사용입니다 </font>"); 
							
						}else{
							$("#userIDdiv").html("<font color='blue'> 이미 사용중인 아이디 사용입니다 </font>"); 
							
						}
					}
					
				});
				
				$("#userIDdiv").html("<font color='blue'> 5~10자리 아이디를 입력하세요 </font>"); 
			}
			
			});
		});
	</script>
	
</body>
</html>