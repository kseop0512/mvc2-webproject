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
					for(let i=0; i<data.length; i++) {
						const p = data[i];
						const div = $("<div></div>");
						div.addClass("posting-item");
						const imgDiv = $("<div></div>");
						imgDiv.addClass("posting-img");
						const img = $("<img>");
						img.attr("src", "/upload/photo/" + p.filepath);
						imgDiv.append(img);
						
						const contentDiv = $("<div></div>");
						contentDiv.addClass("posting-content");
						const title = $("<p></p>");
						const content = $("<p></p>");
						title.text(p.photoTitle);
						content.text(p.photoContent);
						contentDiv.append(title);
						contentDiv.append(content);
						
						div.append(imgDiv);
						div.append(contentDiv);
						
						$(".photoWrapper").append(div);
												
					}
					//화면추가완료 후 다음 더보기를 위한 값 수정
					//value 증가 -> 기존 value + amount
					 $("#more-btn").val(Number(start)+Number(amount));
					//currentCount 값도 읽어온 만큼으로 수정
					const currentCount = Number($("#more-btn").attr("currentCount"))+data.length;
					$("#more-btn").attr("currentCount",currentCount);
					
					const totalCount = $("#more-btn").attr("totalCount");
					if(currentCount == totalCount) {
						$("#more-btn").hide();
						//$("#more-btn").prop("disabled", true);
					}
				},
				error : function() {
					
				}
			});
		});
		$("#more-btn").click();
		
	</script>
</body>
</html>