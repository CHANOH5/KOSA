package com.my.order.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderInfo {
	
	private Integer orderNo;
	private String orderId;
	private Date orderDt;
	private List<OrderLine> lines;

} // end class
