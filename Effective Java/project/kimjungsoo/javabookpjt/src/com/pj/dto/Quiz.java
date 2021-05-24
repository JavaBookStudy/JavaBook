package com.pj.dto;

import java.io.Serializable;

public class Quiz implements Serializable{
	
	private String question;
	private String answer;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Quiz [question=" + question + ", answer=" + answer + "]";
	}
	public Quiz(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	
	
	
}
