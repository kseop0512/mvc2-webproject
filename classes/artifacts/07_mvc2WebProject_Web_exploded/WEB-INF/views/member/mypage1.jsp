<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.input-wrap{
		padding:15px;
	}
	.input-wrap>label{
		font-size:1.1em;
		margin-bottom:10px;
		display:block;
	}
	[name=updateFrm] .btn-box{
		text-align:center;
		margin-top:30px;
	}
</style>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">마이페이지</div>
		<form action="/updateMember.do" method="post" name="updateFrm">
			<div class="input-wrap">
				<label for="memberId">아이디</label>
				<input type="text" name="memberId" id="memberId" class="input-form" value="<%=m.getMemberId() %>" readonly> 
			</div>
			<div class="input-wrap">
				<label for="memberPw">비밀번호</label>
				<input type="password" name="memberPw" id="memberPw" class="input-form" value="<%=m.getMemberPw() %>">
			</div>
			<div class="input-wrap">
				<label for="memberName">이름</label>
				<input type="text" name="memberName" id="memberName" class="input-form" value="<%=m.getMemberName() %>" readonly>
			</div>
			
			<div class="input-wrap">
				<label for="memberPhone">전화번호</label>
				<input type="text" name="memberPhone" id="memberPhone" class="input-form" value="<%=m.getMemberPhone() %>">
			</div>	
			
			<div class="input-wrap">
				<label for="memberAddr">주소</label>
				<input type="text" name="memberAddr" id="memberAddr" class="input-form" value="<%=m.getMemberAddr() %>">
			</div>
			<div class="input-wrap">
				<label for="memberLevel">회원등급</label>
				<%if(m.getMemberLevel()==1) { %>
					<input type="text" name="memberLevel" id="memberLevel" class="input-form" value="관리자" readonly>
				<% }else if (m.getMemberLevel()==2){ %>
					<input type="text" name="memberLevel" id="memberLevel" class="input-form" value="정회원" readonly>
				<% }else if (m.getMemberLevel()==3){ %>
					<input type="text" name="memberLevel" id="memberLevel" class="input-form" value="준회원" readonly>
				<% } %>
				
			</div>
			<div class="input-wrap">
				<label for="enrollDate">가입일</label>
				<input type="text" name="enrollDate" id="enrollDate" class="input-form" value="<%=m.getEnrollDate() %>" readonly>
			</div>
			<div class="btn-box">
				<button type="submit" class="btn bc11 bs2">정보수정</button>
			</div>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>