package com.my.customer.dto;

import java.io.Serializable;

public class Person implements Serializable{
	
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
	
	// 메서드
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

} // end class
