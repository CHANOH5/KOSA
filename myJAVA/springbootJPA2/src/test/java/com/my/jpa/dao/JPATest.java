package com.my.jpa.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.A;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class JPATest {

	@PersistenceContext // entity에 직접 접근 -> JPA를 직접 쓰는것
	EntityManager em;  
	
	@Test
	@Transactional
	@Commit
	void test() {
		A a = new A();
		a.setA_1("two");
		a.setA_2(new BigDecimal(2));
		em.persist(a);
		em.flush();
		em.clear();
		
	} // test
	
} // end class
