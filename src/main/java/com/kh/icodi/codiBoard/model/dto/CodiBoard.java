package com.kh.icodi.codiBoard.model.dto;

import java.sql.Blob;
import java.util.Date;

public class CodiBoard {
	private int codiBoardNo;
	private String memberId;
	private String codiBoardContent;
	private int likeCount;
	private String filename;
	private String isOpen;
	private String useProduct;
	private Date regDate;
	
	public CodiBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CodiBoard(int codiBoardNo, String memberId, String codiBoardContent, int likeCount, String filename,
			String isOpen, String useProduct, Date regDate) {
		super();
		this.codiBoardNo = codiBoardNo;
		this.memberId = memberId;
		this.codiBoardContent = codiBoardContent;
		this.likeCount = likeCount;
		this.filename = filename;
		this.isOpen = isOpen;
		this.useProduct = useProduct;
		this.regDate = regDate;
	}

	public int getCodiBoardNo() {
		return codiBoardNo;
	}

	public void setCodiBoardNo(int codiBoardNo) {
		this.codiBoardNo = codiBoardNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCodiBoardContent() {
		return codiBoardContent;
	}

	public void setCodiBoardContent(String codiBoardContent) {
		this.codiBoardContent = codiBoardContent;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getUseProduct() {
		return useProduct;
	}

	public void setUseProduct(String useProduct) {
		this.useProduct = useProduct;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CodiBoard [codiBoardNo=" + codiBoardNo + ", memberId=" + memberId + ", codiBoardContent="
				+ codiBoardContent + ", likeCount=" + likeCount + ", filename=" + filename + ", isOpen=" + isOpen
				+ ", useProduct=" + useProduct + ", regDate=" + regDate + "]";
	}
}
