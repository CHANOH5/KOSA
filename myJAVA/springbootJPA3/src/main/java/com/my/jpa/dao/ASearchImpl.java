package com.my.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.my.jpa.entity.A;
import com.my.jpa.entity.QA;
import com.querydsl.jpa.JPQLQuery;

public class ASearchImpl extends QuerydslRepositorySupport implements ASearch{
	
	public ASearchImpl() {
		super(A.class);
	}

	@Override
	public List<A> search1() {
		
		QA qa = QA.a;
		
		JPQLQuery<A> query = from(qa);
		query.where(qa.a_1.eq("two")); // WHERE a_1='one'
		// qa.a_1과 같은 행들을 찾아오고 one과 같은 것들을 찾아옴
		
		List<A> list = query.fetch();

		return null;
	} // search1
	
	@Override
	public List<A> search2(int a_2) {
		
		QA qa = QA.a;
		
		JPQLQuery<A> query = from(qa);		// SELECT * FROM a_tbl
		
		// WHERE a_2 >= 3
		query.where(qa.a_2.goe(a_2));
		
		return query.fetch(); // 리턴

	}
	
}
