<%@page import="kr.or.iei.notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.notice.model.vo.NoticePageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	    ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
	    String pageNavi = (String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.notice-tbl a:hover{text-decoration:underline;}
	.notice-tbl tr{border-bottom:1px solid #ccc;}
	.notice-tbl tr>td:nth-child(2){text-align:left;}
	#pageNavi{margin:30px;}
	
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="page-title">공지사항</div>
		<%if(m!=null && m.getMemberLevel() == 1){ %>
		<a class="btn bc4 writeBtn" href="/noticeWriteFrm.do">글쓰기</a>
		<%} %>
		<table class="tbl tbl-hover notice-tbl">
			<tr class="tr-2">
				<th style="width:10%">번호</th>
				<th style="width:45%">제목</th>
				<th style="width:15%">작성자</th>
				<th style="width:20%">작성일</th>
				<th style="width:10%">조회수</th>
			</tr>
			<%for(Notice n : list) {%>
			<tr class="tr-1">
				<td><%=n.getNoticeNo() %></td>
				<td>
					<a href="/noticeView.do?noticeNo=<%=n.getNoticeNo()%>"><%=n.getNoticeTitle() %></a>
				</td>
				<td><%=n.getNoticeWriter() %></td>
				<td><%=n.getRegDate()%></td>
				<td><%=n.getReadCount() %>
			</tr>
			<%} %>
		</table>
		<div id="pageNavi"><%=pageNavi %></div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>