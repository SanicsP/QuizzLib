package quest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NameList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



public class xmlQuestListLoader {
	
	public static QuestionsList parse(File xmlFile) throws ParserConfigurationException, SAXException, IOException , XMLParseException {
		
		
		Document root_elemnt = parseFile(xmlFile);
		QuestionsList list = getQuestionList(root_elemnt);
		System.out.println(list.QuestionCount());
		return null;
		
	}
	
	private static Document parseFile(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilder doc_build = null;
		
		DocumentBuilderFactory dBf = DocumentBuilderFactory.newInstance();
		
		doc_build = dBf.newDocumentBuilder();
		
		return doc_build.parse(xmlFile);
	}
	
	public static QuestionsList getQuestionList(Document doc) throws XMLParseException {
		
		Element root = (Element)doc.getDocumentElement();
		
		String theme= root.getAttribute("theme");
		
		QuestionsList quest_list = new QuestionsList(theme, new ArrayList<>());
	
		NodeList QuestionsTag = root.getElementsByTagName("Question");
		
		for(int i = 0 ; i < QuestionsTag.getLength() ; i++)
		{
			quest_list.addQuestion(getQuestion((Element)QuestionsTag.item(i)));
		}
		
		return quest_list;
	}
	
	public static Question getQuestion(Element question_tag) throws XMLParseException {
		
		if(
				question_tag.getElementsByTagName("enonce").getLength() != 1 ||
				question_tag.getElementsByTagName("hint").getLength() != 1 ||
				question_tag.getElementsByTagName("choice").getLength() == 0
		)
			throw new XMLParseException("Question Element need one enonce and hint Elemnt and need choices elements");
		
		if(
				question_tag.getElementsByTagName("enonce").item(0).getTextContent().equals("")
				//|| question_tag.getElementsByTagName("hint").item(0).getTextContent().equals("")
		)
			throw new XMLParseException("Questions child Elements need text content");
		
		
		Question return_quest = new Question(
				question_tag.getElementsByTagName("enonce").item(0).getTextContent() , 
				question_tag.getElementsByTagName("hint").item(0).getTextContent()
				);
		NodeList ChoicesTag = question_tag.getElementsByTagName("choice");
		
		for(int i = 0 ; i < ChoicesTag.getLength() ; i++)
		{
			return_quest.addChoice(getChoice((Element)ChoicesTag.item(i)));
		}
		
		return return_quest;
		
	}
	
	public static Choice getChoice (Element choiceTag) throws XMLParseException {
		
		boolean isgood = false;
		
		String type_attrib = choiceTag.getAttribute("type");
		
		if(type_attrib.equals(""))
			throw new XMLParseException("the choice need type attr");
		
		if(type_attrib.equals("good"))
			isgood = true;
		else if(!type_attrib.equals("wrong") ||
				choiceTag.getTextContent().equals(""))
			throw new XMLParseException("the type attribute dont have a good value : wrong or good");

		return new Choice(
				choiceTag.getTextContent() , 
				isgood
		);
				
	}
	
	
	
	
}
