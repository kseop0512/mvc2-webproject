package kr.or.iei.photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.photo.model.vo.Photo;

public class PhotoDao {

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from photo";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

    public int insertPhoto(Connection conn, Photo pt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into photo values(photo_seq.nextval, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pt.getPhotoWriter());
			pstmt.setString(2, pt.getPhotoTitle());
			pstmt.setString(3, pt.getPhotoContent());
			pstmt.setString(4, pt.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
    }

	public ArrayList<Photo> photoMore(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Photo> list = new ArrayList<Photo>();
		String query = "select * from (select rownum as rnum, p.* from(select * from photo order by 1 desc)p) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Photo pt = new Photo();
				pt.setPhotoNo(rset.getInt("photo_no"));
				pt.setPhotoWriter(rset.getString("photo_writer"));
				pt.setPhotoTitle(rset.getString("photo_title"));
				pt.setPhotoContent(rset.getString("photo_content"));
				pt.setFilepath(rset.getString("filepath"));
				
				list.add(pt);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
}
