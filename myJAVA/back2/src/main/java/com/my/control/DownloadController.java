package com.my.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/octet-stream; charset=UTF-8");
		
		//이건 문자단위로 응답할때 필요한 것이라 쓰지 않음
//		PrintWriter out = res.getWriter();
		
		//우린 byte단위로 응답하는것을 사용해야 함
		ServletOutputStream sos = res.getOutputStream();
		
		
		String id = req.getParameter("id");
		String opt = req.getParameter("opt");
		String attachesDir = "C:\\KOSA202307\\attaches";
		String fileName = id + "_" + opt + "_";
		File dir = new File(attachesDir);
		
		for(File file : dir.listFiles()) {
			String existFileName = file.getName();
			if(existFileName.startsWith(fileName)) {
				System.out.println(existFileName+"파일입니다. 파일크기 : " + file.length());
				res.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(existFileName, "UTF-8"));
				// 찾아낸 파일을 1바이트씩 읽어서 sos.write로 쓰기해주면 된다
				FileInputStream fis = new FileInputStream(file);
				int readValue = -1;
				while((readValue = fis.read()) != -1) {
					sos.write(readValue);
				}
				sos.close();
				
//				return;
			}
		}
		System.out.println(id + "의 프로필 파일이 없습니다");

		return null;
	}

}
