package quest;

import java.util.ArrayList;
import java.util.Iterator;

public class QuestionsList {
	private String theme;
	private ArrayList<Question> questions;
	private Iterator<Question> iterator;
	
	public QuestionsList(String theme , ArrayList<Question> questions) {
		
		this.theme = theme;
		
		this.questions = (ArrayList<Question>)questions.clone();
		
		this.iterator = this.questions.iterator();
		
	}
}

/*
 * Description	Resource	Path	Location	Type
Type mismatch: cannot convert from Object to ArrayList<Question>	QuestionsList.java	/QuizzLib/src/quest	line 15	Java Problem

 */
