package com.pj.dto;

public class ClientInfo {
	private String CID;
	private String name;
	private int point;
	
	public ClientInfo(String cid) {
		this.point = 0;
		this.CID = cid;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
