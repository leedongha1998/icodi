package com.kh.icodi.cscenter.model.service;

import static com.kh.icodi.common.JdbcTemplate.*;
import static com.kh.icodi.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.icodi.cscenter.model.dao.CsCenterDao;
import com.kh.icodi.cscenter.model.dto.Alarm;
import com.kh.icodi.cscenter.model.dto.CsCenter;
import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer;

public class CsCenterService {
	private CsCenterDao csCenterDao = new CsCenterDao();
	
	public List<CsCenter> findAll() {
		Connection conn = getConnection();
		List<CsCenter> list = csCenterDao.findAll(conn);
		close(conn);
		return list;
	}
	public CsCenter findNoticeContentByNo(int noticeNo) {
		Connection conn = getConnection();
		CsCenter csCenter = csCenterDao.findNoticeContentByNo(conn,noticeNo);
		close(conn);
		return csCenter;
	}
	
	public int insertInquire(CsCenterInquire csCenterInquire) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = csCenterDao.insertInquire(conn,csCenterInquire);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	

	public List<CsCenterInquire> findMyInquire(String loginMemberId) {
		Connection conn = getConnection();
		List<CsCenterInquire> list = csCenterDao.findMyInquire(conn,loginMemberId);
		close(conn);
		return list;
	}
	
	public CsCenterInquire findInquireContentByNo(int inquireNo) {
		Connection conn = getConnection();
		CsCenterInquire csCenterInquire = csCenterDao.findInquireContentByNo(conn,inquireNo);
		close(conn);
		return csCenterInquire;
	}
	public List<CsCenterInquire> findAllInquire() {
		Connection conn = getConnection();
		List<CsCenterInquire> list = csCenterDao.findAllInquire(conn);
		close(conn);
		return list;
	}
	
	public int insertInquireAnswer(CsCenterInquireAnswer answer) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = csCenterDao.insertInquireAnswer(conn,answer);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	public List<CsCenterInquireAnswer> findInquireAnwerByInquireNo(int inquireNo) {
		Connection conn = getConnection();
		List<CsCenterInquireAnswer> answerList = csCenterDao.findInquireAnwerByInquireNo(conn,inquireNo);
		close(conn);
		return answerList;
	}
	
	public int deleteInquireAnswer(int inquireNo, int answerNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = csCenterDao.deleteInquireAnswer(conn,inquireNo,answerNo);			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}
	
	
	

}
