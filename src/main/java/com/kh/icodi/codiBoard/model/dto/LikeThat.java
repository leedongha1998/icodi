package com.kh.icodi.codiBoard.model.dto;

public class LikeThat {
	private int likeNo;
	private String memberId;
	private int codiBoardNo;
	
	public LikeThat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeThat(int likeNo, String memberId, int codiBoardNo) {
		super();
		this.likeNo = likeNo;
		this.memberId = memberId;
		this.codiBoardNo = codiBoardNo;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCodiBoardNo() {
		return codiBoardNo;
	}

	public void setCodiBoardNo(int codiBoardNo) {
		this.codiBoardNo = codiBoardNo;
	}

	@Override
	public String toString() {
		return "LikeThat [likeNo=" + likeNo + ", memberId=" + memberId + ", codiBoardNo=" + codiBoardNo + "]";
	}
	
}
