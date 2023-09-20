package com.my.customer.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class CustomerOracleMybatisRepository implements CustomerRepository {

	private SqlSessionFactory sqlSessionFactory;
	
	public CustomerOracleMybatisRepository() {
		
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			
			// sqlSessionFactory를 멤버변수로 만듦
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
 	
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
			Customer c = (Customer)session.selectOne("com.my.customer.customerMapper.selectById", id);
			
			if(c != null) {
				return c;
			} else {
				// select한 결과(id가 없다)가 null이면
				throw new FindException("");	
			} // if-else
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
		

		return null;
	} // selectById

	@Override
	public void insert(Customer c) throws AddException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.customer.customerMapper.insert", c);
			
			session.commit();
			
		} catch(Exception e) {
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
	} // insert

} // end class
