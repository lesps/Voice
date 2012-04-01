package edu.upenn.cis350.voice;


public class Question {

	private String text;
	private Type type;
	private Integer answer;
	
	public Question(String text, Type type){
		this.text = text;
		this.type = type;
		this.answer = -1;
	}
	
	public Type getType(){
		return type;
	}
	
	public String getText(){
		return text;
	}
	
	public int getAnswer(){
		return answer;
	}
	
	public void setAnswer(int a){
		answer = a;
	}
}
