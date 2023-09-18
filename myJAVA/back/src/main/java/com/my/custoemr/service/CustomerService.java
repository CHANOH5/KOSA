package com.my.custoemr.service;

import com.my.customer.dao.CustomerOracleRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.FindException;

public class CustomerService {

	private CustomerRepository repository;
	public CustomerService() {
		repository = new CustomerOracleRepository();
	}
	
	/**
	 *  아이디와 비밀번호에 일치하는 고객 정보가 있다면 반환값이 없고
	 *  										 존재하지 않으면 FindException 발생
	 * @param id 아이디값
	 * @param pwd 비번값
	 * @throws FindException
	 */
	public void login(String id, String pwd) throws FindException {
		
		try {
			Customer c = repository.selectById(id);
			if(!c.getPwd().equals(pwd)) {
				throw new FindException();
			}
		} catch(FindException e) {
			throw new FindException("로그인 실패");
		} // try-catch
		
		// throws된 예외를 servlet이 받을거임
		
		
	} // login
	
} // end class