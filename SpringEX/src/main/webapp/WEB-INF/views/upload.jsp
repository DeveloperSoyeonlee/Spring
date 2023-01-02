<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>

<script type="text/javascript">
	  var cnt=1;
	  function file_add(){
		  $('#d_file').append("<input type='file' name='file"+cnt+"'>");
		  cnt++;  
	  } 

</script>



<body>
    <h1>upload.jsp</h1>
    <form method="post" enctype="multipart/form-data">
       아이디 : <input type="text" name="id"> <hr>
       <input type="button" value="파일업로드 추가" onclick="file_add();">
       
       <div id="d_file">
       
       </div>
       
       <hr>
       <input type="submit" value="전송하기(업로드)">
    
    </form>
    
    

</body>
</html>