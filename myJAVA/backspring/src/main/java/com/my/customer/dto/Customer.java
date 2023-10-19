package com.my.customer.dto;

import lombok.Data;

@Data
public class Customer extends Person {
	private static final long serialVersionUID = 1L;
	
	//필드
	private String id;
	transient private String pwd;
	
	// 생성자
	public Customer() {
		super();
	}
	
	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public Customer(String id, String pwd, String name, String address) {
//		this.id = id;
//		this.pwd = pwd;
		this(id, pwd);
		this.name = name;
		super.address = address;
//		super(name, address);
	}

	
} // end class
