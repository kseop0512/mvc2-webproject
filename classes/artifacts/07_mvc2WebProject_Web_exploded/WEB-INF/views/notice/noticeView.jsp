<%@page import="kr.or.iei.notice.model.vo.NoticeComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Notice n = (Notice)request.getAttribute("n");
    ArrayList<NoticeComment> commentList = (ArrayList<NoticeComment>)request.getAttribute("commentList");
    ArrayList<NoticeComment> reCommentList = (ArrayList<NoticeComment>)request.getAttribute("reCommentList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	 #noticeView th, #noticeView td{border:1px solid #eee;}
	 #noticeContent{min-height:300px;text-align:left;font-family:ns-light;}
	 .inputCommentBox{margin:50px;}
	 .inputCommentBox>form>ul{list-style-type:none;display:flex;}
	 .inputCommentBox>form>ul>li:first-child{width:15%;display: flex;justify-content: center;align-items: center}
	 .inputCommentBox>form>ul>li:first-child>span{font-size:80px;color:#ccc;}
	 .inputCommentBox>form>ul>li:nth-child(2){width:75%;}
	 .inputCommentBox>form>ul>li:nth-child(2)>textarea.input-form{height:96px;min-height:96px;}
	 .inputCommentBox>form>ul>li:last-child{width:10%;}
	 .inputRecommentBox{margin:30px 0;display:none}
	 .inputRecommentBox>form>ul{list-style-type:none;display:flex}
	 .inputRecommentBox>form>ul>li:first-child{width:15%;display:flex;justify-content: center;align-items: center}
	 .inputRecommentBox>form>ul>li:first-child>span{font-size:50px;color:#ccc;}
	 .inputRecommentBox>form>ul>li:nth-child(2){width:75%;}
	 .inputRecommentBox>form>ul>li:nth-child(2)>textarea.input-form{height:96px;min-height:96px}
	 .inputRecommentBox>form>ul>li:last-child{width:10%}

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
					<a class="btn bc44" href="/noticeUpdateFrm.do?noticeNo=<%=n.getNoticeNo()%>">수정</a>
					<button type="button" class="btn bc44" onclick="noticeDelete(<%=n.getNoticeNo()%>);">삭제</button>
				</th>
			</tr>
			<%} %>
		</table>
		<%if(m!=null){ %>
		<div class="inputCommentBox">
			<form action="/insertComment.do" method="post">
				<ul>
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<input type="hidden" name="ncWriter" value="<%=m.getMemberId()%>">
						<input type="hidden" name="noticeRef" value="<%=n.getNoticeNo()%>">
						<input type="hidden" name="ncRef" value="0">
						<textarea name="ncContent" class="input-form"></textarea>
					</li>
					<li>
						<button type="submit" class="btn bc1 bs4">등록</button>
					</li>
				</ul>
			</form>
		</div>
		<%} %>
		<div class="commentBox">
			<%for(NoticeComment nc : commentList) {%>
			<ul class="posting-comment">
				<li>
					<span class="material-icons">account_box</span>
				</li>
				<li>
					<p class="comment-info">
						<span><%=nc.getNcWriter()%></span>
						<span><%=nc.getNcDate()%></span>
					</p>
					<p class="comment-content"><%=nc.getNcContent()%></p><%-- br 처리필요 --%>
					<textarea name="ncContent" class="input-form" style="min-height:96px;display:none;"><%=nc.getNcContent()%></textarea>
					<p class="comment-link">
						<%if(m!=null) {%>
							<%if(m.getMemberId().equals(nc.getNcWriter()) || m.getMemberLevel()==1) { %><%-- 본인이 작성한 곳에 수정/삭제 버튼 보여짐 --%>
							<a href="javascript:void(0)" onclick="modifyComment(this, <%=nc.getNcNo()%>, <%=n.getNoticeNo()%>);">수정</a>
							<a href="javascript:void(0)" onclick="deleteComment(this, <%=nc.getNcNo()%>, <%=n.getNoticeNo()%>);">삭제</a>
							<%}%>
						<a href="javascript:void(0);" class="recShow">답글달기</a>
						<%} //답글조건 if문 (로그인 체크)%>
					</p>
				</li>
			</ul>
		    <%for(NoticeComment ncc:reCommentList) { %>
				<% if(ncc.getNcRef() == nc.getNcNo()) {%>
					<ul class="posting-comment reply">
						<li>
							<span class="material-icons">subdirectory_arrow_right</span>
							<span class="material-icons">account_box</span>
						</li>
						<li>
							<p class="comment-info">
								<span><%=ncc.getNcWriter()%></span>
								<span><%=ncc.getNcDate()%></span>
							</p>
							<p class="comment-content"><%=ncc.getNcContent()%> </p>
							<textarea name="ncContent" class="input-form" style="min-height:96px;display:none;"><%=ncc.getNcContent()%></textarea>
							<p class="comment-link">
								<%if(m!=null) {%>
								<%if(m.getMemberId().equals(ncc.getNcWriter()) || m.getMemberLevel()==1) { %><%-- 본인이 작성한 곳에 수정/삭제 버튼 보여짐 --%>
								<a href="javascript:void(0)" onclick="modifyComment(this, <%=ncc.getNcNo()%>, <%=n.getNoticeNo()%>);">수정</a>
								<a href="javascript:void(0)" onclick="deleteComment(this, <%=ncc.getNcNo()%>, <%=n.getNoticeNo()%>);">삭제</a>
								<%}%>
								<%} //답글조건 if문 (로그인 체크)%>
							</p>
						</li>
					</ul>
				<%} //해당 댓글의 대댓글인지 체크하는 if문 종료%>
		    <%} //대댓글 출력 for문 종료%>
			<%if(m!=null) {%>
			<div class="inputRecommentBox">
				<form action="/insertComment.do" mehtod="post">
				<ul>
					<li>
						<span class="material-icons">subdirectory_arrow_right</span>
					</li>
					<li>
						<input type="hidden" name="ncWriter" value="<%=m.getMemberId()%>">
						<input type="hidden" name="noticeRef" value="<%=n.getNoticeNo()%>">
						<input type="hidden" name="ncRef" value="<%=nc.getNcNo()%>">
						<textarea class="input-form" name="ncContent" id=""></textarea>
					</li>
					<li>
						<button type="submit" class="btn bc1 bs4">등록</button>
					</li>
				</ul>
				</form>

			</div>
			<%} //대댓글달기 form 조건문%>
			<%} //댓글 반복문 종료%>
		</div>
	</div>
	<script>
		function noticeDelete(noticeNo) {
			if(confirm("공지사항을 삭제하시겠씁니까?")) {
				location.href="/noticeDelete.do?noticeNo=" + noticeNo;
			}
		}
		$(".recShow").on("click", function() {
		    const idx = $(".recShow").index(this);
            if($(this).text() == "답글달기") {
                $(this).text("취소");
            } else {
                $(this).text("답글달기");
            }
            $(".inputRecommentBox").eq(idx).toggle();
            $(".inputRecommentBox").eq(idx).find("textarea").focus();
		});

		function modifyComment(obj, ncNo, noticeNo) {
			$(obj).parent().prev().show();//textarea 화면에 보여줌
			$(obj).parent().prev().prev().hide();//내용을 보여주던 p 태그 숨기기

			//수정 -> 수정완료
			$(obj).text("수정완료");
			$(obj).attr("onclick", "modifyComplete(this, "+ ncNo +", " + noticeNo +")");

			//삭제 -> 수정취소
			$(obj).next().text("수정취소");
			$(obj).next().attr("onclick", "modifyCancel(this, "+ ncNo +", " + noticeNo +")")
			//답글달기버튼 숨김
			$(obj).next().next().hide();
		}

		function modifyCancel(obj, ncNo, noticeNo) {
			$(obj).parent().prev().hide();//textarea 숨김
			$(obj).parent().prev().prev().show();//기존댓글 보여줌
			//수정완료 -> 수정
			$(obj).prev().text("수정");
			$(obj).prev().attr("onclick", "modifyComment(this, "+ ncNo +", " + noticeNo +")");
			//수정취소 -> 삭제
			$(obj).text("삭제");
			$(obj).attr("onclick", "deleteComment(this, "+ ncNo +", " + noticeNo +")");
			//답글달기 버튼 보여줌
			$(obj).next().show();
		}

		function modifyComplete(obj, ncNo, noticeNo) {
			//form 태그를 생성해서 전송
			//1. form 태그 생성
			const form = $("<form action='/noticeCommentUpdate.do' method='post'></form>")
			//2. input 태그 생성(ncNo)
			const ncInput = $("<input type='text' name='ncNo'>");
			//ncNo값 세팅
			ncInput.val(ncNo);
			//form 태그에 추가
			form.append(ncInput);
			const noticeNoInput = $("<input type='text' name='noticeNo'>");
			noticeNoInput.val(noticeNo);
			form.append(noticeNoInput);

			//4.textarea
			const ncContent = $(obj).parent().prev();
			form.append(ncContent);

			$("body").append(form);
			form.submit();

		}

		function deleteComment(obj, ncNo, noticeNo){
			if(confirm("댓글을 삭제하시겠습니까?")) {
				location.href = "/deleteNoticeComment.do?ncNo=" + ncNo +"&noticeNo=" + noticeNo;
			}
		}

	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>