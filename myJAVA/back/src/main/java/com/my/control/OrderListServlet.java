package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.service.OrderService;


@WebServlet("/orderlist")
public class OrderListServlet extends HttpServlet {
	
	private OrderService service;
	
	public OrderListServlet() {
		service = new OrderService();
	}
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
//		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		// 응답형식 지정 -> JSON형태로 할거임
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");	
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setContentType("application/json; charset=utf-8");
		
		// 응답출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		// 요청 전달데이터 얻기
		HttpSession session = req.getSession();
//		System.out.println("in orderlist:" + session.getId());
		String loginedId = (String)session.getAttribute("loginedId");
		Map<String, Object> map = new HashMap<>();
		if(loginedId == null) { //로그인 안된 경우
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		}else {
			
			try {
				
				List<OrderInfo> list = service.findById(loginedId);
				map.put("status", 1);
				map.put("list", list);		
				
			} catch (FindException | AddException e) {
				
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
				
			}	
		}
		out.print( mapper.writeValueAsString(map));
		
		
	} // doGet

} // end class
