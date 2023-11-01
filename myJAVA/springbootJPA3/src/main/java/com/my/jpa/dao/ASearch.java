package com.my.jpa.dao;


import java.util.List;

import com.my.jpa.entity.A;

public interface ASearch {		
	/**	 * 전체 A Entity들을 Qdsl로 검색한다	 
	* @return 	 
	*/	
	List<A> search1();		
	/**
	 * SELECT * FROM a_tbl WHERE a_2 >= 3 sql문을 만듦
	* @param a_2   ex)3
	* @return	 
	*/	
	List<A> search2(int a_2);
}