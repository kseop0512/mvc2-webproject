<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<style>
	#noticeWrite td, #noticeWrite th{border:1px solid #ccc;}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">공지사항 작성</div>
		<form action="/noticeWrite.do" method="post" enctype="multipart/form-data"><%-- 첨부파일이 있을때 method는 무조건 post, enctype="multipart/form-data" 필수 --%>
			<table class="tbl" id="noticeWrite">
				<tr class="tr-1">
					<th class="td-3">제목</th>
					<td colspan="3">
						<input type="text" name="noticeTitle" class="input-form" required>
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">작성자</th>
					<td class=""><%=m.getMemberId() %><input type="hidden" name="noticeWriter" value="<%=m.getMemberId() %>"></td>
					<th class="td-3">첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				<tr class="tr-1">
					<td colspan="4">
						<textarea name="noticeContent" class="input-form" required></textarea>
					</td>
				</tr>
				<tr class="tr-1">
					<td colspan="4">
						<button type="submit" class="btn bc1 bs4">공지사항 작성</button>
					</td>
				</tr>
			</table>
				
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>