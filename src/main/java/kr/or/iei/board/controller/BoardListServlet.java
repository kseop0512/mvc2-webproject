package kr.or.iei.board.controller;

import kr.or.iei.board.model.service.BoardService;
import kr.or.iei.board.model.vo.Board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BoardList", value = "/boardList.do")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int reqPage = Integer.parseInt(request.getParameter("reqPage"));

        BoardService service = new BoardService();
        Board b = service.selectBoardList(reqPage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
