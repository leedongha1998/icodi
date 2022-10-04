package com.kh.icodi.cscenter.model.dao;

import static com.kh.icodi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.icodi.board.model.dto.Board;
import com.kh.icodi.cscenter.model.dto.Alarm;
import com.kh.icodi.cscenter.model.dto.CsCenter;
import com.kh.icodi.cscenter.model.dto.CsCenterInquire;
import com.kh.icodi.cscenter.model.dto.CsCenterInquireAnswer;
import com.kh.icodi.cscenter.model.dto.SelectType;
import com.kh.icodi.cscenter.model.exception.CsCenterException;

public class CsCenterDao {
	
	private Properties prop = new Properties();
	
	public CsCenterDao() {
		String filename = CsCenterDao.class.getResource("/sql/cscenter/cscenter-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			throw new CsCenterException("sql로드 오류!",e);
		}
	}
	
	public List<CsCenter> findAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CsCenter> list = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int noticeNo = rset.getInt("notice_no");
				String noticeTitle = rset.getString("notice_title");
				String noticeContent = rset.getString("notice_content");
				Date noticeDate = rset.getDate("notice_date");
				String noticeWriter = rset.getString("notice_writer");
				CsCenter csCenter = new CsCenter(noticeNo, noticeTitle, noticeContent, noticeDate, noticeWriter);
				list.add(csCenter);
			}
		} catch (SQLException e) {
			throw new CsCenterException("공지사항 조회 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public CsCenter findNoticeContentByNo(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CsCenter csCenter = null;
		String sql = prop.getProperty("findNoticeContentByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String noticeTitle = rset.getString("notice_title");
				String noticeContent = rset.getString("notice_content");
				Date noticeDate = rset.getDate("notice_date");
				String noticeWriter = rset.getString("notice_writer");
				csCenter = new CsCenter(noticeNo, noticeTitle, noticeContent, noticeDate, noticeWriter);
				
			}
		} catch (SQLException e) {
			throw new CsCenterException("공지사항 상세조회 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return csCenter;
	}

	public int insertInquire(Connection conn, CsCenterInquire csCenterInquire) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertInquire");
//		insert into inquire (inquire_no,writer,inquire_title,inquire_content,inquire_date,inquire_type) values (seq_inquire_no.nextval,?,?,?,default,?)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, csCenterInquire.getMemberId());
			pstmt.setString(2, csCenterInquire.getTitle());
			pstmt.setString(3, csCenterInquire.getContent());
			pstmt.setString(4, csCenterInquire.getSelectType().name());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CsCenterException("1:1문의 저장 오류!",e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<CsCenterInquire> findMyInquire(Connection conn, String loginMemberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CsCenterInquire> list = new ArrayList<>();
		String sql = prop.getProperty("findMyInquire");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMemberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("inquire_no");
				String memberId = rset.getString("writer");
				String inquireTitle = rset.getString("inquire_title");
				String inquireContent = rset.getString("inquire_content");				
				Date inquireDate = rset.getDate("inquire_date");
				SelectType selectType = SelectType.valueOf(rset.getString("inquire_type"));
				
				
				CsCenterInquire csCenterInquire = new CsCenterInquire(no, memberId, inquireTitle, selectType, inquireContent, inquireDate);
				System.out.println(csCenterInquire.getSelectType());
				list.add(csCenterInquire);
			}
			
		} catch (SQLException e) {
			throw new CsCenterException("1:1문의 보기 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public CsCenterInquire findInquireContentByNo(Connection conn, int inquireNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CsCenterInquire  csCenterInquire = null;
		String sql = prop.getProperty("findInquireContentByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquireNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("inquire_no");
				String memberId = rset.getString("writer");
				String inquireTitle = rset.getString("inquire_title");
				String inquireContent = rset.getString("inquire_content");				
				Date inquireDate = rset.getDate("inquire_date");
				String inquireType = rset.getString("inquire_type");
				
				csCenterInquire = new CsCenterInquire(no, memberId, inquireTitle, null, inquireContent, inquireDate);
				
			}
		} catch (SQLException e) {
			throw new CsCenterException("1:1문의 로드 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return csCenterInquire;
	}

	public List<CsCenterInquire> findAllInquire(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CsCenterInquire> list = new ArrayList<>();
		String sql = prop.getProperty("findAllInquire");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt("inquire_no");
				String memberId = rset.getString("writer");
				String inquireTitle = rset.getString("inquire_title");
				String inquireContent = rset.getString("inquire_content");				
				Date inquireDate = rset.getDate("inquire_date");
				SelectType selectType = SelectType.valueOf(rset.getString("inquire_type"));
				
				
				CsCenterInquire csCenterInquire = new CsCenterInquire(no, memberId, inquireTitle, selectType, inquireContent, inquireDate);
				System.out.println(csCenterInquire.getSelectType());
				list.add(csCenterInquire);
			}
		} catch (SQLException e) {
			throw new CsCenterException("1:1문의 로드 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertInquireAnswer(Connection conn, CsCenterInquireAnswer answer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertInquireAnswer");
		//insertInquireAnswer = insert into inquire_comment values(seq_answer_no.nextval,?,?,?,default)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answer.getInquireNo());
			pstmt.setString(2, answer.getAnswerWriter());
			pstmt.setString(3, answer.getAnswerContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CsCenterException("1:1문의 답변 저장 오류!",e);
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<CsCenterInquireAnswer> findInquireAnwerByInquireNo(Connection conn, int inquireNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CsCenterInquireAnswer> list = new ArrayList<>();
		String sql = prop.getProperty("findInquireAnwerByInquireNo");
		//select * from inquire_comment where inquire_no = ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquireNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int answerNo = rset.getInt("answer_no");
				inquireNo = rset.getInt("inquire_no");
				String answerWriter = rset.getString("answer_writer");
				String answerContent = rset.getString("answer_content");
				Date answerDate = rset.getDate("answer_date");
				
				CsCenterInquireAnswer answer = new CsCenterInquireAnswer(answerNo, inquireNo, answerWriter, answerContent, answerDate);
				list.add(answer);
			}
		} catch (SQLException e) {
			throw new CsCenterException("1:1문의 답변 로드 오류!",e);
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int deleteInquireAnswer(Connection conn, int inquireNo, int answerNo) {
//		 delete from inquire_comment where inquire_no = ? and answer_no = ?
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteInquireAnswer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inquireNo);
			pstmt.setInt(2, answerNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CsCenterException("1:1문의 답변 삭제 오류!",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 알람 테이블에 알람 넣기
	 * @param conn
	 * @param csCenterInquire
	 * @return
	 */
	public int insertAlarm(Connection conn, CsCenterInquire csCenterInquire) {
		PreparedStatement pstmt = null;
		int result= 0;
		//insert into alarm (no,member_id,alarm_date,board_no,alarm_message) values(seq_alarm_no.nextval,?,default,?,?)
		String sql = prop.getProperty("insertAlarm");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, csCenterInquire.getMemberId());
			pstmt.setInt(2, 0);
			pstmt.setString(3, csCenterInquire.getTitle() + " 문의에 답변이 달렸습니다.");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CsCenterException("알람 추가 오류!",e); 
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public List<Alarm> findAlarmById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Alarm> list = new ArrayList<>();
		String sql = prop.getProperty("findAlarmById");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int boardNo = rset.getInt("board_no");
				Date alarmDate = rset.getDate("alarm_date");
				String alarmMessage = rset.getString("alarm_message");
				Alarm alarm = new Alarm(0, boardNo, memberId, alarmDate, alarmMessage);
				System.out.println("alarm = " + alarm);
				list.add(alarm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertBoardAlarm(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result= 0;
		//insert into alarm (no,member_id,alarm_date,alarm_message) values(seq_alarm_no.nextval,?,default,?)
		String sql = prop.getProperty("insertAlarm");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setInt(2, board.getNo());
			pstmt.setString(3, board.getTitle() + " 게시글에 답변이 달렸습니다.");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CsCenterException("알람 추가 오류!",e); 
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
}
