package kr.or.iei.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name = "AjaxTest6", urlPatterns = { "/ajaxTest6.do" })
public class AjaxTest6Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest6Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		MemberService service = new MemberService();
		Member m = service.selectOneMember(memberId);
		
		//결과처리
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		gson.toJson(m, out);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
