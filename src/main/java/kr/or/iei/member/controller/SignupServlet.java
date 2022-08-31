package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet(name = "Signup", urlPatterns = { "/signup.do" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");

		
		//2. 값추출
		Member member = new Member();
		
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw( request.getParameter("memberPw"));
		member.setMemberName(request.getParameter("memberName"));
		member.setMemberPhone(request.getParameter("memberPhone"));
		member.setMemberAddr(request.getParameter("memberAddr"));
		
		MemberService service = new MemberService();
		int result = service.insertMember(member);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			request.setAttribute("title", "회원가입성공");
			request.setAttribute("msg", "환영합니다!!!!");
			request.setAttribute("icon", "success");
			
		} else {
			request.setAttribute("title", "회원가입실패");
			request.setAttribute("msg", "관리자에게 문의하세요");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/");
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
