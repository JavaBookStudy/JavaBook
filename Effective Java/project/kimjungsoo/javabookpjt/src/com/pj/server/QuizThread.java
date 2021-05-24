package com.pj.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.pj.dto.ClientInfo;
import com.pj.dto.Quiz;
import com.pj.dto.RequestDTO;

public class QuizThread implements Runnable{

	private Socket clientSocket;
	private Server server = ServerImpl.getInstance();
	private ServerSocket serverSocket = server.getServerSocket();	
	public QuizThread(Socket client) {
		clientSocket = client;
	}
	
	private int quizNum = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {							
				// Client���� ���� ��û ���� ���� ������
				InputStream in = clientSocket.getInputStream();
				
				byte[] b = new byte[10000];
				in.read(b); // client ��û �б�
				
				RequestDTO r = (RequestDTO) deserialize(b);
				
				if(r == RequestDTO.REQUESTQUIZ) {
					// ���� ������
					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
					
					Quiz q = server.getQuizByIndex(quizNum++);
					
					oos.writeObject(q);
					
					oos.close();

				}else {
					// ����
					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
					
					// Ȯ�� �޽��� ������
					oos.writeObject(RequestDTO.ACKFINCONNECTION);
					
					// Ŭ���̾�Ʈ ����Ʈ ���� �ޱ�
					b = new byte[10000];
					in.read(b);
					
					ClientInfo clientInfo = (ClientInfo) deserialize(b);
					
					server.addRank(clientInfo);

					// 1�� ���� ������
					// Item 55. Optional ��ȯ�� ������ �϶�
					Optional<ClientInfo> info = server.getHighestPoint();
					
					if(!info.isPresent()) { // �ְ����� �������� ���� ���(��ũ �߰� ������ Ŭ���̾�Ʈ ������ �ְ������� �Ѵ�)
						oos.writeObject(clientInfo);
					}
					else {// �ְ����� ������ ���
						oos.writeObject(info.get());
					}
					
					
					oos.close();
					clientSocket.close();
					
					break; // while�� Ż��
				}
				
				in.close();
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
