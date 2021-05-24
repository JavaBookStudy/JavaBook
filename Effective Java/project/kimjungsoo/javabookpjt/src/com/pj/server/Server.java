package com.pj.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

import com.pj.dto.ClientInfo;
import com.pj.dto.Quiz;

public interface Server {
	// 서버 실행
	public void runServer() throws ServerInstanceException;
	
	// 1. 퀴즈 문제 등록
	public void addQuiz(Quiz quiz);
	
	// 2-1. 클라이언트 연결 스레드 실행
	public void runConnector() throws ServerInstanceException;
	
	// 2-2. 클라이언트 종료 스레드 실행
	public void runCloser(Socket clientSocket);
	
	// 2-3. 퀴즈 보내는 스레드 실행
	public void runQuizSendor();
	
	// 2-4. 클라이언트 연결시 클라이언트 추가
	public void addClient(ClientInfo client);
	
	// 3. 최고 득점 반환
	public Optional<ClientInfo> getHighestPoint();
	
	public void addRank(ClientInfo client);

	public ServerSocket getServerSocket();

	public Quiz getQuizByIndex(int index);
	
	public void closeServer();
	
}
