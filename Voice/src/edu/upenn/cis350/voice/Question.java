package edu.upenn.cis350.voice;


public class Question implements Comparable<Question>{

	private String _text;
	private Type _type;
	private Integer _answer;
	private Integer _number;
	
	public Question(int number, String text, Type type) {
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
	
	public int compareTo(Question other){
		if(this._number < other._number)
			return -1;
		else if(this._number > other._number)
			return 1;
		else
			return 0;
	}
	
	@Override
	public boolean equals(Object other){
		if(other==null)
			return false;
		if(!(other instanceof Question))
			return false;
		return this.compareTo((Question)other)==0;
	}
	
	@Override
	public int hashCode(){
		return _number * _text.hashCode();
	}
}
