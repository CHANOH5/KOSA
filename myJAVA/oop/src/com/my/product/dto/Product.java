package com.my.product.dto;

import java.util.Objects;

public class Product {
	
 	private String prodNo;		// 상품 번호
	
	private String prodName;	// 상품 이름
	
	private int prodPrice;		// 상품 가격
	
	
	
	public Product(){}
	public Product(String prodNo, String prodName){
//		this.prodNo = prodNo;
//		this.prodName = prodName;		
		this(prodNo, prodName, 0);
	}
	public Product(String prodNo, String prodName, int prodPrice){
		if(prodNo.length() != 5) {
			System.out.println("상품번호는 5자리이어야합니다");
			
		}else {
			this.prodNo = prodNo;
			this.prodName = prodName;
			this.prodPrice = prodPrice;
		}
	}
	
//	public boolean equals(Product p) {
//		return this.prodNo.equals(p.prodNo);
//	}
	
	public boolean equals(String no) {
		return this.prodNo.equals(no);
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj == null) {
//			return false;
//		}
//		if(obj instanceof Product) {
//			Product product = (Product)obj;
//			if(this.prodNo.equals(product.prodNo)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	public void setProdNo(String prodNo, String prodName, int pordPrice) {
		this.prodNo = prodNo;			
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Class currentClass = this.getClass();
		Class paramClass = obj.getClass();
		if(currentClass != paramClass) {
			return false;
		}
		Product other = (Product) obj;
		return Objects.equals(prodNo, other.prodNo);
	}

	public String getProdNo() {
		return prodNo;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public int getProdPrice() {
		return prodPrice;
	}

}
