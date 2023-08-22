import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JDBCtest {
	
	public static void selectTest() {
		
		// 1. JDBC 드라이버 설치
		// 2. 드라이버클래스들을 JVM에 로드
		
		try { /* 드라이버를 찾는 과정 */
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		} // try-catch
		
		// 3. DB와 연결 (oracle Developer에서 데이터베이스 만드는 것과 똑같은 의미)
		
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		// DriverManager클래스의 gettConnection 메서드 사용
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB와 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		// 4. SQL문 DB에 송신하기
//		Statement stmt = null;
//		PreparedStatement stmt = null;
		// 5. SQL문 결과 수신하기( selectSQL결과 반환 )
//		ResultSet rs = null;
		
		/*
		try {
			
			stmt = conn.createStatement();
			int dId = 60; // 부서번호
			String fn = "D";
			String selectSQL = 
					"SELECT employee_id, first_name, hire_date, salary "
					+ "FROM employees "
					+ "WHERE department_id =" + dId
					+ "AND SUBSTR(first_name, 1, 1) = '" + fn + "'";
			
			rs = stmt.executeQuery(selectSQL); // selectSQL을 송신하는 메서드
			while(rs.next()) { // Moves the cursor ( sql결과의 행을 이동시키며 보여줌)
				int eId = rs.getInt("employee_id");
				String eName = rs.getString("first_name");
				Date ehdt = rs.getDate("hire_date");
				int eSal = rs.getInt("salary");
				
				System.out.println(eId + ":" + eName + ":" + ehdt + ":" + eSal);	
			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
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
				
		} // try-catch-finally
		*/

		
//	------------------------------------------------
		
		// Statement는 지저분해져서 PreparedStatement 사용하기 -> 미리 sql문 준비해야함
		
		// 4. SQL문 DB에 송신하기
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		// 5. SQL문 결과 수신하기( selectSQL결과 반환 )
		ResultSet rs = null;
		
		String selectSQL =  					
				"SELECT employee_id, first_name, hire_date, salary "
				+ "FROM employees "
				+ "WHERE department_id = ?"
				+ "AND SUBSTR(first_name, 1, 1) = ?";
		
		try {
			
			pstmt = conn.prepareStatement(selectSQL); // 먼저 송신
			pstmt.setInt(1, 60); // 첫번째 ?에 대한 값 (바인드변수)
			pstmt.setString(2, "D"); // 두번째 ?에 대한 값 (바인드변수)
			rs = pstmt.executeQuery(); // 매개변수없는 executeQuery 메서드
			
			while(rs.next()) { // Moves the cursor ( sql결과의 행을 이동시키며 보여줌)
				int eId = rs.getInt("employee_id");
				String eName = rs.getString("first_name");
				Date ehdt = rs.getDate("hire_date");
				int eSal = rs.getInt("salary");
				
				System.out.println(eId + ":" + eName + ":" + ehdt + ":" + eSal);	
			} // while
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // rs
			
			if(pstmt != null) {
				try {
					pstmt.close();
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
				
		} // try-catch-finally
		
	}
	
	public static void insertTest() {
		
		// 2. JDBC 드라이버 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		// 3. DB와 연결
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.1.21:1521:xe"; // 교수님 db로 접속
		String user = "hr";
		String password = "hr"; 
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB와 연결 성공 :) ");
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch
		
		// 4. SQL구문 송신
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO customer(id, pwd, name) VALUES (?,?,?)";
		
		try {
			
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, "seng"); // 첫번째 바인드값인 id값
			pstmt.setString(2, "na");
			pstmt.setString(3, "gg");
			
			// executeUpdate메서드는 int값으로 반환한다!!!
			// 처리된 행 수를 반환한다!!!!!
			int rowcnt = pstmt.executeUpdate(); // DDL,DML 작업할때 사용하는 메서드 executeUpdate();
			System.out.println(rowcnt + "건 추가 성공");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // pstmt
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // pstmt
			
		} // try-catch-finally
		
	} // insertTest()

	public static void main(String[] args) {

		// selectTest();
		insertTest();
		
	} // main

} // endclass
