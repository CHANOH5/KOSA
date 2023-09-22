package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.dto.Customer;
import com.my.util.Attach;

public class SignupController extends CustomerController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		// CORS 문제 해결
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setContentType("application/json;charset=utf-8");
		
		// 응답출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		// 요청 전달데이터 얻기
//		String id = req.getParameter("id");
//		String pwd = req.getParameter("pwd");
//		String name = req.getParameter("name");
//		Customer c = new Customer(id, pwd, name, null);
		
		// jackson라이브러리의 ObjectMapper클래스 사용하기
		ObjectMapper mapper = new ObjectMapper(); // JSON문자열 만드는 API
		
		Map<String, Object> map = new HashMap<>(); // 응답 내용이 담길 map객체
		
		try {

			Attach attach = new Attach(req);
			String id = attach.getParameter("id");
			String pwd = attach.getParameter("pwd");
			String name = attach.getParameter("name");
			Customer c = new Customer(id, pwd, name, null);
			
			service.signup(c);
			
			// 각각 try-catch해야 profile파일 없는 경우에도 intro 작성할 수 있음
			try {
				String originProfileFileName = attach.getFile("f1").get(0).getName();			
				attach.upload("f1", id+"_profile_" + originProfileFileName);
			}catch(Exception e) {
				
			}
			try {
				String originIntroFileName = attach.getFile("f2").get(0).getName();			
				attach.upload("f2", id+"_intro_" + originIntroFileName);			
			}catch(Exception e) {
				
			}
			
			map.put("status", 1);
			map.put("msg", "가입성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "가입실패");
		} // try-catch
		
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		
		
		return null;
	} // execute

} // end class
