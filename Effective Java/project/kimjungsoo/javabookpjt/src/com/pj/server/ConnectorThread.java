package com.pj.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pj.dto.ClientInfo;

// Item 80. �����庸�ٴ� ������, �½�ũ, ��Ʈ���� �ֿ��϶�
public class ConnectorThread implements Runnable{
	private Server server = ServerImpl.getInstance();
	private ServerSocket serverSocket = server.getServerSocket();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				
				System.out.println(new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + "client connected");
			
				// Client�κ��� ���� �ޱ�
				InputStream in = socket.getInputStream();
				
				byte[] b = new byte[10000];
				in.read(b);
				
				// client ���� ����
				ClientInfo info = (ClientInfo) deserialize(b);
				info.setSocket(socket);
				server.addClient(info);
				System.out.println("Client Added : " + info);
				
				server.runQuizSendor(socket);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	
	// Item 88. readObject �޼���� ��������� �ۼ��϶�
	static Object deserialize(byte[] sf) {
		try {
		return new ObjectInputStream(
				new ByteArrayInputStream(sf)).readObject();
		}catch(IOException | ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
