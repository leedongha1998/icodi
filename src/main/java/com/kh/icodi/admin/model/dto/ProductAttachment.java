package com.kh.icodi.admin.model.dto;

public class ProductAttachment {
	private int productAttachNo;
	private String productCode;
	private String productOriginalFilename;
	private String productRenamedFilename;
	private String codiOriginalFilename;
	private String codiRenamedFilename;
	
	public ProductAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAttachment(int productAttachNo, String productCode, String productOriginalFilename,
			String productRenamedFilename, String codiOriginalFilename, String codiRenamedFilename) {
		super();
		this.productAttachNo = productAttachNo;
		this.productCode = productCode;
		this.productOriginalFilename = productOriginalFilename;
		this.productRenamedFilename = productRenamedFilename;
		this.codiOriginalFilename = codiOriginalFilename;
		this.codiRenamedFilename = codiRenamedFilename;
	}

	public int getProductAttachNo() {
		return productAttachNo;
	}

	public void setProductAttachNo(int productAttachNo) {
		this.productAttachNo = productAttachNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductOriginalFilename() {
		return productOriginalFilename;
	}

	public void setProductOriginalFilename(String productOriginalFilename) {
		this.productOriginalFilename = productOriginalFilename;
	}

	public String getProductRenamedFilename() {
		return productRenamedFilename;
	}

	public void setProductRenamedFilename(String productRenamedFilename) {
		this.productRenamedFilename = productRenamedFilename;
	}

	public String getCodiOriginalFilename() {
		return codiOriginalFilename;
	}

	public void setCodiOriginalFilename(String codiOriginalFilename) {
		this.codiOriginalFilename = codiOriginalFilename;
	}

	public String getCodiRenamedFilename() {
		return codiRenamedFilename;
	}

	public void setCodiRenamedFilename(String codiRenamedFilename) {
		this.codiRenamedFilename = codiRenamedFilename;
	}

	@Override
	public String toString() {
		return "ProductAttachment [productAttachNo=" + productAttachNo + ", productCode=" + productCode
				+ ", productOriginalFilename=" + productOriginalFilename + ", productRenamedFilename="
				+ productRenamedFilename + ", codiOriginalFilename=" + codiOriginalFilename + ", codiRenamedFilename="
				+ codiRenamedFilename + "]";
	}
}
