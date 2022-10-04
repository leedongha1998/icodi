package com.kh.icodi.admin.model.dto;

public class ProductIO {
	private int ioNo;
	private String productCode;
	private String ioStatus;
	private int ioCount;
	
	public ProductIO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductIO(int ioNo, String productCode, String ioStatus, int ioCount) {
		super();
		this.ioNo = ioNo;
		this.productCode = productCode;
		this.ioStatus = ioStatus;
		this.ioCount = ioCount;
	}

	public int getIoNo() {
		return ioNo;
	}

	public void setIoNo(int ioNo) {
		this.ioNo = ioNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getIoStatus() {
		return ioStatus;
	}

	public void setIoStatus(String ioStatus) {
		this.ioStatus = ioStatus;
	}

	public int getIoCount() {
		return ioCount;
	}

	public void setIoCount(int ioCount) {
		this.ioCount = ioCount;
	}

	@Override
	public String toString() {
		return "ProductIO [ioNo=" + ioNo + ", productCode=" + productCode + ", ioStatus=" + ioStatus + ", ioCount="
				+ ioCount + "]";
	}
}
