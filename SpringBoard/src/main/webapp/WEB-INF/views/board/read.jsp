<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 헤더 -->
<%@ include file="../include/header.jsp"%>

<h1>/board/regist.jsp</h1>
<form role="form" method="post"> <!-- 부트스트랩 제공 폼태그 -->
<!-- 수정과 삭제는 bno정보를 전달하는 폼태그 -->
<!-- 수정과 삭제는 bno가 필요하다 -->
	<input type="hidden" name="bno" value="${vo.bno}">
</form>

<%-- ${vo } --%>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">게시판 본문내용</h3>
	</div>
	<!--  /.box-header -->
	<!--  -->
<!-- 	<form role="form" action="" method="post"> -->
		<div class="box-body">
			<div class="form-group">
				<label for="aaa">글 번 호</label>
				<!-- name을 넘겨줘야함. 컬럼값과 같아야함 -->
				<input type="text" name="title" class="form-control" id="aaa"
					value="${vo.bno}" readonly>
			</div>
			<div class="form-group">
				<label for="aaa">제목</label>
				<input type="text" name="title" class="form-control" id="aaa"
					value="${vo.title}" readonly>
					
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">작성자</label> <input type="text"
					name="writer" class="form-control" id="exampleInputPassword1"
					value="${vo.writer}" readonly>
			</div>

			<div class="form-group">
				<label>내 용 </label>
				<textarea name="content" class="form-control" rows="3"
					placeholder="내용을 입력하세요." readonly>${vo.content}</textarea>
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-danger">수정</button>
			<button type="submit" class="btn btn-warning">삭제</button>
			<button type="submit" class="btn bg-purple">목록</button>
		</div>
		
		
		<!-- jQuery 사용한 페이지 이동 -->

		<script>
			$(document).ready(function(){
				var formObj = $("form[role='form']");
				console.log(formObj);
				
				
				// 수정버튼 (bno를 가지고 submit-> /board/modify 페이지로 이동)
				$(".btn-danger").click(function(){
					formObj.attr("action","/board/modify");
					formObj.attr("method","get");
					formObj.submit();
					
				});
				
				// 삭제버튼 (bnbo 가지고 submit -> /board/remove 페이지로 이동)
				$(".btn-warning").click(function(){
					// 바로 삭제동작 -> POST
					formObj.attr("action","/board/remove");
					formObj.submit();
				});
				
				
				// 목록버튼
				$(".bg-purple").click(function(){
					// 목록으로 이동
					location.href="/board/list";
				});
				
				
			});
		</script>

<!-- 	</form> -->
</div>

<!-- 푸터 -->
<%@ include file="../include/footer.jsp"%>
