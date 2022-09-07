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

        int numPerPage = 10;

        int end = numPerPage*reqPage;
        int start = end - numPerPage + 1;

        ArrayList<Board> list = dao.selectBoardList(conn, start, end);

        int totalCount = dao.selectBoardCount(conn);

        int totalPage = 0;
        if(totalCount%numPerPage == 0) {
            totalPage = totalCount/numPerPage;
        } else {
            totalPage = totalCount/numPerPage + 1;
        }

        int pageNaviSize = 5;
        int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;

        String pageNavi = "<ul class='pagination circle-style'>";

        if(pageNo != 1) {
            pageNavi += "<li>";
            pageNavi += "<a class='page-item' href='/noticeList.do?reqPage=" + (pageNo - 1) + "'>";
            pageNavi += "<span class='material-icons'>chevron_left</span>";
            pageNavi += "</a></li>";
        }

        for(int i=0; i<pageNaviSize; i++) {
            if(pageNo == reqPage) {
                pageNavi += "<li>";
                pageNavi += "<a class='page-item active-page' href='/noticeList.do?reqPage=" + pageNo + "'>";
                pageNavi += pageNo;
                pageNavi += "</a></li>";
            } else {
                pageNavi += "<li>";
                pageNavi += "<a class='page-item' href='/noticeList.do?reqPage=" + pageNo + "'>";
                pageNavi += pageNo;
                pageNavi += "</a></li>";
            }
        }
        Board b = new Board();
        return b;
    }
}
