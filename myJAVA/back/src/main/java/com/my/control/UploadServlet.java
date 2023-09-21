package com.my.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		String tempDir = "C:\\KOSA202307\\temp"; // 임시파일이 저장될 경로
		// String타입의 디렉토리를 만들고
		String attachesDir = "C:\\KOSA202307\\attaches";  // 파일이 첨부될 실제 경로
		
		// 업로드될 파일을 설정
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		
//		File repository = new File(attachesDir);
		File repository = new File(tempDir);
		
		// tempDir의 경로에 폴더가 없을경우
		if(!repository.exists()) {
			
			if(repository.mkdir()) {
				System.out.println(tempDir+"폴더 생성");
			} else {
				System.out.println(tempDir+"폴더 생성안됨");
				return;
			} // inner if-else
			
		} // if
		
		// attachesDir의 경로에 폴더가 없을경우
		if(!new File(attachesDir).exists()) {
			if(new File(attachesDir).mkdir()) {
				System.out.println(attachesDir+"폴더 생성");
			} else {
				System.out.println(attachesDir+"폴더 생성안됨");
				return;
			} // inner if-else
		} // if

		fileItemFactory.setRepository(repository);
		// 파일 업로드를 담당하는 클래스! 이게 핵심! 이걸 외워라!
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		// 파싱 
		try {
			// fileitem 요소들로 갇는 items들을 하나씩 담음
			List<FileItem> items = fileUpload.parseRequest(req);
			
			for(FileItem item : items) {
				if(item.isFormField()) { // 요청 전달데이터인 경우
					// 요청 전달데이터 이름, 첨부내용 뭔지 출력해보기
					System.out.println(item.getFieldName() + " : " + item.getString());
					
					
				} else { // 첨부파일인 경우
					System.out.println(item.getName() + " : " + item.getSize());
					if(item.getSize() > 0) {
						
						File attacheFile = new File(attachesDir, item.getName()); 
						
						// 반복문 안에 try-catch를 작성해서 한파일 에러 발생해도 다음파일은 읽도록 만듦
						try {
							item.write(attacheFile); // 첨부파일 서버에 저장
						} catch (Exception e) {
							e.printStackTrace();
						} // innter try-catch
						
					} //inner if-else
				} // if-else
			} // for 
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} // try-catch
		
	} // doPost()

} // end class