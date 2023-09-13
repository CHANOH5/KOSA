package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.module.FindException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dao.ProductOracleRepository;
import com.my.dao.ProductRepository;
import com.my.dto.Product;

@WebServlet("/productjson")
public class ProdictJsonServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ProductRepository service;
	public ProdictJsonServlet() {
		service = new ProductOracleRepository();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//응답형식
		res.setContentType("application/json; charset=utf-8");
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		// 응답 출력스트림 얻기
		PrintWriter out = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		// 요청 전달데이터 얻기
		String prodNo = req.getParameter("prodno");
		
		try {			
			Product p = service.selectByProdNo(prodNo);
			String jsonStr = mapper.writeValueAsString(p);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
			
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		} // try-catch
		
	} // doget

} // endclass
