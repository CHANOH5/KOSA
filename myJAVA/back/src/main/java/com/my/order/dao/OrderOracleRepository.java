package com.my.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.sql.MyConnection;

public class OrderOracleRepository implements OrderRepository {

	@Override
	public void insert(OrderInfo info) throws AddException {
		
		Connection conn = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		
		try {
			insertInfo(conn, info.getOrderId());
			insertLine(conn, info.getLines());
		} finally {
			MyConnection.close(conn, null, null);
		} // try-finally
		
	} // insert
	
	public void insertInfo(Connection conn, String id) throws AddException{
		
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO order_info(order_no, order_id, order_dt)"
				+ "VALUES (order_seq.NEXTVAL, ?, SYSDATE)";
	
		
		try {
			pstmt = conn.prepareStatement(insertInfoSQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null);
		} // try-catch-finally
		
	} // insertInfo
	
	public void insertLine(Connection conn, List<OrderLine> lines) throws AddException {
		
		PreparedStatement pstmt = null;
		
		String insertLineSQL = "INSERT INTO order_line(order_line_no, order_prod_no, order_quantity)\r\n"
				+ "VALUES (order_seq.CURRVAL, ?,  ?)";
		
		try {
			pstmt = conn.prepareStatement(insertLineSQL);
			
			for(OrderLine line : lines) {
				String prodNo = line.getOrderP().getProdNo();
				int quantity = line.getOrderQuantity();
				pstmt.setString(1, prodNo);
				pstmt.setInt(2, quantity);
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MyConnection.close(null, pstmt, null);
		} // try-catch-finally
		
	} // insertLine

	public List<OrderInfo> selectById(String orderId) throws FindException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyConnection.getConnection();
			String selectByIdSQL = "SELECT order_no, order_id, order_dt,"
					+ "          order_quantity,"
					+ "          p.prod_no, prod_name,prod_price"
					+ "FROM order_info info JOIN order_line line ON ( info.order_no = line.order_line_no)"
					+ "                              JOIN product p ON (line.order_prod_no = p.prod_no)"
					+ "WHERE order_id = ?"
					+ "ORDER BY order_dt DESC";
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, orderId);
			rs = pstmt.executeQuery();
			
			// 주문 (기본)정보들이 담길 list
			List<OrderInfo> list = new ArrayList<>();
			
			// 주문번호가 1부터 시작할 것이기 때문에 db에 저장된 다른 주문번호를 셋팅
			int oldOrderNo = 0; 
			
			// 주문 (상세)정보들이 담길 line
			List<OrderLine> lines = null;
			
			while(rs.next()) {
				// 행 하나 이동하면서 주문번호 획득
				int orderNo = rs.getInt("order_no");
				
				// 첫행일 경우 orderNo와 oldOrderNo와 같을 수 없다
				// 즉 첫 행이거나 주문번호가 바뀔때 라는 뜻
				if(oldOrderNo != orderNo) {	
					
					OrderInfo info = new OrderInfo();
					info.setOrderNo(orderNo); // 주문번호
					info.setOrderId(orderId); // 주문아이디
					info.setOrderDt(rs.getDate("order_dt")); // 주문날짜
					
					lines = new ArrayList<>(); 
					info.setLines(lines); // setLines의 인자로 lines를 넣어서 리스트 세팅
					// 아직 orderLine(주문상세)의 행의 정보를 세팅하지 않음
					// orderInfo에 넣어둠
					list.add(info);
					
					oldOrderNo = orderNo;
					// 첫행의 주문번호로 바뀜 0->5
				}
				
				OrderLine line = new OrderLine();
				line.setOrderLineNo(orderNo);
				Product p = new Product();
				// line에 세팅
				line.setOrderP(p);
				
				// line의 상품에 대한 정보 세팅
				p.setProdNo(rs.getString("prod_no"));
				p.setProdName(rs.getString("prod_name"));
				p.setProdPrice(rs.getInt("prod_price"));
				
				line.setOrderQuantity(rs.getInt("order_quantity"));
				
				// lines에 line을 세팅
				lines.add(line);
				// 여기까지 1행
				
				// oldOrderNo가 0->5로 바뀌었으니 
				// if조건에 만족하지 않아서 if문을 돌지않고
				// 바로 136행부터 수행
				// 이렇게해서 lines를 채움
				// 첫행은 아니지만 주문번호가 달라지면 115행 새로운 orderInfo타입의 객체를 생성
				// 다시 if문조건 사용하여 orderInfo객체가 새로 생성	
				
			} // while
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		} // try-catch-finally
		
	} // selectById
	
} // end class
