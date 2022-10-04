package com.kh.icodi.cscenter.model.dto;

import java.sql.Date;

public class CsCenterInquireAnswer {
	private int answerNo;
	private int inquireNo;
	private String answerWriter;
	private String answerContent;
	private Date answerDate;
	public CsCenterInquireAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CsCenterInquireAnswer(int answerNo, int inquireNo, String answerWriter, String answerContent,
			Date answerDate) {
		super();
		this.answerNo = answerNo;
		this.inquireNo = inquireNo;
		this.answerWriter = answerWriter;
		this.answerContent = answerContent;
		this.answerDate = answerDate;
	}
	public int getAnswerNo() {
		return answerNo;
	}
	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}
	public int getInquireNo() {
		return inquireNo;
	}
	public void setInquireNo(int inquireNo) {
		this.inquireNo = inquireNo;
	}
	public String getAnswerWriter() {
		return answerWriter;
	}
	public void setAnswerWriter(String answerWriter) {
		this.answerWriter = answerWriter;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	@Override
	public String toString() {
		return "CsCenterInquireAnswer [answerNo=" + answerNo + ", inquireNo=" + inquireNo + ", answerWriter="
				+ answerWriter + ", answerContent=" + answerContent + ", answerDate=" + answerDate + "]";
	}
	
	
}
