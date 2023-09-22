package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.product.service.ProductService;

// abstract - 추상클래스를 만들어서 overring 하지 않음
public abstract class ProductController implements Controller {

	protected ProductService service;
	public ProductController() {
		// 싱글톤 패턴이기 때문에 new로 가져오지 못하고 getInstance메서드를 사용해야함
		service = ProductService.getInstance();
	} // constructor

} // end class
