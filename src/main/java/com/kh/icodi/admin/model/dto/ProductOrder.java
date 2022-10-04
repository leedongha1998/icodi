package com.kh.icodi.admin.model.dto;

import java.sql.Date;

public class ProductOrder {
	private String orderNo;
	private int orderTotalPrice;
	private String orderPayments;
	private Date orderDate;
	private String orderStatus;
	private int orderTotalCount;
	
	public ProductOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductOrder(String orderNo, int orderTotalPrice, String orderPayments, Date orderDate, String orderStatus,
			int orderTotalCount) {
		super();
		this.orderNo = orderNo;
		this.orderTotalPrice = orderTotalPrice;
		this.orderPayments = orderPayments;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderTotalCount = orderTotalCount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderPayments() {
		return orderPayments;
	}

	public void setOrderPayments(String orderPayments) {
		this.orderPayments = orderPayments;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderTotalCount() {
		return orderTotalCount;
	}

	public void setOrderTotalCount(int orderTotalCount) {
		this.orderTotalCount = orderTotalCount;
	}

	@Override
	public String toString() {
		return "ProductOrder [orderNo=" + orderNo + ", orderTotalPrice=" + orderTotalPrice + ", orderPayments="
				+ orderPayments + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + ", orderTotalCount="
				+ orderTotalCount + "]";
	}
}
