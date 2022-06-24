package board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDao { // DAO(Data Access Object)
	//모델 1이라고 부른다 DAO 한개 추가한것을
	private static BoardDao boardDao = null;
	
	public static BoardDao getInstance() {		//싱글톤 패턴으로 계속 새로 생성하는걸 방지(메모리 절약)
		if(boardDao == null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}
	private BoardDao() {

		try { Class.forName("oracle.jdbc.OracleDriver"); }		// 오라클이 존재한다면 
		catch (Exception e) { e.printStackTrace(); } 

	}
	
	private Connection getConnection() throws Exception {

		return DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "oj", "oj"); // 연결객체 넣어준다;
	}
	
	
	private void dbClose(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		// 얻어온 자원 역순으로 닫아준다.
		if (rs != null) try { rs.close(); } catch (Exception e) {}
		if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
		if (conn != null) try { conn.close(); } catch (Exception e) {}
	}
	
	private void dbClose(Connection conn, PreparedStatement pstmt) {
		// 얻어온 자원 역순으로 닫아준다.
		if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
		if (conn != null) try { conn.close(); } catch (Exception e) {}
	}
	
	
	
}
