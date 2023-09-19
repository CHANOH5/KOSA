package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.custoemr.service.CustomerService;
import com.my.exception.FindException;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService service;
	public LoginController() {
		service = new CustomerService();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
//		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		// 응답형식 지정 -> JSON형태로 할거임
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");	
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setContentType("application/json; charset=utf-8");
		
		// 응답출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		// 요청 전달데이터 얻기
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		// jackson라이브러리의 ObjectMapper클래스 사용하기
		ObjectMapper mapper = new ObjectMapper();
		// 응답할 내용이 key,value이므로
		Map<String, Object> map = new HashMap<>();
		
		HttpSession session = req.getSession();
		//attribute가 있으면 제거할거임
		session.removeAttribute("loginedId");
		
		try {
			
			service.login(id, pwd);
			map.put("status", 1);
			map.put("msg", "로그인 성공");
			// json형태로 응답하기로 했으니 -> jackson라이브러리 쓰기
			
			// 로그인되면 loginedId값을 id로 세팅할거임
			session.setAttribute("loginedId", id);
			
			System.out.println(session);
			
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "로그인 실패");
		}
		
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		
//		out.print("로그인 성공!");
		
	}	// doPost(req,res)

} // end class