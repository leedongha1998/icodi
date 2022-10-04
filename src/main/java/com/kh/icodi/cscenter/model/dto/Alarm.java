package com.kh.icodi.cscenter.model.dto;

import java.sql.Date;

public class Alarm {
	private int no;
	private int boardNo;
	private String memberId;
	private Date alarmDate;
	private String alarmMessage;
	public Alarm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Alarm(int no, int boardNo, String memberId, Date alarmDate, String alarmMessage) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.alarmDate = alarmDate;
		this.alarmMessage = alarmMessage;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getAlarmDate() {
		return alarmDate;
	}
	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}
	public String getAlarmMessage() {
		return alarmMessage;
	}
	public void setAlarmMessage(String alarmMessage) {
		this.alarmMessage = alarmMessage;
	}
	@Override
	public String toString() {
		return "Alarm [no=" + no + ", boardNo=" + boardNo + ", memberId=" + memberId + ", alarmDate=" + alarmDate
				+ ", alarmMessage=" + alarmMessage + "]";
	}
	
	
}
