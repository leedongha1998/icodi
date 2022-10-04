package com.kh.icodi.codiBoard.model.dao;

import static com.kh.icodi.common.JdbcTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.icodi.codiBoard.model.dto.CodiBoardExt;
import com.kh.icodi.codiBoard.model.dto.LikeThat;
import com.kh.icodi.codiBoard.model.exception.CodiBoardException;
import com.kh.icodi.member.model.dto.Member;

public class CodiBoardDao {
	private Properties prop = new Properties();
	
	public CodiBoardDao() {
		String filename = CodiBoardDao.class.getResource("/sql/codiboard/codiboard-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 최신 코디 컬럼 수 조회
	// getTotalContentNewCodi = select count(*) from codi_board
	public int getTotalContentNewCodi(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentNewCodi");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new CodiBoardException("최신 코디게시판 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	
	// 코디 게시판 조회
	// findCodiBoard = select * from ( select row_number () over (order by reg_date desc) rnum, b.*, (select member_id from like_that where codi_board_no = b.codi_board_no and member_id = ?) as liked_member from codi_board b where is_open = 'Y') b where rnum between ? and ?
	public List<CodiBoardExt> findCodiBoard(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CodiBoardExt> codiBoardList = new ArrayList<>();
		String sql = prop.getProperty("findCodiBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("memberId"));
			pstmt.setInt(2, (int)param.get("start"));
			pstmt.setInt(3, (int)param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CodiBoardExt codiBoardExt = handleCodiBoardResultSet(rset);
				codiBoardExt.setLikedMember(rset.getString("liked_member"));
				codiBoardList.add(codiBoardExt);
			}
		} catch (SQLException e) {
			throw new CodiBoardException("로그인 회원 최신 코디게시판 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return codiBoardList;
	}
	
	public List<CodiBoardExt> findHotCodiBoard(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CodiBoardExt> codiBoardList = new ArrayList<>();
		String sql = prop.getProperty("findHotCodiBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("memberId"));
			pstmt.setInt(2, (int)param.get("start"));
			pstmt.setInt(3, (int)param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CodiBoardExt codiBoardExt = handleCodiBoardResultSet(rset);
				codiBoardExt.setLikedMember(rset.getString("liked_member"));
				codiBoardList.add(codiBoardExt);
			}
		} catch (SQLException e) {
			throw new CodiBoardException("로그인 회원 최신 코디게시판 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return codiBoardList;
	}
	
	// 좋아요 유무 조회
	// checkLiked = select * from like_that where member_id = ? and codi_board_no = ?
	public LikeThat checkLiked(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		LikeThat liked = null;
		String sql = prop.getProperty("checkLiked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("loginMemberId"));
			pstmt.setInt(2, (int)data.get("codiBoardNo"));
			rset = pstmt.executeQuery();
			if(rset.next()) {
				liked = handleLikeThatResultSet(rset);
			}
			System.out.println("liked = " + liked);
		} catch (SQLException e) {
			throw new CodiBoardException("회원 별 좋아요 유무 조회 오류!", e);
		} finally {
			close(pstmt);
		}
		return liked;
	}
	
	// 좋아요 추가하기
	// insertLiked = insert into like_that values (seq_like_no.nextval, ?, ?)
	public int insertLiked(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertLiked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("loginMemberId"));
			pstmt.setInt(2, (int)data.get("codiBoardNo"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CodiBoardException("좋아요 추가 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 좋아요 취소하기
	// deleteLiked = delete from like_that where member_id = ? and codi_board_no = ?
	public int deleteLiked(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteLiked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)data.get("loginMemberId"));
			pstmt.setInt(2, (int)data.get("codiBoardNo"));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CodiBoardException("좋아요 취소 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 좋아요 수 조회
	// countLiked = select like_count from codi_board codi_board_no = ?
	public int countLiked(Connection conn, Map<String, Object> data) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeCount = 0;
		String sql = prop.getProperty("countLiked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int)data.get("codiBoardNo"));
			rset = pstmt.executeQuery();
			if(rset.next()) {
				likeCount = rset.getInt(1);
			}
		} catch(SQLException e) {
			throw new CodiBoardException("좋아요 수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return likeCount;
	}
	
	private LikeThat handleLikeThatResultSet(ResultSet rset) throws SQLException {
		LikeThat likeThat = new LikeThat();
		likeThat.setLikeNo(rset.getInt("like_no"));
		likeThat.setMemberId(rset.getString("member_id"));
		likeThat.setCodiBoardNo(rset.getInt("codi_board_no"));
		return likeThat;
	}

	private CodiBoardExt handleCodiBoardResultSet(ResultSet rset) throws SQLException {
		CodiBoardExt codiBoard = new CodiBoardExt();
		codiBoard.setCodiBoardNo(rset.getInt("codi_board_no"));
		codiBoard.setMemberId(rset.getString("member_id"));
		codiBoard.setCodiBoardContent(rset.getString("codi_board_content"));
		codiBoard.setLikeCount(rset.getInt("like_count"));
		codiBoard.setFilename(rset.getString("filename"));
		codiBoard.setIsOpen(rset.getString("is_open"));
		codiBoard.setUseProduct(rset.getString("use_product"));
		codiBoard.setRegDate(rset.getDate("reg_date"));
		return codiBoard;
	}

	public int getLastBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		String sql = prop.getProperty("getLastBoardNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt(1); // 첫번째 컬럼값
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return boardNo;
	}

	public int updateContent(int codiBoardNo, String content, String isOpen, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		//update codi_board set codi_board_content = ?, is_open = ? where codi_board_no = ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, isOpen);
			pstmt.setInt(3, codiBoardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getTotalContentByMe(Connection conn, String loginMemberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentByMe");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMemberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new CodiBoardException("내가 만든 코디 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public List<CodiBoardExt> findCodiBoardByMe(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CodiBoardExt> codiBoardList = new ArrayList<>();
		String sql = prop.getProperty("findCodiBoardByMe");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)param.get("memberId"));
			pstmt.setInt(2, (int)param.get("start"));
			pstmt.setInt(3, (int)param.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				CodiBoardExt codiBoardExt = handleCodiBoardResultSet(rset);
				codiBoardList.add(codiBoardExt);
			}
			System.out.println(codiBoardList);
		} catch (SQLException e) {
			throw new CodiBoardException("내가 만든 코디 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return codiBoardList;
	}
	
	public int countCodiByDate(Connection conn, String codiWhen) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String sql = prop.getProperty("countCodiByDate");
		//select count(*) from codi_board where to_char(reg_date,'YYYYMMDD' )= ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, codiWhen);
			rset = pstmt.executeQuery();
			if(rset.next())
				result = rset.getInt(1);
		} catch (SQLException e) {
			throw new CodiBoardException("코디수 조회 오류!",e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int findAllCodiCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("findAllCodiCnt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) 
				result = rset.getInt(1);
			
		} catch (SQLException e) {
			throw new CodiBoardException("코디수 조회 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
}