package com.kh.icodi.codiBoard.model.service;

import static com.kh.icodi.common.JdbcTemplate.close;
import static com.kh.icodi.common.JdbcTemplate.commit;
import static com.kh.icodi.common.JdbcTemplate.getConnection;
import static com.kh.icodi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.icodi.codiBoard.model.dao.CodiBoardDao;
import com.kh.icodi.codiBoard.model.dto.CodiBoardExt;
import com.kh.icodi.codiBoard.model.dto.LikeThat;
import com.kh.icodi.member.model.dto.Member;

public class CodiBoardService {
	private CodiBoardDao codiBoardDao = new CodiBoardDao();

	public int getTotalContentNewCodi() {
		Connection conn = getConnection();
		int totalContent = codiBoardDao.getTotalContentNewCodi(conn);
		close(conn);
		return totalContent;
	}

	public List<CodiBoardExt> findCodiBoard(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CodiBoardExt> codiBoardList =  codiBoardDao.findCodiBoard(conn, param);
		close(conn);
		return codiBoardList;
	}
	public List<CodiBoardExt> findHotCodiBoard(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CodiBoardExt> codiBoardList =  codiBoardDao.findHotCodiBoard(conn, param);
		close(conn);
		return codiBoardList;
	}

	public LikeThat checkLiked(Map<String, Object> data) {
		Connection conn = getConnection();
		LikeThat liked = codiBoardDao.checkLiked(conn, data);
		close(conn);
		return liked;
	}

	public int deleteLike(Map<String, Object> data) {
		Connection conn = getConnection();
		int likeCount = 0;
		try {
			int result = codiBoardDao.deleteLiked(conn, data);
			likeCount = codiBoardDao.countLiked(conn, data);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return likeCount;
	}

	public int insertLike(Map<String, Object> data) {
		Connection conn = getConnection();
		int likeCount = 0;
		try {
			int result = codiBoardDao.insertLiked(conn, data);
			likeCount = codiBoardDao.countLiked(conn, data);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return likeCount;
	}

	public int updateContent(String content, String isOpen) {
		Connection conn = getConnection();
		int result = 0;
		try {
			
			int codiBoardNo = codiBoardDao.getLastBoardNo(conn);
			System.out.println("coidBoardNo = " + codiBoardNo);
			
			result = codiBoardDao.updateContent(codiBoardNo, content, isOpen, conn);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller에 예외던짐.
		} finally {
			close(conn);
		}
		return result;
	}

	public int getTotalContentByMe(String loginMemberId) {
		Connection conn = getConnection();
		int totalContent = codiBoardDao.getTotalContentByMe(conn, loginMemberId);
		close(conn);
		return totalContent;
	}

	public List<CodiBoardExt> findCodiBoardByMe(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CodiBoardExt> codiBoardList =  codiBoardDao.findCodiBoardByMe(conn, param);
		close(conn);
		return codiBoardList;
	}



	
	
	/**
	 * 날짜별 코디 수 통계
	 * @param codiWhen
	 * @return
	 */
	public int countCodiByDate(String codiWhen) {
		Connection conn = getConnection();
		int result = codiBoardDao.countCodiByDate(conn,codiWhen);
		close(conn);
		return result;
	}

	/**
	 * 코디 총 개수 구하기
	 * @return
	 */
	public int findAllCodiCnt() {
		Connection conn = getConnection();
		int result = codiBoardDao.findAllCodiCnt(conn);
		close(conn);
		return result;
	}
	
	
}
