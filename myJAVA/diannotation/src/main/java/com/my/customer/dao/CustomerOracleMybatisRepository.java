package com.my.customer.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository(value="customerDAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOracleMybatisRepository implements CustomerRepository {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Customer selectById(String id) throws FindException {

//		String resource = "org/mybatis/example/mybatis-config.xml";
//		InputStream inputStream; 생성자로 옮김
		SqlSession session = null;
		
		try {
			
//			inputStream = Resources.getResourceAsStream(resource); 생성자로 옮김
//			SqlSessionFactory sqlSessionFactory =
//			new SqlSessionFactoryBuilder().build(inputStream);
			
			session = sqlSessionFactory.openSession();
			// selectOne의 인자를 mapper의 namespace 부분과
			// select id를 넣음
			Customer c = (Customer)session.selectOne("com.my.customer.CustomerMapper.selectById", id);
			
			if(c != null) {
				return c;
			} else {
				// select한 결과(id가 없다)가 null이면
				throw new FindException("고객없음");	
			} // if-else
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally

	} // selectById

	@Override
	public void insert(Customer c) throws AddException {
	
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.customer.CustomerMapper.insert", c);
			
			session.commit();
		} catch (Exception e) {
			session.rollback();
			
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			
			if(session != null) {
				session.close();
			} // if
			
		} // try-catch-finally
		
	} // insert()

} // end class
