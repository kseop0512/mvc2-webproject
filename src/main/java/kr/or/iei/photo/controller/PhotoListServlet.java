package kr.or.iei.photo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.photo.model.service.PhotoService;

/**
 * Servlet implementation class PhotoListServlet
 */
@WebServlet(name = "PhotoList", urlPatterns = { "/photoList.do" })
public class PhotoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		PhotoService service = new PhotoService();
		int totalCount = service.totalCount();
		
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/photo/photoList.jsp");
		request.setAttribute("totalCount", totalCount);
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
