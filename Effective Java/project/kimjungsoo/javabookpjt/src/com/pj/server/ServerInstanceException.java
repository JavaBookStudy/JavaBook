package com.pj.server;

// Item 70. ������ �� �ִ� ��Ȳ���� �˻� ���ܸ� ������.
// : Main���� ȣ��� Server Instance ������ ������ �� �ֱ� ����.
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
