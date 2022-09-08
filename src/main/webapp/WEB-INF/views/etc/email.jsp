<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">EMAIL</div>
		<h3>1. EMAIL 전송하기</h3><br>
		<form action="/mailSend1.do" method="post">
			<table class="tbl">
				<tr class="tr-1">
					<th class="td-2">제목</th>
					<td><input type="text" name="mailTitle" class="input-form"></td>
				</tr>
				<tr class="tr-1">
					<th class="td-2">수신자메일</th>
					<td>
						<input type="text" name="receiver" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-2">내용</th>
					<td>
						<textarea name="mailContent" class="input-form"></textarea>
					</td>
				</tr>
				<tr class="tr-1">
					<th colspan="2">
						<button class="btn bc2 bs4" type="submit">전송</button>
					</th>
				</tr>
			</table>
		</form>
		<h3>2. 이메일 인증</h3>
		
		<input type="text" id="email">
		<button class="btn bc1" onclick="sendMail();">인증메일 전송</button>
		<div id="auth" style="display:none;">
			<input type="text" id="authCode" placeholder="">
			<button class="btn bc1" id="authBtn">인증</button>
			<span id="timeZone"></span>
			<span id="authMsg"></span>
		</div>
		
	</div>
	
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
		let mailCode;
		function sendMail() {
			const email = $("#email").val();
			
			$.ajax({
				url: "/sendMail2.do",
				data: {email:email},
				type:"post",
				success: function(data){
					mailCode = data;
					$("#auth").show();
					authTime();
				}
				
			})
		}
		let intervalId;
		
		function authTime() {
			$("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>");
			intervalId = window.setInterval(function() {
				timeCount();
			}, 1000)
		}
		function timeCount() {
			const min = Number($("#min").text());
			const sec = $("#sec").text();
			
			if(sec == "00") {
				if (min == 00) {
					mailCode = null;
					clearInterval(intervalId);
					
				} else {
					$("#min").text(min-1);
					$("#sec").text(59);
				}
			} else {
				const newSec = Number(sec)-1;
				if(newSec < 10) {
					$("#sec").text("0" + newSec);
				} else {
					$("#sec").text(newSec);
				}
			} 
		}

		$("#authBtn").on("click", function() {
			const inputValue = $("#authCode").val();
			if(mailCode != null) {
				if(inputValue == mailCode) {
					$("#authMsg").text("인증성공");
					$("#authMsg").css("color","blue");
					clearInterval(intervalId);
					$("#timeZone").hide();
				} else {
					$("#authMsg").text("인증실패");
					$("#authMsg").css("color","red");
				}
			} else {
				$("#authMsg").text("인증시간만료");
				$("#authMsg").css("color","red");
			}
		
			
			
		})
	</script>
</body>
</html>