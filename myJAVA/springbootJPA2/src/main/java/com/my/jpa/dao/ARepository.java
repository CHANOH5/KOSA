package com.my.jpa.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.my.jpa.entity.A;

public interface ARepository extends CrudRepository<A, String> {
	
	//쿼리메서드작성 가능 : findBy멤버변수명, 반환형은 List자료형
	List<A> findByA4(String a4);
	
	/**
	 * 단어를 포함한 a4멤버변수를 갖는 엔티티객체들을 반환한다
	 * @param word 단어
	 * @return 
	 */
	List<A> findByA4Like(String word); // SELECT FROM WHERE a4 LIKE '%?%'

	List<A> findByA4LikeOrderByA4(String word); // WHERE a4 LIKE
	
	
	//JPQL문법을 사용 -> JPA용 전용 쿼리
//	@Query("SELECT p FROM 엔티티클래스 AS p WHERE 멤버변수 LIKE %:word%")
//	@Query("SELECT a FROM A AS a WHERE a4 LIKE %:word%")
//	@Query(value="SELECT * FROM 테이블", nativeQuery = true)
//    public List<A> findByName(@org.springframework.data.repository.query.Param("word") String name);
	
	// JPQL문법에서는 * 을 못쓰기 때문에 AS로 별칭을 주어서 사용해야함
//	@Query("SELECT a FROM A AS a WHERE a4 LIKE %:word%")
	@Query(value="SELECT * FROM a_tbl WHERE a4 LIKE :word",
			nativeQuery = true)
	public List<A> findByA4LIKE2Jpql(String word);
	
	// 특정 컬럼만 검색해오는 메서드는 A타입으로 return받을 수 없고
	// return 자료형을 Object타입으로 해야한다
	@Query(value="SELECT a_1, a4 FROM a_tbl WHERE a4 LIKE :word",
			nativeQuery = true)
	public List<Object[]> findByA4LIKEJpqlA1A4(String word);
	
	@Modifying
	@Query(value="UPDATE a_tbl SET a_2=:a_2 WHERE a_1=:a_1", nativeQuery = true)
	public int modify(String a_1, BigDecimal a_2);
	
	
}
