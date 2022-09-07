<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Member m = (Member)session.getAttribute("m");
    %>
<!-- 구글 아이콘 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- jquery -->
<script src="/js/jquery-3.6.0.js"></script>
<!-- 기본 CSS -->
<link rel="stylesheet" href="/css/default.css" />
<!-- 기본 js -->
<script src="/js/default.js"></script>

<header>
  <div class="site-logo">
    <a href="/">SEOP WORLD</a>
  </div>
  <nav>
    <ul class="navi">
      <li><a href="/noticeList.do?reqPage=1">공지사항</a></li><!-- 페이지를 나누기위해 reqPage 할당 -->
      <li><a href="/boardList.do?reqPage=1">자유게시판</a></li>
      <li><a href="/ajax.do">AJAX</a></li>
      <li><a href="/photoList.do">사진게시만</a></li>
      <li>
		<a href="#">메뉴-5</a>
        <ul class="sub-navi">
          <li><a href="#">sub-1</a></li>
          <li><a href="#">sub-2</a></li>
          <li><a href="#">sub-3</a></li>
        </ul>
       </li>
    </ul>
  </nav>
  
  <div class="header-link">
  <%if(m == null){ %>
  	<button class="btn bc11 modal-open-btn" target="#login-modal">SIGN IN</button>
    <a class="btn bc11" href="/signupFrm.do">SIGN UP</a>
  <%} else { %>
  	<%-- <a class="btn bc11" href="/mypage1.do"><%=m.getMemberName() %></a> --%>
  	<%-- 세션에서 마이페이지로 아이디 보냄 (쿼리 스트링 query string)--%>
  	<a class="btn bc11" href="/mypage2.do?memberId=<%=m.getMemberId()%>"><%=m.getMemberName() %></a>
    <a class="btn bc11" href="/logout.do">LOGOUT</a>  
  <%} %>
  </div>
</header>
<%if(m==null) {%>
 <div id="login-modal" class="modal-bg">
   <div class="modal-wrap">
     <div class="modal-head">
       <h2>SIGN IN</h2>
       <span class="material-icons close-icon modal-close">close</span>
     </div>
     <form action="/signIn.do" method="post">
     <div class="modal-content">
       <div class="input-box" style="border:0">
       		<label for="signId">아이디</label>
       		<input type="text" name="signId" id="signId" class="input-form" placeholder="아이디입력">
       </div>
       <div class="input-box">
       		<label for="signPw">비밀번호</label>
       		<input type="password" name="signPw" id="signPw" class="input-form" placeholder="비밀번호입력">
       </div>
       <div class="input-box link-box">
       		<a href="#">아이디/비밀번호 찾기</a>
       </div>
     </div>
     <div class="modal-foot">
       <button type="submit" class="btn bc11">로그인</button>
       <button type="button" class="btn bc1 modal-close">취소</button>
     </div>
     </form>
     
   </div>
 </div>
 <%}%>