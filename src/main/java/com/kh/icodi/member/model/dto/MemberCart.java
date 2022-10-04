package com.kh.icodi.member.model.dto;

public class MemberCart {
	private int cartNo;
	private String productCode;
	private String memberId;
	private int cartAmount;
	
	public MemberCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberCart(int cartNo, String productCode, String memberId, int cartAmount) {
		super();
		this.cartNo = cartNo;
		this.productCode = productCode;
		this.memberId = memberId;
		this.cartAmount = cartAmount;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(int cartAmount) {
		this.cartAmount = cartAmount;
	}

	@Override
	public String toString() {
		return "MemberCart [cartNo=" + cartNo + ", productCode=" + productCode + ", memberId=" + memberId
				+ ", cartAmount=" + cartAmount + "]";
	}
}
