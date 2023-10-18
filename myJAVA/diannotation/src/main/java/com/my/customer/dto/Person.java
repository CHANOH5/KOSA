package com.my.customer.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// 필드
	protected String name;
	protected String address;
	
	// 생성자
	public Person() {
		super();
	}	
	public Person(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}


} // end class
