package com.spring.shop.exception;

public class GlobalException extends RuntimeException  {
	
	private static final long serialVersionUID = 8636904039967520807L;
	
	private String message;
	
	public GlobalException(String message, Exception e) {
		super(message, e);
		this.message = message;
	}
	
	public GlobalException(String message) {
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		if(message == null || message.length() == 0) {
				return "요청처리에 실패하였습니다.";
		}
		
		return message;
	}
}
