package com.my.product.dao;
import com.my.exception.AddException;
import com.my.product.dto.Product;

public class ProductDAOArray implements ProductDAOInterface {
	
	private Product[] products = new Product[5]; // 상품 저장소
	
	private int totalCnt = 0; // 저장된 상품 수
	
	public void insert(Product product) throws AddException {

//		if(totalCnt == products.length) {
//			
//			System.out.println("저장소가 꽉찼습니다");
//			
//		} else {
		
		for(int i=0; i<totalCnt; i++) {
			if(products[i].getProdNo().equals(product.getProdNo())) {
//				System.out.println("이미 존재하는 상품입니다.");
//				return;
				throw new AddException("이미 존재하는 상품입니다.");
			}
		}

		try {
			products[totalCnt] = product;
			totalCnt++;
		} catch(Exception e) {
//			System.out.println("저장소가 꽉 찼습니다. 저장된 상품수: " + totalCnt);
			// 강제로 예외 발생
			throw new AddException("저장소가 꽉 찼습니다. 저장된 상품수: " + totalCnt);
		}

//		}
		
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
