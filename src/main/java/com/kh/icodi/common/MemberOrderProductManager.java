package com.kh.icodi.common;

import com.kh.icodi.admin.model.dto.ProductExt;
import com.kh.icodi.admin.model.dto.ProductOrder;
import com.kh.icodi.member.model.dto.Member;

public class MemberOrderProductManager {
	private Member member = new Member();
	private ProductExt product = new ProductExt();
	private ProductOrder productOrder = new ProductOrder();
	
	public MemberOrderProductManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberOrderProductManager(Member member, ProductExt product, ProductOrder productOrder) {
		super();
		this.member = member;
		this.product = product;
		this.productOrder = productOrder;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public ProductExt getProduct() {
		return product;
	}

	public void setProduct(ProductExt product) {
		this.product = product;
	}

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}

	@Override
	public String toString() {
		return "MemberOrderProductManager [member=" + member + ", product=" + product + ", productOrder=" + productOrder
				+ "]";
	}
}
