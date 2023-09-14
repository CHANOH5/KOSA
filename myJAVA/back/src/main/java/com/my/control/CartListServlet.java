package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.service.ProductService;



@WebServlet("/cartlist")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService service;
	
	public CartListServlet() {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 응답형식 JSON
		res.setContentType("application/json; charset=utf-8");
		
		// CORS문제 해결
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");

		// 응답 출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		// 요청전달데이터 얻기
//		String cartData = req.getParameter("prodno");
		HttpSession session = req.getSession();
		Map<String, Integer> cartData = (Map)session.getAttribute("cart");
		
//		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		
//		Map.Entry<String, Integer> key = (Map)session.getAttribute("cart");

		for(String prodNo : cartData.keySet()) {
			cartData.get(prodNo);
		}
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			
//			Product p = service.findByProdNo(cartData);
			
			String jsonStr = mapper.writeValueAsString(p);
			out.print(jsonStr);
			
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		
	} // doGet

} // end class
