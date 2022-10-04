package com.kh.icodi.cscenter.model.dto;

import java.sql.Date;

public class CsCenterInquire {
	private int no;
	private String memberId;
	private String title;
	private SelectType selectType;
	private String content;
	private Date inquireDate;
	public CsCenterInquire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CsCenterInquire(int no, String memberId, String title, SelectType selectType, String content,
			Date inquireDate) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.title = title;
		this.selectType = selectType;
		this.content = content;
		this.inquireDate = inquireDate;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public SelectType getSelectType() {
		return selectType;
	}
	public void setSelectType(SelectType selectType) {
		this.selectType = selectType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getInquireDate() {
		return inquireDate;
	}
	public void setInquireDate(Date inquireDate) {
		this.inquireDate = inquireDate;
	}
	@Override
	public String toString() {
		return "CsCenterInquire [no=" + no + ", memberId=" + memberId + ", title=" + title + ", selectType="
				+ selectType + ", content=" + content + ", inquireDate=" + inquireDate + "]";
	}
	
	
	
	
	
}
