package kr.or.iei.photo.controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.iei.photo.model.service.PhotoService;
import kr.or.iei.photo.model.vo.Photo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PhotoListServlet
 */
@WebServlet(name = "PhotoWrite", urlPatterns = { "/photoWrite.do" })
public class PhotoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/photo";
		int maxSize = 10*1024*1024;

		MultipartRequest mRequest = new MultipartRequest(request,saveDirectory,maxSize,"UTF-8", new DefaultFileRenamePolicy());

		String photoWriter = mRequest.getParameter("photoWriter");
		String photoTitle = mRequest.getParameter("photoTitle");
		String photoContent = mRequest.getParameter("photoContent");
		String filepath = mRequest.getFilesystemName("imageFile");

		Photo pt = new Photo();
		pt.setPhotoWriter(photoWriter);
		pt.setPhotoTitle(photoTitle);
		pt.setPhotoContent(photoContent);
		pt.setFilepath(filepath);

		PhotoService service = new PhotoService();
		int result = service.insertPhoto(pt);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if(result>0) {
			request.setAttribute("title", "작성 완료");
			request.setAttribute("msg", "게시물이 등록되었습니다.");
			request.setAttribute("icon", "success");
		} else {
			request.setAttribute("title", "작성 실패");
			request.setAttribute("msg", "관리자에게 문의하세요.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/photoList.do");

		view.forward(request,response);

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
