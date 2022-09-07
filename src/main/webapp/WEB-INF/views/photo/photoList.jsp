<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int totalCount = (Integer)request.getAttribute("totalCount");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">사진게시판 <%=totalCount %></div>
		<%if(m!=null)  {%>
		
		<a href="/photoWriteFrm.do" class="btn bc4">글쓰기</a>
		<%} %>
		<div class="photoWrapper posting-wrap"></div>
		<button class="btn bc44 bs4" id="more-btn" totalCount="<%=totalCount%>" currentCount="0" value="1">더보기</button>
		<%--
			totalCount : 전체 게시물 수
			currentCount : 실제 jsp에 가지고온 게시물 수
			value : 더보기요청시 다음 게시물 시작 번호
		--%>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp" %>

	<script>
		$("#more-btn").on("click", function() {
			const amount = 5;//더보기 클릭 시 추가로 가져올 게시물
			const start = $(this).val();
			$.ajax({
				url: "/photoMore.do",
				type : "post",
				data : {start:start, amount:amount},
				success : function(data) {
					console.log(data);
					const imgEl = $("<img>");
					
					imgEl.attr("src", "/upload/photo/" + data[0].filepath);
					
					$(".photoWrapper").append(imgEl);
				},
				error : function() {
					
				}
			})
		})
		
	</script>
</body>
</html>