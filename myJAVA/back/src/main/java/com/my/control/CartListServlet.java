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
		
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.22:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		// 응답형식 JSON
		res.setContentType("application/json; charset=utf-8");
		
		// 응답 출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		// 요청전달데이터 얻기
//		String cartData = req.getParameter("prodno");
		HttpSession session = req.getSession();
		Map<String, Integer> cart = (Map)session.getAttribute("cart");
		
		// List<T> 처럼 제네릭타입을 준건데 타입을 Map으로 정한 것
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
	    if (cart == null) {
	        cart.remove("null");
	    }
		
		// prodNo에 map의 keySet메서드를 써서 key값들을 넣음
		for(String prodNo : cart.keySet()) {
			// key를 가져와 quantitiy에 value값을 대입
			int quantity = cart.get(prodNo);
			// prodNo와 quantity를 map객체에 넣어음 -> list객체에 넣기위함
			Map<String, Object> map = new HashMap<>();
			map.put("prodNo", prodNo);
			map.put("quantity", quantity);
			list.add(map);

		} // for
		
		ObjectMapper mapper = new ObjectMapper();

//		Product p = service.findByProdNo(cartData);
		String jsonStr = mapper.writeValueAsString(list);
		out.print(jsonStr);

	} // doGet

} // end class