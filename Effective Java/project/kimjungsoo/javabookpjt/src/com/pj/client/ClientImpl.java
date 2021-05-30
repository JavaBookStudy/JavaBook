package com.pj.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.pj.dto.ClientInfo;
import com.pj.dto.Quiz;
import com.pj.dto.RequestDTO;

public class ClientImpl implements Client {
	
	private Socket serverSocket;
	private ClientInfo myInfo;
	private Scanner sc;
	
	// Singleton
	private ClientImpl() {
		sc = new Scanner(System.in);
	}
	private static Client instance = new ClientImpl();
	
	public static Client getInstance() {
		return instance;
	}
	
	@Override
	public boolean connect(String name) {
		// TODO Auto-generated method stub
		try {
			serverSocket = new Socket("127.0.0.1", 7777);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		// 서버로 클라이언트 정보 전송
		try(OutputStream output = serverSocket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(output);){
			// 클라이언트 정보 객체 생성
			myInfo = new ClientInfo(name);
			oos.writeObject(myInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		System.out.println("Server Connected");

		return true;
	}

	@Override
	public void process(String command) {
		// TODO Auto-generated method stub
		int c = Integer.parseInt(command);
		switch(c) {
			case 1: quiz(); break; // 퀴즈 풀기
			case 2: quit(); break; // 종료
		}
	}

	@Override
	public void quiz() {
		// TODO Auto-generated method stub
		Quiz quiz = getQuiz();
		System.out.println("-------문제------");
		System.out.println(quiz.getQuestion());
		
		System.out.println("-------정답------");
		String ans = sc.nextLine();
		
		if(ans.equals(quiz.getAnswer())) {
			System.out.println("정답입니다...");
			myInfo.setPoint(myInfo.getPoint()+1);
		}
		else {
			System.out.println("오답입니다...");
		}
	}

	@Override
	public Quiz getQuiz() {
		// 서버로 퀴즈 요청 전송
		Quiz quiz = null;
		try(OutputStream output = serverSocket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(output);
				InputStream input = serverSocket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(input);){
			// 퀴즈 요청 전송
			oos.writeInt(RequestDTO.REQUESTQUIZ.ordinal());
			
			// 퀴즈 받기
			quiz = (Quiz)ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return quiz;
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		ClientInfo topPlayer = closeConnection();
		System.out.println("---------결과-------");
		System.out.println("나의 점수: " + myInfo.getPoint());
		System.out.println("최고 점수: " + topPlayer.getPoint() + " ("+topPlayer.getName()+")");
		System.out.println("-------------------");
		
		sc.close();
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendPoint(int point) {
		// 안씀
		
	}

	@Override
	public ClientInfo closeConnection() {
		ClientInfo topPlayer = null;
		try(OutputStream output = serverSocket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(output);
				InputStream input = serverSocket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(input);){
			// 종료 요청 전송
			oos.writeInt(RequestDTO.FINCONNECTION.ordinal());
			
			// 현재 점수 정보 전송
			oos.writeObject(myInfo);
			
			// 1등 정보 받기
			topPlayer = (ClientInfo)ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return topPlayer;
	}

	public ClientInfo getMyInfo() {
		return myInfo;
	}

	public void setMyInfo(ClientInfo myInfo) {
		this.myInfo = myInfo;
	}

}
