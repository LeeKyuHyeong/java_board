package board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao { // DAO(Data Access Object)
	//모델 1이라고 부른다 DAO 한개 추가한것을
	private static MemberDao memberDao = null;
	
	public static MemberDao getInstance() {		//싱글톤 패턴으로 계속 새로 생성하는걸 방지(메모리 절약)
		if(memberDao == null) {
			memberDao = new MemberDao();
		}
		return memberDao;
	}
	private MemberDao() {

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
	
	public int checkID(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = -1;
		
		String sql = "SELECT count(*) as cnt FROM TBL_MEMBER where id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt, rs);
		}
		return result;
	}
	
	public boolean registerMember(MemberDto memberDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		String sql = "INSERT INTO tbl_member(id, password, name, birth, phone, zipcode, address1, address2) ";
			  sql += "values(?,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getBirth());
			pstmt.setString(5, memberDto.getPhone());
			pstmt.setString(6, memberDto.getZipcode());
			pstmt.setString(7, memberDto.getAddress1());
			pstmt.setString(8, memberDto.getAddress2());
			
			if(pstmt.executeUpdate() > 0 ) {
				result = true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt);
		}
		return result;
	}
	
	public MemberDto getUser(MemberDto memberDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDto userInfo = null;
		String sql = "SELECT id, name FROM tbl_member WHERE id =? AND password =?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userInfo = new MemberDto();
				userInfo.setId(rs.getString("id"));
				userInfo.setName(rs.getString("name"));				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt, rs);			
		}
		
		return userInfo;
	}
}
