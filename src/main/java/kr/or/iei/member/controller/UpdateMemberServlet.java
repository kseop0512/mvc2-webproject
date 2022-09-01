package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet(name = "UpdateMember", urlPatterns = { "/updateMember.do" })
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		//member.setMemberId(request.getParameter("memberId"));
		
		// 정보수정을 위한 조건절 적성을 위해 memberNo 가 필요
		// 로그인된 세션에서 회원번호를 가져옴
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");//session에서 받아올때 object 타입이므로 형변환!
		String memberId = m.getMemberId();
		int memberNo = m.getMemberNo();
		String memberPw = request.getParameter("memberPw");
		String memberPhone = request.getParameter("memberPhone");
		String memberAddr = request.getParameter("memberAddr");
		member.setMemberNo(memberNo);
		member.setMemberPw(memberPw);
		member.setMemberPhone(memberPhone);
		member.setMemberAddr(memberAddr);
		//3. 비지니스 로직

		MemberService service = new MemberService();
		int result = service.updateMember(member);
		
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			//변경성공 시 세션의 정보를 변경데이터로 업데이트
			m.setMemberPw(memberPw);
			m.setMemberPhone(memberPhone);
			m.setMemberAddr(memberAddr);
			
			request.setAttribute("title", "정보변경 성공");
			request.setAttribute("msg", "정보가 변경되었습니다.");
			request.setAttribute("icon", "success");
			
		} else {
			request.setAttribute("title", "정보변경 실패");
			request.setAttribute("msg", "관리자에게 문의하세요");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/mypage2.do?memberId=" + memberId);
		
		
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
