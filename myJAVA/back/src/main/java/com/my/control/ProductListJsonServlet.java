package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.PageGroup;
import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.service.ProductService;


@WebServlet("/productlistjson")
public class ProductListJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService service;
	public ProductListJsonServlet() {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// 응답형식 지정 -> JSON형태로 할거임
		res.setContentType("application/json; charset=utf-8");
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		// 응답 출력스트림 얻기
		PrintWriter out = res.getWriter();
		
		// 요청전달데이터 currentPage 얻기
		String currentPage = req.getParameter("currentPage");
		int cp = 1;
		
		if(currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		} // if
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			PageGroup<Product> pg = service.findAll(cp); // 
			// 이 객체의 내용을 out으로 출력할 것임
//			out.print("{ \"나이\" : 10 ,\"주소\" : \"서울특별시 양천구 목동\"}");
			
			String jsonStr = mapper.writeValueAsString(pg);
			out.print(jsonStr);
			
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		
	} // doGet

} // end class
