package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class QuestionSet {

	private String _nameOfSet;
	private Map<String, String> _QAPairs = new HashMap<String, String>();
	private File _theSet;

	public QuestionSet(String nameOfSet) {
		_nameOfSet = nameOfSet;
		_theSet = new File("QuestionSets/" + _nameOfSet + ".csv");
		if (!_theSet.exists()) {
			// create file if does not exist
			try {
				_theSet.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// read all questions of the user
			List<String> listOfQuestions;
			try {
				listOfQuestions = Files.readAllLines(Paths.get(_theSet.toURI()));

				if (!listOfQuestions.isEmpty()) {
					// initialize a new questions' list
					for (String question : listOfQuestions) {
						String[] entry = question.split(",");
						_QAPairs.put(entry[0], entry[1]);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// _QAPairs = new HashMap<String, String>();
	}

	public void addQAPair(String question, String answer) {
		_QAPairs.put(question, answer);
	}

	public String getSetName() {
		return _nameOfSet;
	}

	public List<List> generateUserDefined() {
		return null;
	}

	public List<List> generateRandomQuestionList(int numOfQuestions) {
		List<List> randomList = new ArrayList<List>();
		for (int i = 0; i < numOfQuestions; i++) {
			String question = (String) _QAPairs.keySet().toArray()[new Random()
					.nextInt(_QAPairs.keySet().toArray().length)];
			String answer = _QAPairs.get(question);
			List<String> pair = new ArrayList<String>();
			pair.add(question);
			pair.add(answer);
			randomList.add(pair);
		}
		return randomList;
	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public List<List<String>> getQuestionsInSet() {
		// System.out.println("Step 1 succeed.");
		// List<String> listForEdit = new ArrayList<String>(_QAPairs.values());
		// for (int i = 0; i < listForEdit.size(); i++) {
		// System.out.println("Flag" + listForEdit.get(i));
		// }
		// return listForEdit;
		// for (int i=0; i<_QAPairs.size(); i++) {
		// List<String> tempQA = new ArrayList();
		// tempQA
		// }
		// return listForEdit;

		List<List<String>> toReturn = new ArrayList<List<String>>();
		for (Entry<String, String> entry : _QAPairs.entrySet()) {
			List<String> newEntry = new ArrayList<String>();
			newEntry.add(entry.getKey());
			newEntry.add(entry.getValue());
			toReturn.add(newEntry);
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}

		return toReturn;
	}

}
