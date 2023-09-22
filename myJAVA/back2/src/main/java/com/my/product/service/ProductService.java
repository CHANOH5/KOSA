package com.my.product.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dao.ProductOracleMybatisRepository;
import com.my.product.dao.ProductRepository;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;

public class ProductService {

	private ProductRepository repository;
	
	private static ProductService service = new ProductService();
	
	public ProductService() {
		repository = new ProductOracleMybatisRepository();
	}
	
	public static ProductService getInstance() {
		return service;
	}
	
	public PageGroup<Product> findAll(int currentPage) throws FindException {
	
		if(currentPage < 1) {
			currentPage = 1;
		} // if
		
		int cntPerPage = 3; // 한 페이지당 보여줄 목록 수!!

		// currentPage     	// 1 2 3 4
		int startRow;	// 1 4 7 10
		int endRow;		// 3 6 9 12
		
		startRow = (currentPage - 1) * cntPerPage + 1;
		endRow = startRow + cntPerPage - 1; // endRow를 수정합니다.

		List<Product> list = repository.selectAll(startRow, endRow);
		
		// 전체 행 수 획득(전체 상품수)
		int totalCnt = repository.selectCount();
//		
//		int totalPage; 		//총 페이지수 계산
//		int cntPerPageGroup = 2; // 페이지그룹에 보여줄 페이지 목록 수 
//		
//		// currentPage가 1인 경우 2 3 4 5 6 
//		int startPage; 	//1		  1 3 3 5 4
//		int endPage; 	//2		  2 4 4 6 6
//		
//		if(endPage > totalPage) {
//			endPage = totalPage;
//		}
		
		PageGroup<Product> pg = new PageGroup<>(list, currentPage, totalCnt);
		
		return pg;
		
	} // findAll
	
	public Product findByProdNo(String prodNo) throws FindException {
		return repository.selectByProdNo(prodNo);
	} // findByProdNo
	
} // end class
