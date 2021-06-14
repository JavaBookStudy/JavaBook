package com.pj.main;

import java.util.Scanner;

import com.pj.client.Client;
import com.pj.client.ClientImpl;
import com.pj.dto.ClientInfo;

public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = ClientImpl.getInstance();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--------퀴즈 게임--------");
		
		System.out.print("사용자 이름을 입력하세요 : ");
		
		String userName = sc.nextLine();
		ClientInfo info = new ClientInfo(userName);
		
		// 서버 연결
		if(!client.connect(userName)) {
			System.out.println("서버 연결 실패...");
			System.exit(1);
		}
		
		while(true) {
			System.out.println("[메뉴] 1: 문제 풀기  2: 종료");
			System.out.print("입력: ");
			String command = sc.nextLine();
			
			client.process(command);
			
			// 종료
			if(Integer.parseInt(command) == 2) {
				break;
			}
		}
	}

}
