package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class ChangeLevelServlet
 */
@WebServlet(name = "ChangeLevel", urlPatterns = { "/changeLevel.do" })
public class ChangeLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeLevelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		
		MemberService service = new MemberService();
		int result = service.changeLevel(memberNo, memberLevel);
		
		//4. 결과처리
		//정보변경 성공 -> 관리자페이지
		if(result>0) {
			response.sendRedirect("/adminPage.do");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "정보변경실패");
			request.setAttribute("msg", "정보변경 중 문제가 발생했습니다.");
			request.setAttribute("icon", "warning");
			request.setAttribute("loc", "/adminPage.do");
			view.forward(request, response);
		}
		//정보변경 실패 -> msg.jsp 이용해서 메세지 출력후 관리자페이지
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
