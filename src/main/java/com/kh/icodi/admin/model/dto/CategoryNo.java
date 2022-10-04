package com.kh.icodi.admin.model.dto;

public enum CategoryNo {
	TOP(1), BOTTOM(2), SHOES(3), ACC(4);
	
	private int value;
	
	CategoryNo(int value) {
		this.value = value;
	}
	
	public int getValue(int value) {
		return this.value;
	}
	
	public static CategoryNo valueOf(int value) {
		switch(value) {
		case 1 : return TOP;
		case 2 : return BOTTOM;
		case 3 : return SHOES;
		case 4 : return ACC;
		default : throw new AssertionError("Unknown CategoryNo : " + value);
		}
	}
	
	public static int stringOf(String value) {
		switch(value) {
		case "TOP" : return 1;
		case "BOTTOM" : return 2;
		case "SHOES" : return 3;
		case "ACC" : return 4;
		default : throw new AssertionError("Unknown CategoryNo : " + value);
		}
	}
}
