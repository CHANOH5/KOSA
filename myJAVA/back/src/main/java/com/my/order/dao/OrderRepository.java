package com.my.order.dao;

import com.my.exception.AddException;
import com.my.order.dto.OrderInfo;

public interface OrderRepository {
	
	/**
	 * 주문기본정보와 상세정보들을 추가한다
	 * @param info
	 * @throws AddException
	 */
	void insert(OrderInfo info) throws AddException;

} // end class