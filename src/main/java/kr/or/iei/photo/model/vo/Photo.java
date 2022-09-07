package kr.or.iei.photo.model.vo;

public class Photo {
	private int photoNo;
	private String photoWriter;
	private String photoTitle;
	private String photoContent;
	private String filepath;
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Photo(int photoNo, String photoWriter, String photoTitle, String photoContent, String filepath) {
		super();
		this.photoNo = photoNo;
		this.photoWriter = photoWriter;
		this.photoTitle = photoTitle;
		this.photoContent = photoContent;
		this.filepath = filepath;
	}
	public int getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}
	public String getPhotoWriter() {
		return photoWriter;
	}
	public void setPhotoWriter(String photoWriter) {
		this.photoWriter = photoWriter;
	}
	public String getPhotoTitle() {
		return photoTitle;
	}
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}
	public String getPhotoContent() {
		return photoContent;
	}
	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}
