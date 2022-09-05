package kr.or.iei.notice.model.vo;

public class NoticeComment {
    private int ncNo;
    private String ncWriter;
    private String ncContent;
    private String ncDate;
    private int noticeRef;
    private int ncRef;

    public NoticeComment() {
        super();
    }

    public NoticeComment(int ncNo, String ncWriter, String ncContent, String ncDate, int noticeRef, int ncRef) {
        this.ncNo = ncNo;
        this.ncWriter = ncWriter;
        this.ncContent = ncContent;
        this.ncDate = ncDate;
        this.noticeRef = noticeRef;
        this.ncRef = ncRef;
    }

    public int getNcNo() {
        return ncNo;
    }

    public void setNcNo(int ncNo) {
        this.ncNo = ncNo;
    }

    public String getNcWriter() {
        return ncWriter;
    }

    public void setNcWriter(String ncWriter) {
        this.ncWriter = ncWriter;
    }

    public String getNcContent() {
        return ncContent;
    }

    public void setNcContent(String ncContent) {
        this.ncContent = ncContent;
    }

    public String getNcDate() {
        return ncDate;
    }

    public void setNcDate(String ncDate) {
        this.ncDate = ncDate;
    }

    public int getNoticeRef() {
        return noticeRef;
    }

    public void setNoticeRef(int noticeRef) {
        this.noticeRef = noticeRef;
    }

    public int getNcRef() {
        return ncRef;
    }

    public void setNcRef(int ncRef) {
        this.ncRef = ncRef;
    }
}
