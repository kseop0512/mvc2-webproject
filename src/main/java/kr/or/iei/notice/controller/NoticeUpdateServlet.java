package kr.or.iei.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate.do" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//파일업로드 경로설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/notice";
		//파일최대크기 지정
		int maxSize = 10*1024*1024;
		//request -> MultipartRequest 변환(파일업로드
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		int noticeNo = Integer.parseInt(mRequest.getParameter("noticeNo"));
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String noticeContent = mRequest.getParameter("noticeContent");
		//기존파일이 지워졌으면 delete, 그 외 stay
		String status = mRequest.getParameter("status");
		String filename = mRequest.getOriginalFileName("upfile");
		String filepath = mRequest.getFilesystemName("upfile");
		//기존첨부파일이 있었으면 기존첨부파일값, 없었으면 null
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		
		//기존첨부파일이 있는경우
		//	-> 새 첨부파일이 없는경우	-> 1)기존파일을 삭제, 2)기존파일 유지
		//	-> 새 첨부파일이 있는경우 -> 기존파일을 삭제
		//기존첨부파일이 없는경우
		//	-> 새 첨부파일이 없는경우
		//	-> 새 첨부파일이 있는경우
		if(oldFilename != null && status.equals("stay")) {
			filename = oldFilename;
			filepath = oldFilepath;
		} 
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setFilename(filename);
		n.setFilepath(filepath);
		
		//3.비즈니스 로직
		NoticeService service = new NoticeService();
		int result = service.updateNotice(n);
		
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			
			request.setAttribute("title", "수정 완료");
			request.setAttribute("msg", "게시물이 수정되었습니다.");
			request.setAttribute("icon", "success");
			if(status.equals("delete")) {
				File delFile = new File(saveDirectory + "/" + oldFilepath);
				delFile.delete();
			}
		} else {
			request.setAttribute("title", "수정 실패");
			request.setAttribute("msg", "관리자에게 문의하세요.");
			request.setAttribute("icon", "error");
		}

		request.setAttribute("loc", "/noticeView.do?noticeNo=" + noticeNo);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
