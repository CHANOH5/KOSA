package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

public class CartListController implements Controller {

	ProductService service = new ProductService();
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 서블릿이 응답할 형식 지정하기
		res.setContentType("application/json;charset=utf-8");

		// 응답 출력 스트림 얻기
		PrintWriter out = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		// 세션 생성
		HttpSession session = req.getSession();
		
		// session 객체의 Attribute값 얻기 (이름: "cart")
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");	
		
		List list = new ArrayList<>();
		
		if(cart == null) {
			cart = new HashMap<String, Integer>();
			session.setAttribute("cart", cart);
		} // if
		
		Product p;
		for(String prodNo : cart.keySet()) {
			int quantity = cart.get(prodNo);
			
			try {
				p = service.findByProdNo(prodNo);
				Map map = new HashMap<>();
				
				map.put("product", p);
				map.put("quantity", quantity);
				
				list.add(map);
			} catch (FindException e) {
				e.printStackTrace();
			} // try-catch
			
		} // for
			
		// JSON 문자열 응답
		
		String jsonStr = mapper.writeValueAsString(list);
		System.out.println(jsonStr);
		out.print(jsonStr);
		
		
		return null;
	} // execute

} // end class
