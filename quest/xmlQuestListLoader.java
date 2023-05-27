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
	
	public static String[] REQUIERED_TAGS = {"Enonce" , "Hint" , "Choice" , "Question"};
	public static String REQUIERED_ROOT_ELMENT = "QuestionList";
	public static String[] REQUIERED_ATTRIBUTES = {"theme" , "type"};
	public static String[] REQUIERED_VALUES = {"good" , "wrong"};
	
	public static QuestionsList LoadQuestList(File xmlFile) throws ParserConfigurationException, SAXException, IOException , XMLParseException {
		Document root_elemnt = parseFile(xmlFile);
		return getQuestionList(root_elemnt);		
	}
	
	private static Document parseFile(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilder doc_build = null;
		
		DocumentBuilderFactory dBf = DocumentBuilderFactory.newInstance();
		
		doc_build = dBf.newDocumentBuilder();
		
		return doc_build.parse(xmlFile);
	}
	
	private static QuestionsList getQuestionList(Document doc) throws XMLParseException {
		//String requiered_root_element = "QuestionList";
		
		//String[] requiered_attributes = {"theme"};
		//String[] requiered_tags = {"Question"};
		
		Element root = (Element)doc.getDocumentElement();
		
		if(!root.getTagName().equals(REQUIERED_ROOT_ELMENT) ||
			root.getAttributes().getLength() == 0
				)
			throw new XMLParseException("the root elemnt must be a" + REQUIERED_ROOT_ELMENT +" with theme attribute");
		
		else if (
				root.getElementsByTagName(REQUIERED_TAGS[3]).getLength() == 0
		)
			throw new XMLParseException("QuestionList Elment must have " + REQUIERED_TAGS [3] +"Elements");
		
		String theme= root.getAttribute(REQUIERED_ATTRIBUTES[0]);
		
		QuestionsList quest_list = new QuestionsList(theme, new ArrayList<>());
	
		NodeList QuestionsTag = root.getElementsByTagName(REQUIERED_TAGS[3]);
		
		for(int i = 0 ; i < QuestionsTag.getLength() ; i++)
		{
			quest_list.addQuestion(getQuestion((Element)QuestionsTag.item(i)));
		}
		
		return quest_list;
	}
	
	private static Question getQuestion(Element question_tag) throws XMLParseException {
		
		//String[] requiered_tags = {"enonce" , "hint" , "choice" , "Question"};
		
		if(
				question_tag.getElementsByTagName(REQUIERED_TAGS[0]).getLength() != 1 ||
				question_tag.getElementsByTagName(REQUIERED_TAGS[1]).getLength() != 1 ||
				question_tag.getElementsByTagName(REQUIERED_TAGS[2]).getLength() == 0
		)
			throw new XMLParseException(REQUIERED_TAGS[3] + " Element need one " + REQUIERED_TAGS[0] + 
					" and " + REQUIERED_TAGS[1] + " Elemnt and need "+ REQUIERED_TAGS[2] +"elements");
		
		if(
				question_tag.getElementsByTagName(REQUIERED_TAGS[0]).item(0).getTextContent().equals("")
				//|| question_tag.getElementsByTagName("hint").item(0).getTextContent().equals("")
		)
			throw new XMLParseException(REQUIERED_TAGS[3] +" child Elements need text content");
		
		
		Question return_quest = new Question(
				question_tag.getElementsByTagName(REQUIERED_TAGS[0]).item(0).getTextContent() , 
				question_tag.getElementsByTagName(REQUIERED_TAGS[1]).item(0).getTextContent()
				);
		NodeList ChoicesTag = question_tag.getElementsByTagName(REQUIERED_TAGS[2]);
		
		for(int i = 0 ; i < ChoicesTag.getLength() ; i++)
		{
			return_quest.addChoice(getChoice((Element)ChoicesTag.item(i)));
		}
		
		return return_quest;
		
	}
	
	private static Choice getChoice (Element choiceTag) throws XMLParseException {
		
		String[] requiered_values = {"good" , "wonrg"};
		
		String type_attribute = "type";
		
		boolean isgood = false;
		
		String type_attrib = choiceTag.getAttribute(REQUIERED_ATTRIBUTES[1]);
		
		if(type_attrib.equals(""))
			throw new XMLParseException("the choice need type attr");
		
		if(type_attrib.equals(REQUIERED_VALUES[0]))
			isgood = true;
		else if(!type_attrib.equals(REQUIERED_VALUES[1]) ||
				choiceTag.getTextContent().equals(""))
			throw new XMLParseException("the type attribute dont have a good value : wrong or good");

		return new Choice(
				choiceTag.getTextContent() , 
				isgood
		);
				
	}
	
	
	
	
}
