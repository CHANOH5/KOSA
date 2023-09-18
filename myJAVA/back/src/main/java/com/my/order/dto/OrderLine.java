package com.my.order.dto;

import com.my.dto.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderLine {

	private Integer orderLineNo;
//	private String orderProdNo;
	private Product orderP;
	private Integer orderQuantity;
	
} // end class
