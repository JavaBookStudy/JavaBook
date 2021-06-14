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
		
		// ������ Ŭ���̾�Ʈ ���� ����
		try(OutputStream output = serverSocket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(output);){
			// Ŭ���̾�Ʈ ���� ��ü ����
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
			case 1: quiz(); break; // ���� Ǯ��
			case 2: quit(); break; // ����
		}
	}

	@Override
	public void quiz() {
		// TODO Auto-generated method stub
		Quiz quiz = getQuiz();
		System.out.println("-------����------");
		System.out.println(quiz.getQuestion());
		
		System.out.println("-------����------");
		String ans = sc.nextLine();
		
		if(ans.equals(quiz.getAnswer())) {
			System.out.println("�����Դϴ�...");
			myInfo.setPoint(myInfo.getPoint()+1);
		}
		else {
			System.out.println("�����Դϴ�...");
		}
	}

	@Override
	public Quiz getQuiz() {
		// ������ ���� ��û ����
		Quiz quiz = null;
		try(OutputStream output = serverSocket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(output);
				InputStream input = serverSocket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(input);){
			// ���� ��û ����
			oos.writeInt(RequestDTO.REQUESTQUIZ.ordinal());
			
			// ���� �ޱ�
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
		System.out.println("---------���-------");
		System.out.println("���� ����: " + myInfo.getPoint());
		System.out.println("�ְ� ����: " + topPlayer.getPoint() + " ("+topPlayer.getName()+")");
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
		// �Ⱦ�
		
	}

	@Override
	public ClientInfo closeConnection() {
		ClientInfo topPlayer = null;
		try(OutputStream output = serverSocket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(output);
				InputStream input = serverSocket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(input);){
			// ���� ��û ����
			oos.writeInt(RequestDTO.FINCONNECTION.ordinal());
			
			// ���� ���� ���� ����
			oos.writeObject(myInfo);
			
			// 1�� ���� �ޱ�
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
