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

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "InsertComment", urlPatterns = { "/insertComment.do" })
public class InsertCommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        NoticeComment nc =  new NoticeComment();
        nc.setNcWriter(request.getParameter("ncWriter"));
        nc.setNcContent(request.getParameter("ncContent"));
        nc.setNoticeRef(Integer.parseInt(request.getParameter("noticeRef")));
        nc.setNcRef(Integer.parseInt(request.getParameter("ncRef")));

        NoticeService service = new NoticeService();
        int result = service.insertNoticeComment(nc);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

        if(result>0){
            request.setAttribute("title", "성공");
            request.setAttribute("msg", "댓글 작성 완료");
            request.setAttribute("icon", "success");
        } else {
            request.setAttribute("title", "실패");
            request.setAttribute("msg", "댓글 작성 실패");
            request.setAttribute("icon", "error");
        }

        request.setAttribute("loc", "/noticeView.do?noticeNo=" + nc.getNoticeRef());
        view.forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
