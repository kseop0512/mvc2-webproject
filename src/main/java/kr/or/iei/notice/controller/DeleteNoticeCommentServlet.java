package kr.or.iei.notice.controller;

import kr.or.iei.notice.model.service.NoticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteNoticeComment", value = "/deleteNoticeComment.do")
public class DeleteNoticeCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int ncNo = Integer.parseInt(request.getParameter("ncNo"));
        int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

        NoticeService service = new NoticeService();
        int result = service.deleteNoticeComment(ncNo);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
        
        if(result>0){
            request.setAttribute("title", "삭제성공");
            request.setAttribute("msg", "댓글이 삭제되었습니다.");
            request.setAttribute("icon", "success");
            
        } else {
            request.setAttribute("title", "삭제실패");
            request.setAttribute("msg", "댓글을 삭제하지 못했습니다.");
            request.setAttribute("icon", "error");
        }

        request.setAttribute("loc", "/noticeView.do?noticeNo=" + noticeNo);
        view.forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
