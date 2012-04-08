package edu.upenn.cis350.voice;


public class Question {

	private String _text;
	private Type _type;
	private Integer _answer;
	private Integer _number;
	
	public Question(int number, String text, Type type){
		this._text = text;
		this._type = type;
		this._number = number;
		this._answer = -1;
	}
	
	public Type getType(){
		return _type;
	}
	
	public String getText(){
		return _text;
	}
	
	public int getAnswer(){
		return _answer;
	}
	
	public int getNumber(){
		return _number;
	}
	
	public void setAnswer(int a){
		_answer = a;
	}
}
