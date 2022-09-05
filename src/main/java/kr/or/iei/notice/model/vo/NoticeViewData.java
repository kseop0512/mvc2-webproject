package kr.or.iei.notice.model.vo;

import java.util.ArrayList;

public class NoticeViewData {
	private Notice n;
	private ArrayList<NoticeComment> commentList;
	private ArrayList<NoticeComment> reCommentList;
	public NoticeViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeViewData(Notice n, ArrayList<NoticeComment> commentList, ArrayList<NoticeComment> reCommentList) {
		super();
		this.n = n;
		this.commentList = commentList;
		this.reCommentList = reCommentList;
	}
	public Notice getN() {
		return n;
	}
	public void setN(Notice n) {
		this.n = n;
	}
	public ArrayList<NoticeComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<NoticeComment> commentList) {
		this.commentList = commentList;
	}
	public ArrayList<NoticeComment> getReCommentList() {
		return reCommentList;
	}
	public void setReCommentList(ArrayList<NoticeComment> reCommentList) {
		this.reCommentList = reCommentList;
	}
	
	
}
