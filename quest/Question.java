package quest;

import java.util.ArrayList;

public class Question {
// field 
	private String enonce;
	private String hint;
	private ArrayList<Choice> Choices;
	
//constructors 
	public Question(String enonce , String hint) {
		this.enonce = enonce;
		this.hint = hint;
		this.Choices = new ArrayList<>();
	}
	
//getters 
	public String getEnonce() {
		return enonce;
	}
	
	public String getHint() {
		return hint;
	}
	
//setters 
	
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	
	
	public void setHint(String hint) {
		this.hint = hint;
	}
	
//methods
	
	public void addChoice(Choice choice) {
		this.Choices.add(choice);
	}
	
	public void removeChoice(int index) {
		this.Choices.remove(index);
	}
	
	public Choice getChoice(int index) {
		return this.Choices.get(index);
	}
	public int ChoiceCount() {
		return this.Choices.size();
	}
	public boolean respond(int question_index) {
		return this.Choices.get(question_index).isGood();
	}
	
		
}
