package com.kh.icodi.admin.model.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductExt extends Product {
	private List<ProductAttachment> attachmentList = new ArrayList<>();

	public ProductExt() {
		super();
	}

	public ProductExt(String productCode, int categoryCode, String productName, int productPrice,
			Timestamp productRegDate, int productStock, ProductSize productSize, String productColor,
			String productInfo, double productPluspoint) {
		super(productCode, categoryCode, productName, productPrice, productRegDate, productStock, productSize, productColor,
				productInfo, productPluspoint);
	}
	
	public List<ProductAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<ProductAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public void addAttachment(ProductAttachment attach) {
		this.attachmentList.add(attach);
	}

	@Override
	public String toString() {
		return "ProductExt [attachmentList=" + attachmentList + ", toString()=" + super.toString() + "]";
	}


}
