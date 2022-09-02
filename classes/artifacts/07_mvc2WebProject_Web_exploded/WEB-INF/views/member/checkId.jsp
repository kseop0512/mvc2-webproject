<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String memberId = (String)request.getAttribute("memberId");
    boolean result = (boolean)request.getAttribute("result");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/default.css">
<script src="/js/jquery-3.6.0.js"></script>

<style>
	#check-container{
		text-align:center;
		padding-top:50px;
		width:250px;
		margin: 0 auto;
	}
	.id-wrap{
		display:flex
	}

	.id-wrap>input{width:70%}
	.id-wrap>button{width:30%}
</style>
</head>
<body>
	<div id="check-container">
		<%if(result) { %>
		<div>[<span class="fc-4 f-bold"><%=memberId %></span>]는 사용 가능합니다.</div>
		<br><br>
		<button type="button" class="btn bc1" onclick="closeWindow('<%=memberId%>');">닫기</button>
		
		<%}else{ %>
		<div>[<span class="fc-6 f-bold"><%=memberId %></span>]는 이미 사용중입니다.</div>
		<br><br>
		<form action="/checkId.do">
			<div class="id-wrap">
				<input type="text" name="checkId" class="input-form">
				<button type="submit" class="btn bc2">조회</button>
			</div>
		</form>
		<%} %>
	</div>
	<script>
		function closeWindow(memberId) {
			const idInput = $("#memberId", opener.document);
			idInput.val(memberId);
			self.close();
			
		}
	</script>
</body>
</html>