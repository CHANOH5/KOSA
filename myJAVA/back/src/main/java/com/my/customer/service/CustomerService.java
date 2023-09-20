package com.my.customer.service;

import com.my.customer.dao.CustomerOracleMybatisRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class CustomerService {

	private CustomerRepository repository;
	public CustomerService() {
		repository = new CustomerOracleMybatisRepository();
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
	
	/**
	 * 아이디에 해당하는 고객이 존재하지 않으면 FindException발생
	 * @param id
	 * @throws FindException
	 */
	public void idDupChk(String id) throws FindException {
		
		repository.selectById(id);
		
	} // idDupChk
	
	public void insert(Customer c) throws AddException {
		
		repository.insert(c);
		
	} // insert
	
} // end class
