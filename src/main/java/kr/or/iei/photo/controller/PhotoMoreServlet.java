package kr.or.iei.photo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.iei.photo.model.service.PhotoService;
import kr.or.iei.photo.model.vo.Photo;

/**
 * Servlet implementation class PhotoMoreServlet
 */
@WebServlet(name = "PhotoMore", urlPatterns = { "/photoMore.do" })
public class PhotoMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoMoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int start = Integer.parseInt(request.getParameter("start"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		PhotoService service = new PhotoService();
		ArrayList<Photo> list = service.photoMore(start, amount);
		
		//결과처리
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		new Gson().toJson(list,out);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
