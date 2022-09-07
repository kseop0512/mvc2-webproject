package kr.or.iei.board.model.dao;

import common.JDBCTemplate;
import kr.or.iei.board.model.vo.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDao {
    public ArrayList<Board> selectBoardList(Connection conn, int start, int end) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<Board> list = new ArrayList<Board>();
        String query ="select from free_board order by board_no desc";
        query = "select rownum as rnum, n.* from(select from free_board order by board_no desc)n";
        query = "select * from(select rownum as rnum, n.* from(select from free_board order by board_no desc)n) where rnum between ? and ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);

            rset = pstmt.executeQuery();

            while(rset.next()) {
                Board b = new Board();
                b.setBoardNo(rset.getInt("board_no"));
                b.setBoardTitle(rset.getString("board_title"));
                b.setBoardWriter(rset.getString("board_writer"));
                b.setBoardContent(rset.getString("board_content"));
                b.setRegDate(rset.getString("reg_date"));
                b.setReadCount(rset.getInt("read_count"));
                b.setFilename(rset.getString("filename"));
                b.setFilepath(rset.getString("filepath"));

                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }


        return list;
    }

    public int selectBoardCount(Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int count = 0;

        String query = "select count(*) as cnt from free_board";


        try {
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();
            count = rset.getInt("cnt");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }

        return count;
    }
}
