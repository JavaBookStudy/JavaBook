package com.pj.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

import com.pj.dto.ClientInfo;
import com.pj.dto.Quiz;

public interface Server {
	// ���� ����
	public void runServer() throws ServerInstanceException;
	
	// 1. ���� ���� ���
	public void addQuiz(Quiz quiz);
	
	// 2-1. Ŭ���̾�Ʈ ���� ������ ����
	public void runConnector() throws ServerInstanceException;
	
	// 2-2. Ŭ���̾�Ʈ ���� ������ ����
	public void runCloser(Socket clientSocket);
	
	// 2-3. ���� ������ ������ ����
	public void runQuizSendor();
	
	// 2-4. Ŭ���̾�Ʈ ����� Ŭ���̾�Ʈ �߰�
	public void addClient(ClientInfo client);
	
	// 3. �ְ� ���� ��ȯ
	public Optional<ClientInfo> getHighestPoint();
	
	public void addRank(ClientInfo client);

	public ServerSocket getServerSocket();

	public Quiz getQuizByIndex(int index);
	
	public void closeServer();
	
}
