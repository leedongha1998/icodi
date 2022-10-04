package com.kh.icodi.board.model.service;

import static com.kh.icodi.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.icodi.board.model.dao.BoardDao;
import com.kh.icodi.board.model.dto.Attachment;
import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.board.model.dto.BoardComment;
import com.kh.icodi.board.model.dto.BoardExt;


/**
 * DML - Connection 생성 - Dao 요청 - 트랜잭션 처리 - Connection 반환
 * 
 * 
 * DQL - Connection 생성 - Dao 요청 - Connection 반환
 * 
 * 
 * @author Han
 *
 */
public class BoardService {

	private BoardDao boardDao = new BoardDao();

	public List<Board> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Board> list = boardDao.findAll(conn, param);
		close(conn);
		return list;
	}

	// DQL
	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = boardDao.getTotalContent(conn);
		close(conn);
		return totalContent;
	}

	public int insertBoard(Board board) {
		Connection conn = getConnection();
		int result = 0;

		try {
			// board테이블에 insert
			result = boardDao.insertBoard(conn, board);

			// 방금 등록된 board.no 컬럼값을 조회해와야한다.
			int boardNo = boardDao.getLastBoardNo(conn);
			System.out.println("boardNo = " + boardNo);

			// attachment테이블 insert
			List<Attachment> attachments = ((BoardExt) board).getAttachments();
			if (attachments != null && !attachments.isEmpty()) {

				for (Attachment attach : attachments) {
					attach.setBoardNo(boardNo);
					result = boardDao.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller에 예외던짐.
		} finally {
			close(conn);
		}
		return result;
	}

	public Board findByNo(int no, boolean hasRead) {
		Connection conn = getConnection();
		Board board = null;

		// 조회수 증가처리
		try {
			if(!hasRead) {
				//hasRead가 false일때 조회수를 올려준다.
				int result = boardDao.updateReadCount(conn, no);
			}

			// board 테이블에서 조회
			board = boardDao.findByNo(conn, no);

			// attachment테이블에서 조회 List<Attachment>
			List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, no);
			((BoardExt) board).setAttachments(attachments);

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);

		}

		return board;
	}

	public Board findByNo(int no) {
		// int no만 들어왔다는건 읽었다는 뜻임으로 no와 true로 다시 함수를 호출 해준다.
		return findByNo(no, true);
	}

	//DQL
	public Attachment findAttachmentByNo(int no) {
		Connection conn = getConnection();
		Attachment attach = boardDao.findAttachmentByNo(conn, no);
		close(conn);
		return attach;
	}

	public List<Attachment> findAttachmentByBoardNo(int boardNo) {
		Connection conn = getConnection();
		List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, boardNo);
		close(conn);
		return attachments;
	}

	//DML
	public int deleteBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteBoard(conn, no);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		
		return result;
	}

	
	public int updateBoard(BoardExt board) {
		Connection conn = getConnection();
		int result = 0;
		try {
			//1. 게시글 수정
			result = boardDao.updateBoard(conn, board);
			
			//2. 첨부파일 등록
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null || !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = boardDao.insertAttachment(conn, attach);
				}
			}
					
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteAttachment(int attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteAttachment(conn, attachNo);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		
		return result;
	
	}

	public int insertBoardComment(BoardComment boardComment) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.insertBoardComment(conn, boardComment);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		
		return result;
	}

	public List<BoardComment> findBoardCommentByBoardNo(int boardNo) {
		Connection conn = getConnection();
		List<BoardComment> commentList = boardDao.findBoardCommentByBoardNo(conn, boardNo);
		close(conn);
		return commentList;
	}

	public int deleteComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.deleteBoardComment(conn, no);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		
		return result;
	}

	public List<BoardExt> findLike(Map<String, Object> param) {
		Connection conn = getConnection();
		List<BoardExt> list = boardDao.findLike(conn, param);
		close(conn);
		return list;
	}

	public int getTotalContentLike(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = boardDao.getTotalContentLike(conn, param);
		close(conn);
		return totalContent;
	}
	
	public List<Board> findAllByMe(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Board> list = boardDao.findAllByMe(conn, param);
		close(conn);
		return list;
	}

	public int getTotalContentByMe(String writer) {
		Connection conn = getConnection();
		int totalContent = boardDao.getTotalContentByMe(conn, writer);
		close(conn);
		return totalContent;
	}

}
