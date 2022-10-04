package com.kh.icodi.board.model.dao;
import static com.kh.icodi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.icodi.board.model.dto.Attachment;
import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.board.model.dto.BoardComment;
import com.kh.icodi.board.model.dto.BoardExt;
import com.kh.icodi.board.model.dto.CommentLevel;
import com.kh.icodi.board.model.exception.BoardException;
import com.kh.icodi.member.model.exception.MemberException;

/**
 * 
 * Properties 쿼리관리객체 - /sql/board/board-query.properties load!
 * 
 * DML
 * 
 * DQL
 * - PreparedStatement객체 
 * - 미완성쿼리/값대입
 * - 실행
 * - ResultSet처리 (Board객체)
 * - PreparedStatement/ResultSet 반환
 *
 */
public class BoardDao {

	private Properties prop = new Properties();

	public BoardDao() {
		String filename = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> list = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_count"));
				board.setCommentCount(rset.getInt("comment_count"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			throw new BoardException("게시글 목록 조회 오류!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	private BoardExt handleBoardResultSet(ResultSet rset) throws SQLException {
		int no = rset.getInt("board_no");
		String writer = rset.getString("writer");
		String title = rset.getString("board_title");
		String content = rset.getString("board_content");
		int readCount = rset.getInt("board_read_count");
		Timestamp regDate = rset.getTimestamp("reg_date");
		return new BoardExt(no, title, writer, content, readCount, regDate);
	}

	public int getTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContent");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContent = rset.getInt(1);
		} catch (SQLException e) {
			throw new BoardException("총 게시물수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	//DML
	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result= 0;
		String sql = prop.getProperty("insertBoard");
		//insert into board (board_no, writer, board_title, board_content) values (seq_board_no.nextval, ?, ?, ?)
		try {
			//1.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			
			//2.
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			//service에 예외 던짐(unchecked, 비지니스를 설명가능한 구체적 커스텀 예외로 전환해서 던짐)
			throw new MemberException("게시글 등록 오류!", e);
		}finally {
			//3.
			close(pstmt);
		}
		return result;
	}

	//DQL
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
			throw new BoardException("생성된 게시글 번호 조회 오류!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return boardNo;
	}

	//DML
	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException("첨부파일 등록 오류!" , e);
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Board findByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		String sql = prop.getProperty("findByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				board = handleBoardResultSet(rset); //BoardExt로 반환
			
			}
			
		} catch (SQLException e) {
			throw  new BoardException("게시글 1건 조회 오류!", e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		return board;
	}

	public List<Attachment> findAttachmentByBoardNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByBoardNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				attachments.add(handleAttachmentResultSet(rset));
			}
			
		} catch (SQLException e) {
			throw new BoardException("게시글 별 첨부파일 조회 오류!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return attachments;
	}

	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();
		attach.setNo(rset.getInt("attachment_no"));
		attach.setBoardNo(rset.getInt("board_no"));
		attach.setOriginalFilename(rset.getString("original_filename"));
		attach.setRenamedFilename(rset.getString("renamed_filename"));
		
		return attach;
	}

	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateReadCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("조회수 증가 오류!", e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Attachment findAttachmentByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment attach = null;
		String sql = prop.getProperty("findAttachmentByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				attach = handleAttachmentResultSet(rset);
			}
			
		} catch (SQLException e) {
			throw new BoardException("첨부파일 한건 조회 오류!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public int deleteBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시물 삭제 오류!", e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, BoardExt board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException("게시물 수정 오류!", e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAttachment(Connection conn, int attachNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException("첨부파일 삭제 오류!", e);
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int insertBoardComment(Connection conn, BoardComment boardComment) {
		PreparedStatement pstmt = null;
		int result = 0;
		//insertBoardComment = insert into board_comment (comment_no, writer, board_no, comment_content, comment_level, comment_ref) values(seq_board_comment_no.nextval, ?, ?, ?, ?, ?)
		String sql = prop.getProperty("insertBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(4, boardComment.getCommentLevel().getValue()); //enum의 value값 : 1, 2
			pstmt.setString(1, boardComment.getWriter());
			pstmt.setString(3, boardComment.getContent());
			pstmt.setInt(2, boardComment.getBoardNo());
			//null이 들어가면 안되지만 들어가게 해주고 object로 받는다.
			pstmt.setObject(5, boardComment.getCommentRef() == 0 ? 
					null : boardComment.getCommentRef());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException("댓글/답글 등록 오류!", e);
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	//DQL
	public List<BoardComment> findBoardCommentByBoardNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardComment> commentList = new ArrayList<>();
		//select * from board_comment where board_no = ? start with comment_level = 1 connect by prior no = comment_ref order siblings by no asc
		String sql = prop.getProperty("findBoardCommentByBoardNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				commentList.add(handBoardCommentResultSet(rset));
			}
		} catch (SQLException e) {
			throw new BoardException("게시글별 댓글 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}

	private BoardComment handBoardCommentResultSet(ResultSet rset) throws SQLException {
		int no = rset.getInt("comment_no");
		CommentLevel commentLevel = CommentLevel.valueOf(rset.getInt("comment_level"));
		String writer = rset.getString("writer");
		String content = rset.getString("comment_content");
		int boardNo = rset.getInt("board_no");
		int commentRef = rset.getInt("comment_ref"); //null인경우 0을 반환!
		Timestamp regDate = rset.getTimestamp("comment_date");
		
		return new BoardComment(no, commentLevel, writer, content, boardNo, commentRef, regDate);
	}

	public int deleteBoardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException("댓글/답글 삭제 오류!", e);
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public List<BoardExt> findLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardExt> list = new ArrayList<>();
		String sql = prop.getProperty("findLike");
		//select * from (select row_number () over (order by reg_date desc) rnum, b.*  from board b where # like ?) b where rnum between ? and ?
		
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		int start = (int) param.get("start");
		int end = (int) param.get("end");
		sql = sql.replace("#", col);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(handleBoardResultSet(rset));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getTotalContentLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentLike");
		String col = (String) param.get("searchType");
		String val = (String) param.get("searchKeyword");
		sql = sql.replace("#", col);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + val + "%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return totalContent;
	}
	
	public List<Board> findAllByMe(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> list = new ArrayList<>();
		String sql = prop.getProperty("findAllByMe");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("writer"));
			pstmt.setInt(2, (int) param.get("start"));
			pstmt.setInt(3, (int) param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_count"));
				board.setCommentCount(rset.getInt("comment_count"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			throw new BoardException("내가 쓴 게시글 목록 조회 오류!", e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int getTotalContentByMe(Connection conn, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContentByMe");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContent = rset.getInt(1);
		} catch (SQLException e) {
			throw new BoardException("내가 쓴 게시물수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

}
