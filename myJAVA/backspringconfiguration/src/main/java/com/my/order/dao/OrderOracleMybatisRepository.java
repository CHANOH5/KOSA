package com.my.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository(value="orderDAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderOracleMybatisRepository implements OrderRepository {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	@Transactional(rollbackFor = AddException.class)
	public void insert(OrderInfo info) throws AddException {

		//같은 세션을 쓰도록
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();

			insertInfo(session,info.getOrderId());
			insertLine(session,info.getLines());
			
			// commit 수행하여 db에 저장
//			session.commit();
			
		} catch(Exception e) {
			// 에러 발생하면 rollback
//			session.rollback();
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

	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AddException.class)
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
