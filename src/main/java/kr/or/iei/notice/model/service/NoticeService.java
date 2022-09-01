package kr.or.iei.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.notice.model.dao.NoticeDao;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticePageData;

public class NoticeService {
	private NoticeDao dao;

	public NoticeService() {
		super();
		dao = new NoticeDao();
	}

	public NoticePageData selectNoticeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		//1. 한페이지 당 게시물 수 지정 - > 10개
		int numPerPage = 10;
		//요청페이지 1page -> 가장 최신글 1~10
		// reqPage==1 -> 최신글 1번~10번까지
		//요청페이지 2page -> 가장 최신글 11~20
		// reqPage==2 -> 최신글 11번~20번까지
		// reqPage==3 -> 최신글 21번~30번까지
		int end = numPerPage*reqPage;
		int start = end - numPerPage + 1;
		
		ArrayList<Notice> list = dao.selectNoticeList(conn, start, end);
 		
		//페이징처리
		//전체페이지 수 계산 -> 전체 게시물 수 조회
		int totalCount = dao.selectNoticeCount(conn);
		
		//전체페이지
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		//지정해줘야할 값 페이지 네비게이션 사이즈
		int pageNaviSize = 5;

		//페이지 네비게이션 시작번호지정
		//reqPage 1~5  -> 1 2 3 4 5 
		//reqPage 6~10 -> 6 7 8 9 10
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1; //시작번호식

		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼 
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/noticeList.do?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		} 
		//페이지숫자
		for(int i=0; i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/noticeList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/noticeList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/noticeList.do?reqPage=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		NoticePageData npd = new NoticePageData(list, pageNavi);

		JDBCTemplate.close(conn);
		
		return npd;
	}
	
}
