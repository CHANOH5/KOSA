package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		// 응답형식 지정 -> JSON형태로 할거임
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setContentType("application/json; charset=utf-8");
		
		
		HttpSession session = req.getSession();
		System.out.println("지우기 전:" + session.getId());
		session.removeAttribute("loginedId");
		session.invalidate();
		System.out.println("지운 후:" + session.getId());
		
	} // doGet

} // end class
