package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.service.ProductService;

@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService service;
	public ProductListServlet() {
		service = new ProductService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		// 요청전달데이터 currentPage 얻기
		String currentPage = req.getParameter("currentPage");
		int cp = 1;
		
		if(currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		} // if
		
		// 정상처리되면 갈 곳
		String path = "productlistresult.jsp";
		try {
			
			List<Product> list = service.findAll(cp);
			
			// 데이터 보낼 list를 담기
			req.setAttribute("list", list);

		} catch (FindException e) {
			e.printStackTrace();
			path = "fail.jsp"; // DB연결이 안되거나 실패한 경우 
			req.setAttribute("msg", e.getMessage());
		} // try-catch 
		
		// 페이지 이동 및 데이터 보내기 
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, res);
		
	} // doGet

} // end class
