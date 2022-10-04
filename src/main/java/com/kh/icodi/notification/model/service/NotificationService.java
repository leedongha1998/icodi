package com.kh.icodi.notification.model.service;

import static com.kh.icodi.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.cscenter.model.dao.CsCenterDao;
import com.kh.icodi.cscenter.model.dto.Alarm;
import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.ws.endpoint.HelloWebSocket;
import com.kh.icodi.ws.endpoint.MessageType;

public class NotificationService {
	private CsCenterDao csCenterDao = new CsCenterDao();
	
	public int notifyInquireAnswer(CsCenterInquire csCenterInquire) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = csCenterDao.insertAlarm(conn,csCenterInquire);
			commit(conn);
		}catch(Exception e){
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		// 사용자 실시간 알림
		if(HelloWebSocket.isConnected(csCenterInquire.getMemberId())) {
			// 메세지 생성
			Map<String, Object> data = new HashMap<>();
			data.put("receiver", csCenterInquire.getMemberId());
			data.put("msg", "[" + csCenterInquire.getTitle() + "] 문의에 답변이 달렸습니다.");
	
			// 메세지 전송
			HelloWebSocket.sendMessage(MessageType.NOTIFY_NEW_COMMENT,data);
			
			
		}
		return result;
	}

	public List<Alarm> findAlarmById(String memberId) {
		Connection conn = getConnection();
		List<Alarm> list = csCenterDao.findAlarmById(conn, memberId);
		close(conn);
		return list;
	}

	public int notifyBoardAnswer(Board board) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = csCenterDao.insertBoardAlarm(conn,board);
			commit(conn);
		}catch(Exception e){
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		// 사용자 실시간 알림
		if(HelloWebSocket.isConnected(board.getWriter())) {
			// 메세지 생성
			Map<String, Object> data = new HashMap<>();
			data.put("receiver", board.getWriter());
			data.put("msg", "[" + board.getTitle() + "] 게시글에 답변이 달렸습니다.");
			
			
			// 메세지 전송
			HelloWebSocket.sendMessage(MessageType.NOTIFY_NEW_COMMENT,data);
			
			
		}
		return result;
	}

	public List<Alarm> findAlarmByBoardNo(String memberId) {
		Connection conn = getConnection();
		List<Alarm> list = csCenterDao.findAlarmById(conn,memberId);
		close(conn);
		return list;
	}
	
}
