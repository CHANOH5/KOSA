package com.my.jpa.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.A;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ARepositoryTest {

	@Autowired
	ARepository r;
	
	@Test
	void test() {
		// 인터페이스는 직접 객체 생성할 수 없기 때문에
		// 클래스이름 확인해보기
		log.error(r.getClass().getName());
	}
	
	@Test 
	@Transactional
	@Commit
	void testSave(){
		A a = new A();
		a.setA_1("one");
		a.setA_2(new BigDecimal(1.0));
		a.setA4("a4");
		r.save(a);
		
//		A a1 = new A();
//		a1.setA_1("one");
//		a1.setA_2(new BigDecimal(2.0));
//		r.save(a1);
		
	} // testSave()
	
	@Test
	void testFindById() {
		
		String a_1 = "one";
		Optional<A> optA = r.findById(a_1);
		
		Assertions.assertTrue(optA.isPresent()); // 있는지 확인
		
		A a = optA.get(); // 객체 변수에 대입
		
		log.error("a_1{}, a_2{}", a.getA_1(), a.getA_2());
		
	} // testFindById
	
	@Test
	void testUpdate() {
		
		String a_1 = "one";
		Optional<A> optA = r.findById(a_1);
		
		Assertions.assertTrue(optA.isPresent()); // 있는지 확인
		
		A a = optA.get(); // 객체 변수에 대입
		// 여기에 변경할 값을 설정
		a.setA_2(new BigDecimal(3.0));
		r.save(a);
		
	} // testUpdate
	
	@Test
	@Transactional
	@Commit
	void testDeleteById() {
		
		String a_1 = "one";	
		r.deleteById(a_1); // entity가 없으면 silently signore -> rollback 예외발생

	} // testDelete
	
	@Test
	@Transactional
	@Commit
	void testDelete() {
		
		String a_1 = "one";	
//		A a = new A();
//		a.setA_1(a_1);
//		r.delete(a); // entity가 없으면 rollback 예외 발생 안함
		
		Optional<A> aOpt = r.findById(a_1);
		aOpt.ifPresent(a -> r.delete(a));
	
	} // testDelete
	
	@Test
	void testQueryMethod() {
		
		// 기본제공되는 findById()와 findAll()가 아닌 직접 메소드 만들어서 사용 가능
		// ARepository의 선언부에 선언하기
		List<A> list = r.findByA4("one");
		// log.error(list); => 오류남. list 자료형이기 때문에 아래처럼 포맷팅 해줘야함
		log.error("r.findByA4(a4one)결과 : {}", list);
	}
	
	@Test
	void test6FindByA4Like() {
		String word = "%4f%";
		
		List<A> list = r.findByA4Like(word);
		
		log.error("r.findByA4Like(4f)의 결과: {}", list);
		
	} // test6FindByA4Like
	
	@Test
	void testFindByA4LikeOrderByA4() {
		String word = "%4f%";
		List<A> list = r.findByA4LikeOrderByA4(word);
		log.error("r.findByA4LikeOrderByA4(4f)결과 : {}", list);
	} // testFindByA4Like
	
	@Test
	void testFindByA4LIKE2Jpql() {
		String word = "%4f%";
		List<A> list = r.findByA4LIKE2Jpql(word);
		log.error("r.findByA4LIKE2Jpql(4f)결과 : {}", list);
	} // testFindByA4Like
	
	@Test
	void testFindByA4LIKEJpqlA1A4() {
		String word = "%4f%";
		List<Object[]> list = r.findByA4LIKEJpqlA1A4(word);
		
		for(Object[] arr : list) {
			
			log.error("r.findByA4LIKE2Jpql(4f)결과 : {}, {}", arr[0], arr[1]);
		}
		
	} // testFindByA4Like
	
	@Test
	@Transactional
	@Commit
	void testModify() {
		String a_1 = "one";
		BigDecimal a_2 = new BigDecimal(888);
		int rowcnt = r.modify(a_1, a_2);
		
		log.error("수정된 행 수 : {}", rowcnt);
	}
	
} // end class
