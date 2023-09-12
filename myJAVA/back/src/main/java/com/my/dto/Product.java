package com.my.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
 	private String prodNo;		// 상품 번호
	
	private String prodName;	// 상품 이름
	
	private Integer prodPrice;		// 상품 가격

	public boolean equals(String no) {
		return this.prodNo.equals(no);
	} //equals

	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	} // hashcode

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
	} // equals


} // end class
