package com.pj.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class ClientInfo implements Serializable{
	private Socket socket;
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	private void readObject(ObjectInputStream s) 
		throws IOException, ClassNotFoundException{
		s.defaultReadObject();
		
		// ���� ��Ҹ� ��������� �����Ѵ�.
		
	}

	@Override
	public String toString() {
		return "ClientInfo [socket=" + socket + ", CID=" + CID + ", name=" + name + ", point=" + point + "]";
	}
	
}
