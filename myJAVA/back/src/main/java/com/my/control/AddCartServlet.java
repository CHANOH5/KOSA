package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 1.요청전달 데이터 얻기
		String prodNo = req.getParameter("prodNo");
		
		String quantityStr = req.getParameter("quantity");
		int quantity = Integer.parseInt(quantityStr);
		
		System.out.println("상품번호 : " + prodNo + ", 수량 : " + quantity);
		
		// 2. HttpSession 객체 얻기
		HttpSession session = req.getSession();
		
		String sessionId = session.getId();
		
		// 3. session객체의 어티르뷰트 값 얻기
		Map<String, Integer> cart = (Map)session.getAttribute("cart");
		
		// 4. cart attribute가 없으면 객체 생성 후 어트리뷰트로 추가
		if(cart == null) {
//			cart = (Map<String, Integer>) req.getSession();
//			cart = (Map)session.getAttribute("cart");
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		} // if
		
		// 5. 요청전달데이터 상품번호가 key, 수량이 value로 
		//    어트리뷰트의 요소로 추가한다.
		if(cart.containsKey(prodNo)) {
            // 상품번호가 있으면 수량을 누적
            int add = cart.get(prodNo);
            cart.put(prodNo, add + quantity);
		} else { 
			// 없으면 그냥 추가
			cart.put(prodNo, quantity);
		} // if-else
		
		// 6. 어트리뷰트 요소들을 모두 출력한다
		for(Map.Entry<String, Integer>key : cart.entrySet()) {
			System.out.println("상품번호 : " + key.getKey() + ", 상품수량 : " + key.getValue());
		} // for
		
	} // doGet

} // end class