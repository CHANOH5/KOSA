package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.order.service.OrderService;

@WebServlet("/addorder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService service;
	public AddOrderServlet() {
		service = new OrderService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
//		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		// 응답형식 지정 -> JSON형태로 할거임
		res.setContentType("application/json; charset=utf-8");
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
		
		PrintWriter out = res.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr;
		
		HttpSession session = req.getSession();
		
		// session에 있ㅎ는 loginedId값 얻기
		String loginedId = (String)session.getAttribute("loginedId");
		
//		String prodNo = req.getParameter("prodNo");
//		String prodName = req.getParameter("prodName");
//		String prodPrice = req.getParameter("prodPrice");
//		String qunatity = req.getParameter("quantity");

//		OrderInfo orderInfo = new OrderInfo();

//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<>();
		
		if(loginedId == null) {
			map.put("status", 0);
			map.put("msg", "로그인하세여");
		} else {
			
			Map<String, Integer> cart = (Map)session.getAttribute("cart"); 
			
			if( cart == null) {
				map.put("status", 0);
				map.put("msg", "장바구니가 비었습니다");
			} else {
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId);
				
				List<OrderLine> lines = new ArrayList<>();
				info.setLines(lines);
				
				for(String prodNo : cart.keySet()) {
					int quantity = cart.get(prodNo);
					
					OrderLine line = new OrderLine();
					Product p = new Product();
					p.setProdNo(prodNo);
					
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line);
				}
				
				try {
					service.add(info);
					//장바구니 비우기
					session.removeAttribute("cart");
					map.put("status", 1);
				} catch (AddException e) {
					e.printStackTrace();
					map.put("status", 0);
					map.put("msg", e.getMessage());
				} // try-catch
			} // inner if-else
		} // if-else

		out.print(mapper.writeValueAsString(map));
	} // doGet

} // end class
