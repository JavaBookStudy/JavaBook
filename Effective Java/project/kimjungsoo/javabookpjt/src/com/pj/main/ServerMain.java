package com.pj.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pj.dto.Quiz;
import com.pj.server.Server;
import com.pj.server.ServerImpl;
import com.pj.server.ServerInstanceException;

public class ServerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = ServerImpl.getInstance();
		
		// ÄûÁî ¼³Á¤
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("quiz.txt")));
				){
			
			int num = Integer.parseInt(br.readLine());
			
			for(int i=0;i<num;i++) {
				String[] q = br.readLine().split(" ");
				server.addQuiz(new Quiz(q[0], q[1]));
			}
		
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			server.runServer();
		} catch (ServerInstanceException e) {
			System.out.println(e.getMsg());
			e.printStackTrace();
		}
		
		
	}

}
