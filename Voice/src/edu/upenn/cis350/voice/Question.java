package edu.upenn.cis350.voice;


public class Question {

	private String text;
	private Type type;
	
	public Question(String text, Type type){
		this.text = text;
		this.type = type;
	}
	
	public Type getType(){
		return type;
	}
	
	public String getText(){
		return text;
	}
}
