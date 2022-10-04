package com.kh.icodi.admin.model.dto;

import java.sql.Timestamp;

public class Product {
	private String productCode;
	private int categoryCode;
	private String productName;
	private int productPrice;
	private Timestamp productRegDate;
	private int productStock;
	private ProductSize productSize;
	private String productColor;
	private String productInfo;
	private double productPluspoint;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productCode, int categoryCode, String productName, int productPrice, Timestamp productRegDate,
			int productStock, ProductSize productSize, String productColor, String productInfo,
			double productPluspoint) {
		super();
		this.productCode = productCode;
		this.categoryCode = categoryCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productRegDate = productRegDate;
		this.productStock = productStock;
		this.productSize = productSize;
		this.productColor = productColor;
		this.productInfo = productInfo;
		this.productPluspoint = productPluspoint;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Timestamp getProductRegDate() {
		return productRegDate;
	}

	public void setProductRegDate(Timestamp productRegDate) {
		this.productRegDate = productRegDate;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public double getProductPluspoint() {
		return productPluspoint;
	}

	public void setProductPluspoint(double productPluspoint) {
		this.productPluspoint = productPluspoint;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", categoryCode=" + categoryCode + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", productRegDate=" + productRegDate + ", productStock="
				+ productStock + ", productSize=" + productSize + ", productColor=" + productColor + ", productInfo="
				+ productInfo + ", productPluspoint=" + productPluspoint + "]";
	}
}
