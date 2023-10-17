package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends CustomerController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		// 헤더 설정
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
//		
		
		res.setContentType("application/json; charset=utf-8");
		
		
		HttpSession session = req.getSession();
		System.out.println("지우기 전:" + session.getId());
		session.removeAttribute("loginedId");
		session.invalidate();
		System.out.println("지운 후:" + session.getId());
	
		return null;
	} // execute

} // end class
