package com.kh.icodi.codiBoard.model.dto;

import java.sql.Blob;
import java.util.Date;

public class CodiBoardExt extends CodiBoard {
	private String likedMember;

	public CodiBoardExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CodiBoardExt(int codiBoardNo, String memberId, String codiBoardContent, int likeCount, String filename,
			String isOpen, String useProduct, Date regDate) {
		super(codiBoardNo, memberId, codiBoardContent, likeCount, filename, isOpen, useProduct, regDate);
		// TODO Auto-generated constructor stub
	}

	public String getLikedMember() {
		return likedMember;
	}

	public void setLikedMember(String likedMember) {
		this.likedMember = likedMember;
	}

	@Override
	public String toString() {
		return "CodiBoardExt [likedMember=" + likedMember + ", toString()=" + super.toString() + "]";
	}
}
