package com.my.control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessiontracking")
public class SessionTrackingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		HttpSession session = req.getSession();
		String sessionId = session.getId();
		boolean flag = session.isNew();
		// 최종 사용시간 확인
		long time = session.getMaxInactiveInterval();
		
		System.out.println("세션ID: " +  sessionId 
				+ ", 세션생성여부: " + flag
				+ ", 세션 최종사용시간: " + new Date(time));

	} // doGet

} // end class
