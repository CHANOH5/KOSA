//package com.my.customer.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.my.customer.dto.Customer;
//import com.my.exception.FindException;
//import com.my.sql.MyConnection;
//
//public class CustomerOracleRepository implements CustomerRepository {
//
//	@Override
//	public Customer selectById(String id) throws FindException {
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		// DB연결
//		try {
//			conn = MyConnection.getConnection();
//		} catch (Exception e) {
//			throw new FindException(e.getMessage());
//		} // try-catch
//		
//		
//		String selectByIdSQL = "SELECT * "
//				+ "FROM customer "
//				+ "WHERE id=?";
//		
//		try {
//			// db서버로 sql문을 송신
//			pstmt = conn.prepareStatement(selectByIdSQL);
//			// ?값(바인드변수)을 설정
//			pstmt.setString(1, id);
//			
//			// 설정한 바인드 변수값 송신
//			rs = pstmt.executeQuery();
//
//			// 최대 반환 행 수가 1개행이기 때문에 while반복문을 돌 이유가 없다
//			if(rs.next()) {
//				return new Customer(
//						// 행이 존재하면 Customer 객체를 생성해서 반환
//						id,
//						rs.getString("PWD"),
//						rs.getString("NAME"),
//						null
//						);
//			}else {
//				throw new FindException("고객이 없습니다");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			MyConnection.close(conn, pstmt, rs);
//		} // try-catch-finally
//		
//		return null;
//		
//	} // selectById
//
//} // end class
