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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;


@WebServlet("/iddupchk")
public class idDupChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService service;
	
	public idDupChkServlet() {
		service = new CustomerService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// CORS 문제 해결
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setContentType("application/json;charset=utf-8");
		
		// 응답출력스트림 얻기
		PrintWriter out = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		// 요청전달데이터 얻기
		String id = req.getParameter("id");
		
		Map<String, Object> map = new HashMap<>();

		try {
			service.idDupChk(id);
			// 고객이 있는 경우
			map.put("status", 0);
		} catch (FindException e) {
			// 고객이 없는 경우
			map.put("status", 1);
		} // try-catch
		
		// JSON 응답
		out.print( mapper.writeValueAsString(map));
		
	} // doGet

} // main
