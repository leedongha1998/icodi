package com.kh.icodi.stats.model.dto;

import java.sql.Date;

public class Stats {
	private String visitMemberId;
	private Date visitDate;
	private int countVisit;
	
	public Stats() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stats(String visitMemberId, Date visitDate, int countVisit) {
		super();
		this.visitMemberId = visitMemberId;
		this.visitDate = visitDate;
		this.countVisit = countVisit;
	}

	public String getVisitMemberId() {
		return visitMemberId;
	}

	public void setVisitMemberId(String visitMemberId) {
		this.visitMemberId = visitMemberId;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public int getCountVisit() {
		return countVisit;
	}

	public void setCountVisit(int countVisit) {
		this.countVisit = countVisit;
	}

	@Override
	public String toString() {
		return "Stats [visitMemberId=" + visitMemberId + ", visitDate=" + visitDate + ", countVisit=" + countVisit
				+ "]";
	}
	
	
}
