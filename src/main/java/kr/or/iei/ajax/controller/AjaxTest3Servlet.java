package kr.or.iei.ajax.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjaxTest3", value = "/ajaxTest3.do")
public class AjaxTest3Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int su1 = Integer.parseInt(request.getParameter("num1"));
        int su2 = Integer.parseInt(request.getParameter("num2"));

        int result = su1 + su2;

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
