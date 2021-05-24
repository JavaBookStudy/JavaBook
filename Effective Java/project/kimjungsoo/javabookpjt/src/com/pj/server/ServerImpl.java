package com.pj.server;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

import com.pj.dto.ClientInfo;
import com.pj.dto.Quiz;

public class ServerImpl implements Server {
	
	private ExecutorService execConnector;
	private ExecutorService execQuizSendor;
	private PriorityQueue<ClientInfo> results;
	private List<ClientInfo> clients;
	private List<Quiz> quizes;
	private ServerSocket serverSocket = null;
	
	// Singleton
	private ServerImpl() {
		this.results = new PriorityQueue<ClientInfo>((o1, o2)->{
			return -Integer.compare(o1.getPoint(), o2.getPoint()); // Max Heap
		});
		this.clients = new ArrayList<ClientInfo>();
		this.quizes = new ArrayList<Quiz>();
	}
	private static Server instance = new ServerImpl();
	
	public static Server getInstance() {
		return instance;
	}

	@Override
	public void runServer() throws ServerInstanceException{	
		// TODO Auto-generated method stub
		
		try {
			setServerSocket(new ServerSocket(7777));
			System.out.println("Server Connected");
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new ServerInstanceException("Server Socket Creation Error");
		}
		
		try {
			runConnector();

		}catch(ServerInstanceException e) {
			throw e;
		}
		
		
	}

	@Override
	public void addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		quizes.add(quiz);
	}

	// Item 80. 스레드보다는 실행자, 태스크, 스트림을 애용하라
	@Override
	public void runConnector() throws ServerInstanceException{
		// TODO Auto-generated method stub
		execConnector = Executors.newSingleThreadExecutor();
		Runnable runnable = new ConnectorThread();
		execConnector.execute(runnable);
	}
	
	@Override
	public void runQuizSendor() {
		// TODO Auto-generated method stub
		execQuizSendor = Executors.newSingleThreadExecutor();
		Runnable runnable = new ConnectorThread();
		execQuizSendor.execute(runnable);
	}

	@Override
	public void runCloser(Socket clientSocket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	synchronized public void addClient(ClientInfo client) {
		// TODO Auto-generated method stub
		clients.add(client);
	}

	@Override
	public Optional<ClientInfo> getHighestPoint() {
		// TODO Auto-generated method stub
		Optional<ClientInfo> opt = Optional.ofNullable(results.peek());
		
		return opt;
	}
	
	@Override
	public void closeServer() {
		execConnector.shutdown();
		execQuizSendor.shutdown();
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Quiz getQuizByIndex(int index) {
		if(quizes.size() > index) {
			return quizes.get(index);
		}
		else if(quizes.size() > 0){
			return quizes.get(index % quizes.size());
		}
		else {
			return new Quiz("빈 문제", "빈 정답");
		}
	}

	@Override
	public void addRank(ClientInfo client) {
		// TODO Auto-generated method stub
		results.offer(client);
	}
	

}
