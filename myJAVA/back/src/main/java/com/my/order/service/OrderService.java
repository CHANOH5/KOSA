package com.my.order.service;

import com.my.exception.AddException;
import com.my.order.dao.OrderOracleRepository;
import com.my.order.dao.OrderRepository;
import com.my.order.dto.OrderInfo;

public class OrderService {
	
	public OrderRepository repository;
	public OrderService() {
		repository = new OrderOracleRepository();
	}
	
	/**
	 * 주문을 추가한다
	 * @param info
	 * @throws AddException
	 */
	public void add(OrderInfo info) throws AddException {
		repository.insert(info);
	} // add

} // endclass
