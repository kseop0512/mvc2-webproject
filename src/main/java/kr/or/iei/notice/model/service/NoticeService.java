package kr.or.iei.notice.model.service;

import kr.or.iei.notice.model.dao.NoticeDao;

public class NoticeService {
	private NoticeDao dao;

	public NoticeService() {
		super();
		dao = new NoticeDao();
	}
	
}
