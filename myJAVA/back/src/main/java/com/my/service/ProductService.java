package com.my.service;

import java.util.List;

import com.my.dao.ProductOracleRepository;
import com.my.dao.ProductRepository;
import com.my.dto.Product;
import com.my.exception.FindException;

public class ProductService {

	private ProductRepository repository;
	public ProductService() {
		repository = new ProductOracleRepository();
	}
	
	public List<Product> findAll(int currentPage) throws FindException {
	
		if(currentPage < 1) {
			currentPage = 1;
		} // if
		
		int cntPerPage = 3; // 한 페이지당 보여줄 목록 수!!

		// currentPage     	// 1 2 3 4
		int startRow = 1;	// 1 4 7 10
		int endRow = 3;		// 3 6 9 12
		
//		for(int i = 1; i <= currentPage; i++) {
//			
//			endRow =+ 3;
//		} // for
		
		return repository.selectAll(startRow, endRow);
		
	} // findAll
	
} // end class
