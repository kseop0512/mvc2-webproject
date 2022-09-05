package kr.or.iei.board.model.service;

import common.JDBCTemplate;
import kr.or.iei.board.model.dao.BoardDao;
import kr.or.iei.board.model.vo.Board;

import java.sql.Connection;
import java.util.ArrayList;

public class BoardService {
    private BoardDao dao;

    public BoardService() {
        super();
        dao = new BoardDao();
    }


    public Board selectBoardList(int reqPage) {
        Connection conn = JDBCTemplate.getConnection();

        int numperPage = 10;

        int end = numperPage*reqPage;
        int start = end - numperPage + 1;

        ArrayList<Board> list = dao.selectBoardList(conn, start, end);

        int totalCount = dao.selectBoardCount(conn);

        return b;
    }
}
