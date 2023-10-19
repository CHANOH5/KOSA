package com.my.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dao.OrderRepository;
import com.my.order.dto.OrderInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	/**
	 * 주문을 추가한다
	 * @param info
	 * @throws AddException
	 */
	public void add(OrderInfo info) throws AddException {
		repository.insert(info);
	} // add
	
	/**
	 * 로그인된 아이디가 주문한 목록을 조회한다
	 * @param loginedId
	 * @return
	 * @throws FindException
	 * @throws AddException 
	 */
	public List<OrderInfo> findById(String loginedId) throws FindException, AddException{
		return repository.selectById(loginedId);
		
	}

} // endclass
