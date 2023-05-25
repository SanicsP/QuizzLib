package test;
import java.util.ArrayList;

import quest.*;

public class TestApp {
	
	public static void main(String[] args)
	{
		Question quest1 = new Question("What is the capital of France"
				, "is a european coutry");
		quest1.addChoice(new Choice(new String("Mdrid"), false));
		quest1.addChoice(new Choice("Nairoby", false ));
		quest1.addChoice(new Choice("Tokyo", false ));
		quest1.addChoice(new Choice("Paris", true ));
		print_question(quest1);
		
		Question quest2 = new Question("What is the capital of Espagna "
				, "is a european coutry");
		quest2.addChoice(new Choice(new String("Mdrid"), true));
		quest2.addChoice(new Choice("Buenos Aires", false ));
		quest2.addChoice(new Choice("Shangai", false ));
		quest2.addChoice(new Choice("Seoul", false ));
		print_question(quest2);
		
		QuestionsList Geography = new QuestionsList("Geaography", new ArrayList<>());
		Geography.addQuestion(quest1);
		Geography.addQuestion(quest2);
		
		
		
		
	}
	
	public static void print_choice(Choice choice) {
		System.out.println("\t\t" + choice.getEnonce());
		if(choice.isGood()) {
			System.out.println("\t\t\ttype : good");
		}
		else {
			System.out.println("\t\t\ttype : wrong");
		}
	}
	
	public static void print_question(Question question) {
		
		System.out.println("\tEnonce : " + question.getEnonce());
		
		for(int i = 0 ; i < question.ChoiceCount() ; i++ ) {
			print_choice(question.getChoice(i));
		}
	}
	
	public static void print_question_list(QuestionsList question_list) {
		
	}
	
}
