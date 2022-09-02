package kr.or.iei.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet(name = "NoticeDelete", urlPatterns = { "/noticeDelete.do" })
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		NoticeService service = new NoticeService();
		//삭제 후 파일을 처리하기위해 해당 정보를 받음
		Notice n = service.deleteNotice(noticeNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(n!=null) {
			//게시글 삭제 성공하면 해당 게시글의 첨부파일을 삭제
			if(n.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String deleteFile = root + "upload/notice/" + n.getFilepath();
				File delFile = new File(deleteFile);
				delFile.delete();
			}
			
			request.setAttribute("title", "삭제완료");
			request.setAttribute("msg", "삭제가 완료 되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/noticeList.do?reqPage=1");
			
		} else {
			request.setAttribute("title", "삭제실패");
			request.setAttribute("msg", "관리자에게 문의하세요.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/noticeView.do?noticeNo=" + noticeNo);
		}
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
