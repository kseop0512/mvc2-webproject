package kr.or.iei.notice.controller;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.notice.model.vo.NoticeComment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "NoticeCommentUpdate", value = "/noticeCommentUpdate.do")
public class NoticeCommentUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.인코딩
        request.setCharacterEncoding("utf-8");
        //2. 값 추출
        int ncNo = Integer.parseInt(request.getParameter("ncNo"));
        int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
        String ncContent = request.getParameter("ncContent");
        NoticeComment nc = new NoticeComment();
        nc.setNcNo(ncNo);
        nc.setNcContent(ncContent);
        //3. 비즈니스 로직
        NoticeService service = new NoticeService();
        int result = service.updateNoticeComment(nc);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
        if(result>0) {
            request.setAttribute("title", "성공");
            request.setAttribute("msg", "댓글이 수정되었습니다");
            request.setAttribute("icon", "success");
        } else {
            request.setAttribute("title", "실패");
            request.setAttribute("msg", "댓글 수정 실패");
            request.setAttribute("icon", "error");
        }
        request.setAttribute("loc", "/noticeView.do?noticeNo="+noticeNo);
        view.forward(request,response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
