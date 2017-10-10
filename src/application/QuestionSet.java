package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuestionSet {

	private String _nameOfSet;
	private Map<String, String> _QAPairs;
	
	public QuestionSet(String nameOfSet) {
		_nameOfSet = nameOfSet;
		_QAPairs = new HashMap<String, String>();
	}
	
	public void addQAPair(String question, String answer) {
		_QAPairs.put(question, answer);
	}

	public List<List> generateQuestionList(int numOfQuestions){
		List<List> randomList = new ArrayList<List>();
		for (int i=0; i<numOfQuestions; i++) {
			String question = (String) _QAPairs.keySet().toArray()[new Random().nextInt(_QAPairs.keySet().toArray().length)];
			String answer = _QAPairs.get(question);
			List<String> pair = new ArrayList<String>();
			pair.add(question);
			pair.add(answer);
			randomList.add(pair);
		}
		return randomList;
	}
	
}
