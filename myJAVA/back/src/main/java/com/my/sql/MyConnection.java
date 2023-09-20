package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyConnection {
	
	/**
	 * JDBC드라이버 로드 및 DB와 연결한다
	 * @return db와 연결하는 Connection객체를 반환한다.
	 * @throws Exception 드라이버클래스를 찾지 못하거나 DB연결 실패시 예외발생한다
	 */
	// DB와의 연결만 담당
	public static Connection getConnection() throws Exception{
		// JDBC 드라이버 로드 -> 라이브러리에 ojdbc를 넣어놔야 연결이 제대로 된다
		Class.forName("oracle.jdbc.OracleDriver");
		
		// DB와 연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		return DriverManager.getConnection(url, user, password);
		
		
//		String resource = "org/mybatis/example/mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory =
//		new SqlSessionFactoryBuilder().build(inputStream);
//		
//		SqlSession session = sqlSessionFactory.openSession();
//		return session.getConnection();
		
//		return null;
	} // getConnection()
	
	/**
	 * DB와 연결을 닫는다
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	// DB와 연결 해제만 담당
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		
		// DB연결 해제
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // rs
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // stmt
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // conn
		
	} // close
 
} // end class
