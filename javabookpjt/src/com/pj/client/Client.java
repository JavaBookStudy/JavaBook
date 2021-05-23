package com.pj.client;

import com.pj.dto.ClientInfo;
import com.pj.dto.Quiz;

public interface Client {
	// 서버에 연결
	public boolean connect(String name);
	
	// 콘솔에서 명령 받기
	public void process(String command);
	
	// 1. 문제 풀기
	public void quiz();
	
	// 1-1. 서버로부터 문제 받기
	public Quiz getQuiz();
	
	// 2. 종료
	public void quit();
	
	// 2-1. 서버에게 현재 포인트 보내기
	public void sendPoint(int point);
	
	// 2-2. 서버에게 종료 요청 보내고 최고 득점자 정보 받기
	public ClientInfo closeConnection();
}
