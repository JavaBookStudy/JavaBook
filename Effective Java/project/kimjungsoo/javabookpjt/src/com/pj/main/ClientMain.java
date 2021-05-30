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
		
		System.out.println("--------���� ����--------");
		
		System.out.print("����� �̸��� �Է��ϼ��� : ");
		
		String userName = sc.nextLine();
		ClientInfo info = new ClientInfo(userName);
		
		// ���� ����
		if(!client.connect(userName)) {
			System.out.println("���� ���� ����...");
			System.exit(1);
		}
		
		while(true) {
			System.out.println("[�޴�] 1: ���� Ǯ��  2: ����");
			System.out.print("�Է�: ");
			String command = sc.nextLine();
			
			client.process(command);
			
			// ����
			if(Integer.parseInt(command) == 2) {
				break;
			}
		}
	}

}
