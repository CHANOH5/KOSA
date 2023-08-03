package com.my.product.dao;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
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
	
	public Product selectByProdNo(String prodNo) throws FindException {
	
		// 강사님 코드
		for(int i = 0; i < totalCnt; i++) {
			
			Product savedP = products[i];  // 이미 저장된 상품 
			
			if(savedP.getProdNo().equals(prodNo)) {
				return savedP;
			} // if
			
		} // for
		
//		return null;
		throw new FindException("상품이 없습니다.");
		
	} // selectByNo
	
	public Product[] selectAll() throws FindException {

		// 강사님 코드
		if( totalCnt == 0 ) {
//			return null;
			throw new FindException("상품이 한개도 없습니다.");
		}
		
		Product[] all = new Product[totalCnt];
		
		for(int i = 0; i < totalCnt; i++) {
			all[i] = products[i];
		}
		
		return all;
		
	} // selectAll()

	@Override
	public void update(Product p) throws ModifyException {
		
		if ( p == null ) {
			throw new ModifyException("변경할 상품이 없습니다.");
		}
		
		for(int i=0; i<totalCnt; i++) {
			if(products[i].getProdNo().equals(p.getProdNo())) {
				products[i].setProdName(p);
				products[i].setProdPrice(p);
			} //if
		} // for
		
	} // update

	@Override
	public void delete(String prodNo) throws RemoveException {
		
		products[totalCnt] = prodNo;
		totalCnt--;
		
	}

} // end class
