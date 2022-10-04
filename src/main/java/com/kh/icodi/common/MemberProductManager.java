package com.kh.icodi.common;

import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.dto.MemberCart;

public class MemberProductManager {
	private Member member = new Member();
	private ProductExt productExt = new ProductExt();
	private MemberCart memberCart = new MemberCart();
	
	public MemberProductManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberProductManager(Member member, ProductExt productExt, MemberCart memberCart) {
		super();
		this.member = member;
		this.productExt = productExt;
		this.memberCart = memberCart;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public ProductExt getProductExt() {
		return productExt;
	}

	public void setProductExt(ProductExt productExt) {
		this.productExt = productExt;
	}

	public MemberCart getMemberCart() {
		return memberCart;
	}

	public void setMemberCart(MemberCart memberCart) {
		this.memberCart = memberCart;
	}

	@Override
	public String toString() {
		return "MemberProductManger [member=" + member + ", productExt=" + productExt + ", memberCart=" + memberCart
				+ "]";
	}
}
