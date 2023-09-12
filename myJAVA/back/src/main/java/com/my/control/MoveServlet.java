package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// sendRedirect 메서드사용
//		res.sendRedirect("http://www.naver.com");
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter(); // 출력스트림
//		out.print("BEFORE FORWARD");
//		RequestDispatcher rd = req.getRequestDispatcher("res"); // ""에 /url을 적어주면 된다
		// RequestDispatcher의 두가지 메서드
		
//		rd.forward(req, res); // move서블릿에서 사용했던 req와 res가 anotherResource에 전달되고 다시 돌아오지 않는다 
		// 기존페이지에서 사용했던 req와 다른 페이지에서 사용했던 req가 같은 것이다
//		out.print("AFTER FORWARD");
		
		out.print("BEFORE INCLUDE");
		RequestDispatcher rd = req.getRequestDispatcher("res");
		rd.include(req, res); // 다른 자원의 content를 현재 응답에 포함한다 (다른 자원에 보내싿가 돌아온다)
		out.print("AFTER INCLUDE");
		
	} // doGet
	
} // end class
