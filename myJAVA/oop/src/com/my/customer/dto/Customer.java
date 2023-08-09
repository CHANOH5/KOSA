package com.my.customer.dto;

import java.io.Serializable;

public class Customer extends Person {
	
	/**
	 * 
	 */
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

	// 메서드
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String toString() {
		return "아이디 : " + id + ", 비밀번호 : " + pwd + ", 이름 : " + name + ", 주소 : " + address;
	}
	
} // end class
