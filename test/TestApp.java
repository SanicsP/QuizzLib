package test;
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
