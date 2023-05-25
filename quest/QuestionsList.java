package quest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class QuestionsList {
	private String theme;
	private ArrayList<Question> questions;
	private int iterator;
	
	//constructor
	public QuestionsList(String theme , ArrayList<Question> questions) {
		
		this.theme = theme;
		
		this.questions = (ArrayList<Question>)questions.clone();
		
		this.iterator = 0;
		
	}
	//getters

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	//functions 
	
	public void addQuestion(Question quest) {
		this.questions.add(quest);
	}
	
	public void removeQuestion(int index) {
		this.questions.remove(index);
	}
	
	public Question getQuestion(int index) {
		return this.questions.get(index);
	}
	
	public int QuestionCount() {
		return this.questions.size();
	}
	
	public boolean respond(int index) {
		
		boolean isgood = false;
		
		if(this.iterator < this.questions.size() )
		{
			isgood = this.questions.get(this.iterator).respond(index);
			iterator++;
		}
		else {
			System.out.println("quest array finish");
		}
		
		return isgood;
	}
	
	
	
}

/*
 * Description	Resource	Path	Location	Type
Type mismatch: cannot convert from Object to ArrayList<Question>	QuestionsList.java	/QuizzLib/src/quest	line 15	Java Problem

 */
