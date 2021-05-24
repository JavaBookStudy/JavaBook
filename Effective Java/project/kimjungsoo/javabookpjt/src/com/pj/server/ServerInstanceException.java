package com.pj.server;

// Item 70. 복구할 수 있는 상황에는 검사 예외를 던진다.
// : Main에서 호출된 Server Instance 오류를 복구할 수 있기 때문.
public class ServerInstanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String msg;
	public ServerInstanceException(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
