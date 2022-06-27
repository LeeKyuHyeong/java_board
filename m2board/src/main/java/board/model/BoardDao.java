package board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

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
	
	public boolean insertBoard(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO tbl_board(no,title,content,id) VALUES(seq_board.nextval, ?, ?, ?)";
		
		boolean result = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setString(3, boardDto.getMemberDto().getId());
			
			if(pstmt.executeUpdate() > 0) {
				result = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt);
		}
		return result;
	}
	
	public List<BoardDto> getBoardList(long startnum, long endnum){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardDto> list = new ArrayList<>();
		
//		String sql = "SELECT b.no, b.title, b.id, b.regdate, b.readcount, m.name "
//				+ "FROM tbl_board b join tbl_member m "
//				+ "ON b.id = m.id "
//				+ "ORDER BY no DESC ";
		String sql = 
				"SELECT B.* " +
		        "FROM (SELECT rownum AS rnum, A.* " +
		              "FROM (SELECT b.no, b.title, m.id, " + 
		                           "case when to_char(b.regdate, 'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD') " + 
		                               "then to_char(b.regdate, 'HH24:MI:SS') " +
		                               "else to_char(b.regdate, 'YYYY-MM-DD') end AS regdate, b.readcount, m.name " + 
		                    "FROM tbl_board b join tbl_member m " +
		                    "ON b.id = m.id " +
		                    "ORDER BY no DESC) A) B " +
		        "WHERE ?<=rnum AND rnum<=? ";
		
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, startnum);
			pstmt.setLong(2, endnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
				
				BoardDto boardDto = new BoardDto();
				boardDto.setNo(rs.getLong("no"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setRegdt(rs.getString("regdate"));
				boardDto.setReadcnt(rs.getInt("readcount"));
				boardDto.setMemberDto(memberDto);
				
				list.add(boardDto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt, rs);
		}
		
		return list;
	}
	public BoardDto getBoardView(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		String sql = "SELECT b.no, b.title, b.id, b.regdate, b.readcount, b.content, m.name "
				+ "FROM tbl_board b join tbl_member m "
				+ "ON b.id = m.id "
				+ "WHERE b.no = ? ";
		
		BoardDto boardDto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setName(rs.getString("name"));
				memberDto.setId(rs.getString("id"));
				
				boardDto = new BoardDto();
				
				boardDto.setNo(rs.getLong("no"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setRegdt(rs.getString("regdate"));
				boardDto.setReadcnt(rs.getInt("readcount"));
				boardDto.setMemberDto(memberDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt, rs);
		}
		
		return boardDto;
	}
	public boolean updateBoard(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE tbl_board SET title = ?, content = ? WHERE no = ? AND id = ?";
		
		boolean result = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setLong(3, boardDto.getNo());
			pstmt.setString(4, boardDto.getMemberDto().getId());
						
			if(pstmt.executeUpdate() > 0) {
				result = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt);
		}
		return result;
	}
	
	
	public boolean updateReadcnt(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE tbl_board SET readcount = readcount + 1 WHERE no = ? ";
		
		boolean result = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			if(pstmt.executeUpdate() > 0) {
				result = true;				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt);
		}
		
		return result;
	}
	public boolean deleteBoard(Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE tbl_board WHERE no = ? ";
		
		boolean result = false;
			
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			if(pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			dbClose(conn, pstmt);
		}
		
		return result;
	}
	public long getRecordCnt() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT count(*) AS cnt FROM tbl_board ";
		long result = 0L;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getLong("cnt");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, pstmt, rs);
		}
		
		return result;
	}
	
}
