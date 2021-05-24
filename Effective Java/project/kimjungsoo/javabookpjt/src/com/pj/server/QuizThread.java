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
				// Client에게 퀴즈 요청 오면 퀴즈 보내기
				InputStream in = clientSocket.getInputStream();
				
				byte[] b = new byte[10000];
				in.read(b); // client 요청 읽기
				
				RequestDTO r = (RequestDTO) deserialize(b);
				
				if(r == RequestDTO.REQUESTQUIZ) {
					// 퀴즈 보내기
					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
					
					Quiz q = server.getQuizByIndex(quizNum++);
					
					oos.writeObject(q);
					
					oos.close();

				}else {
					// 종료
					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
					
					// 확인 메시지 보내기
					oos.writeObject(RequestDTO.ACKFINCONNECTION);
					
					// 클라이언트 포인트 정보 받기
					b = new byte[10000];
					in.read(b);
					
					ClientInfo clientInfo = (ClientInfo) deserialize(b);
					
					server.addRank(clientInfo);

					// 1등 정보 보내기
					// Item 55. Optional 반환은 신중히 하라
					Optional<ClientInfo> info = server.getHighestPoint();
					
					if(!info.isPresent()) { // 최고점이 존재하지 않을 경우(랭크 추가 에러시 클라이언트 점수를 최고점으로 한다)
						oos.writeObject(clientInfo);
					}
					else {// 최고점이 존재할 경우
						oos.writeObject(info.get());
					}
					
					
					oos.close();
					clientSocket.close();
					
					break; // while문 탈출
				}
				
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	
	// Item 88. readObject 메서드는 방어적으로 작성하라
	static Object deserialize(byte[] sf) {
		try {
		return new ObjectInputStream(
				new ByteArrayInputStream(sf)).readObject();
		}catch(IOException | ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
