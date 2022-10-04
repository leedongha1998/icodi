package com.kh.icodi.common;

import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.member.model.dto.Member;
import com.kh.icodi.member.model.dto.MemberCart;

public class MemberCartProductManager {
	private MemberCart cart = new MemberCart();
	private ProductExt product = new ProductExt();
	private Member member = new Member();
	
	public MemberCartProductManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberCartProductManager(MemberCart cart, ProductExt product, Member member) {
		super();
		this.cart = cart;
		this.product = product;
		this.member = member;
	}

	public MemberCart getCart() {
		return cart;
	}

	public void setCart(MemberCart cart) {
		this.cart = cart;
	}

	public ProductExt getProduct() {
		return product;
	}

	public void setProduct(ProductExt product) {
		this.product = product;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "MemberCartProductManager [cart=" + cart + ", product=" + product + ", member=" + member + "]";
	}
}
