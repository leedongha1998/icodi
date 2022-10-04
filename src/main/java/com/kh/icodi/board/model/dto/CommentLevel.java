package com.kh.icodi.board.model.dto;

/**
 * 댓글 : COMMENT
 * 
 * 답글 : REPLY
 * 
 * @author Han
 *
 */
public enum CommentLevel {
	//comment는 내부적으로 1이라는 value를 가짐
	// name값으로 호출하면 COMMENT가 호출 됨.
	COMMENT(1), REPLY(2);
	
	private int value;
	
	//생성자
	CommentLevel(int value){
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public static CommentLevel valueOf(int value) {
		switch(value) {
		case 1 : return COMMENT;
		case 2 : return REPLY;
		default : throw new AssertionError("Unknown CommentLevel : " + value);
		}
	}
}
