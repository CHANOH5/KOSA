package com.my.product.dao;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository(value = "productDAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOracleMybatisRepository implements ProductRepository {

	// 멤버변수
	@Autowired
	@Qualifier(value = "sqlSessionFactory84")
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {

		SqlSession session = null;
		
		// 반환타입이 list니까 담아서 반환할 list객체 생성
		List<Product> list = new ArrayList<>();
		
		try {
			session = sqlSessionFactory.openSession();
			// return되는 자료형이 (select 메서드의 sql문을 수행했을때 반환되는 값(행)이 한개인 경우) 
//			session.selectOne();
			// return되는 자료형이 (select 메서드의 sql문을 수행했을때 반환되는 값(행)이 여러개인 경우)
			Map<String, Integer> map = new HashMap<>();
			// 매개변수로 전달받은 값을 value값으로 넣음
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.product.ProductMapper.selectAll", map);
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally

	} // selectAll

	
	@Override
	public Integer selectCount() throws FindException {

		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			int count = session.selectOne("com.my.product.ProductMapper.selectCount");

			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		} // try-catch-finally

	} // selectCount
	

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {

		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession(); // Connection
			
			Product p = session.selectOne("com.my.product.ProductMapper.selectByProdNo", prodNo);
			
			if( p != null ) {
				return p;
			} else {
				throw new FindException("상품이 없습니다");
			}
			
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = MyConnection.getConnection();
//		} catch (Exception e) {
////			e.printStackTrace();
//			// db와 연결문제시 강제 FindException예외 발생 시키기
//			throw new FindException(e.getMessage());
//		}
//		
//		String selectByProdNoSQL = "SELECT *\r\n"
//				+ "		 FROM product\r\n"
//				+ "		 WHERE prod_no=?";
//		
//		try {
//			pstmt = conn.prepareStatement(selectByProdNoSQL);
//			pstmt.setString(1, prodNo);
//			rs = pstmt.executeQuery();		
//			
//			// 행이 존재하면 next메서드가 true를 반환, 존재하지 않으면 false를 반환
//			if(rs.next()) {
//				return new Product(rs.getString("prod_no"),
//									rs.getString("prod_name"),
//									rs.getInt("prod_price")
//									);
//			} else {  // 없으면
//				throw new FindException("상품이 없습니다.");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		} finally {
//			MyConnection.close(conn, pstmt, rs);
//		} // try-catch-finally
//	
	} // selectByProdNo
	
	
	//==================== 테스트 메서드========================

	public static void main(String[] args) {
		ProductOracleMybatisRepository repository = new ProductOracleMybatisRepository();
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
//		try {
//			System.out.println(repository.selectCount());
//		} catch(FindException e) {
//			throw new FindException(e.getMessage());
//		} // try-catch
		
		try {
			System.out.println(repository.selectByProdNo("C0001"));			
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch

	} // test-main

} // end class
