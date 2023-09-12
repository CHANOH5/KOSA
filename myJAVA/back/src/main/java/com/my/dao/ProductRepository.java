package com.my.dao;

import java.lang.module.FindException;
import java.util.List;

import com.my.dto.Product;

public interface ProductRepository {

	/**
	 * 상품번호로 오름차순 정렬된 상품들 중 시작행에서 부터 끝행까지 상품을 검색한다
	 * @param startRow 시작행
	 * @param endRow 끝행
	 * @return 상품들
	 * @throws FindException - DB와의 연결에 실패한 경우, 
	 */
	List<Product> selectAll(int startRow, int endRow) throws FindException;

	/**
	 * 전체 상품수를 검색한다
	 * @return 전체상품수
	 * @throws FindException - DB와의 연결 실패한 경우 예외발생
	 */
	Integer selectCount() throws FindException;
	
} // end class
