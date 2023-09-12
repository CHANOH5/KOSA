package com.my.dao;

import java.lang.module.FindException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.Product;
import com.my.sql.MyConnection;

public class ProductOracleRepository implements ProductRepository {

	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// DB연결
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} // try-catch

		String selectAllSQL = 
						"SELECT *\r\n"
				+ "		FROM ( SELECT rownum rn, a.*\r\n"
				+ "		           FROM (SELECT *\r\n"
				+ "		                 FROM product\r\n"
				+ "		                 ORDER BY prod_no\r\n"
				+ "		                ) a\r\n"
				+ "		      )\r\n"
				+ "		WHERE rn BETWEEN ? AND ?";
		
		List<Product> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(selectAllSQL);
			// ?에 대한 값 설정
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			// ResultSet에 결과 삽입
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				// 한행의 정보 꺼내오기 
				String prodNo = rs.getString("PROD_NO"); // 상품번호
				String prodName = rs.getString("PROD_NAME"); // 상품이름
				int prodPrice = rs.getInt("PROD_PRICE"); // 상품가격
				
				// 행 하나하나에 대한 정보를 Product에 대입
				Product p = new Product(prodNo, prodName, prodPrice);
				
				// 이 정보를 List에 넣기
				list.add(p);
			} // while
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			// 연결해제
			MyConnection.close(conn, pstmt, rs);
		} // try-catch-finally

	} // selectAll
	
	// 테스트하기 
	
	@Override
	public Integer selectCount() throws FindException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// db와 연결
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} // try-catch
		
		// db와 연결이 되면 아래 sql문 수행
		String selectCountSQL = "SELECT COUNT(*)\r\n"
				+ "FROM product";
		
		try {
			pstmt = conn.prepareStatement(selectCountSQL);
			
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1); // 전체상품 수 

		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		} // try-catch-finally

	} // selectCount
	
	
	//==================== 테스트 메서드========================

	public static void main(String[] args) {
		ProductOracleRepository repository = new ProductOracleRepository();
//		int startRow = 2;
//		int endRow = 4;
//		
//		try {
//			List<Product> list = repository.selectAll(startRow, endRow);
//			System.out.println(list);
//		} catch (FindException e) {
//			e.printStackTrace();
//		} // try-catch
		
		// 전체 상품 수 테스트
		try {
			System.out.println(repository.selectCount());
		} catch(FindException e) {
			e.printStackTrace();
		}
	} // test-main

} // end class
