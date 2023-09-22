package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.order.service.OrderService;

public abstract class OrderController implements Controller {

	protected OrderService service;
	
	public OrderController() {
		// 싱글톤 패턴이기 때문에 new로 가져오지 못하고 getInstance메서드를 사용해야함
		service = OrderService.getInstance();
		
	} //cosntrcutor

} // end class
