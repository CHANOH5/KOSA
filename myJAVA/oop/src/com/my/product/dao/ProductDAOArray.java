package com.my.product.dao;
import com.my.product.dto.Product;

public class ProductDAOArray implements ProductDAOInterface {
	
	private Product[] products = new Product[5]; // 상품 저장소
	
	private int totalCnt = 0; // 저장된 상품 수
	
	public void insert(Product product) {

		if(totalCnt == products.length) {
			
			System.out.println("저장소가 꽉찼습니다");
			
		} else {
			
		products[totalCnt] = product;
		totalCnt++;
		
		}
		
	} // insert
	
	public Product selectByProdNo(String prodNo) {
	
		// 강사님 코드
		for(int i = 0; i < totalCnt; i++) {
			
			Product savedP = products[i];  // 이미 저장된 상품 
			
			if(savedP.getProdNo().equals(prodNo)) {
				return savedP;
			} // if
			
		} // for
		
		return null;
	} // selectByNo
	
	public Product[] selectAll() {

		// 강사님 코드
		if( totalCnt == 0 ) {
			return null;
		}
		
		Product[] all = new Product[totalCnt];
		
		for(int i = 0; i < totalCnt; i++) {
			all[i] = products[i];
		}
		
		return all;
		
	} // selectAll()

} // end class
