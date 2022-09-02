<%@page import="kr.or.iei.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Notice n = (Notice)request.getAttribute("n");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	 #noticeView th, #noticeView td{border:1px solid #eee;}
	 #noticeContent{min-height:300px;text-align:left;font-family:ns-light;}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">공지사항</div>
		<table class="tbl" id="noticeView">
			<tr class="tr-3">
				<th colspan="6"><%=n.getNoticeTitle() %></th>
			</tr>
			<tr class="tr-1">
				<th class="td-3">작성자</th>
				<td><%=n.getNoticeWriter() %></td>
				<th class="td-3">조회수</th>
				<td><%=n.getReadCount() %></td>
				<th class="td-3">작성일</th>
				<td><%=n.getRegDate() %></td>
			</tr>
			
			<tr class="tr-1">
				<th class="td-3">첨부파일</th>
				<td colspan="5">
					<%if(n.getFilename() != null) {%>
					<img src="/img/file.png" width="16px">
					<a href="/noticeFileDown.do?noticeNo=<%=n.getNoticeNo() %>"><%=n.getFilename() %></a>
					<%} %>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div id="noticeContent"><%=n.getNoticeContentBr() %></div>
				</td>
			</tr>
			<%if(m != null && n.getNoticeWriter().equals(m.getMemberId())){ %>
			<tr class="tr-1">
				<th colspan="6">
					<a class="btn bc44" href="#">수정</a>
					<button type="button" class="btn bc44" onclick="noticeDelete(<%=n.getNoticeNo()%>);">삭제</button>
				</th>
			</tr>
			<%} %>
		</table>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>