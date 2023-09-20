package com.my.order.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

public class OrderOracleMybatisRepository implements OrderRepository {

	private SqlSessionFactory sqlSessionFactory;
	
	public OrderOracleMybatisRepository() {
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		try {
			
			inputStream = Resources.getResourceAsStream(resource);
			
			// sqlSessionFactory를 멤버변수로 만듦
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	} // 생성자
	
	@Override
	public void insert(OrderInfo info) throws AddException {

		//같은 세션을 쓰도록
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();

			insertInfo(session,info.getOrderId());
			insertLine(session,info.getLines());
			
			// commit 수행하여 db에 저장
			session.commit();
			
		} catch(Exception e) {
			// 에러 발생하면 rollback
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-finally
		
	} // insert
	
	public void insertInfo(SqlSession session, String id) throws AddException{
		try {
			session.insert("com.my.order.orderMapper.insertInfo", id);			
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
	} // insertInfo

	
	public void insertLine(SqlSession session, List<OrderLine> lines) throws AddException {
		
		try {
			for(OrderLine line : lines) {
				session.insert("com.my.order.orderMapper.insertLine", line);
			} // for
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage()); //
		} // try-catch
		
	} // insertLine
	
//	public List<OrderInfo> selectById(String orderId) throws FindException{
//		
//		SqlSession session = null;
//		
//		
//		
//	}

	public List<OrderInfo> selectById(String orderId) throws FindException{
		SqlSession session = null;
		try {
			
			List<OrderInfo> list = new ArrayList<>();
			session = sqlSessionFactory.openSession();
			
			list = session.selectList("com.my.order.orderMapper.selectById", orderId);
			
			System.out.println("주문 기본(OrderInfo) 객체 수: " + list.size());
			for(OrderInfo info : list) {
				System.out.println(info.getOrderNo() + " : " + info.getOrderDt());
				for(OrderLine line : info.getLines()) {
					System.out.println("주문 상세: 상품 번호 - " + line.getOrderP().getProdNo() + ", 수량 - " + line.getOrderQuantity());
				} // inner-for
				System.out.println(" --------------------- ");
			} // outer-for
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally
		
	} // selectById
	
} // end class
