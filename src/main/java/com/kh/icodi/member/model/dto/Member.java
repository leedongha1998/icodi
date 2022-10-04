package com.kh.icodi.member.model.dto;

import java.sql.Timestamp;

import com.kh.icodi.admin.model.dto.ProductExt;

public class Member {
	private String memberId;
	private String memberName;
	private String password;
	private String email;
	private String phone;
	private Timestamp enrollDate;
	private MemberRole memberRole;
	private int point;
	private String address;
	private String addressEx;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Member(String memberId, String memberName, String password, String email, String phone, Timestamp enrollDate,
			MemberRole memberRole, int point, String address, String addressEx) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.memberRole = memberRole;
		this.point = point;
		this.address = address;
		this.addressEx = addressEx;
	}



	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}
	public MemberRole getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressEx() {
		return addressEx;
	}
	public void setAddressEx(String addressEx) {
		this.addressEx = addressEx;
	}
	@Override
	public String toString() {
		return memberId + "," + memberName + "," + password + ","
				+ email + "," + phone + "," + enrollDate + "," + memberRole + ","
				+ point + "," + address + "," + addressEx;
	}
	
}
