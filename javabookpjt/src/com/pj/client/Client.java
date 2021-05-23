package com.pj.client;

import com.pj.dto.ClientInfo;
import com.pj.dto.Quiz;

public interface Client {
	// ������ ����
	public boolean connect(String name);
	
	// �ֿܼ��� ��� �ޱ�
	public void process(String command);
	
	// 1. ���� Ǯ��
	public void quiz();
	
	// 1-1. �����κ��� ���� �ޱ�
	public Quiz getQuiz();
	
	// 2. ����
	public void quit();
	
	// 2-1. �������� ���� ����Ʈ ������
	public void sendPoint(int point);
	
	// 2-2. �������� ���� ��û ������ �ְ� ������ ���� �ޱ�
	public ClientInfo closeConnection();
}
