//package com.my.control;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.my.customer.dto.Customer;
//import com.my.customer.service.CustomerService;
//import com.my.exception.AddException;
//
//@WebServlet("/signup")
//public class SignupServlet2 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private CustomerService service;
//	
//	public SignupServlet2() {
//		service = new CustomerService();
//	}
//
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//		// CORS 문제 해결
//		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
//		res.setHeader("Access-Control-Allow-Credentials", "true");
//		
//		res.setContentType("application/json;charset=utf-8");
//		
//		// 응답출력스트림 얻기
//		PrintWriter out = res.getWriter();
//		
//		// 요청 전달데이터 얻기
//		String id = req.getParameter("id");
//		String pwd = req.getParameter("pwd");
//		String name = req.getParameter("name");
//		
//		// jackson라이브러리의 ObjectMapper클래스 사용하기
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Object> map = new HashMap<>();
//		
//		try {
//			Customer c = new Customer(id, pwd, name, null);
//					
//			service.insert(c);
//			map.put("status", 1);
//			map.put("msg", "가입성공");
//			
//		} catch(AddException e) {
//			e.printStackTrace();
//			map.put("status", 0);
//			map.put("msg", "가입실패");
//		} // try-catch
//		
//		String jsonStr = mapper.writeValueAsString(map);
//		out.print(jsonStr);
//		
//	} // doPost
//
//} //end class
