package application;

import java.util.List;

public class QuestionModel {

	private static QuestionModel _modelInstance;
	
	private List<String> _preloadQAPairs;
	
	private List<String> _currentQuestionList;
	
	private String _currentQuestion;
	
	private String _currentAnswer;

	/**
	 * Constructor
	 */
	private QuestionModel() {
		//load premade question as a list into the program
		BashProcess preloadQuestions = new BashProcess("./BashCommands", "load");
		_preloadQAPairs = preloadQuestions.getresult();
	}

	/**
	 * Get the instance of the singleton class QuestionModel
	 * 
	 * @return the only instance of QuestionModel
	 */
	public static QuestionModel getInstance() {
		if (_modelInstance == null) {
			_modelInstance = new QuestionModel();
		}
		return _modelInstance;
	}

	public void generateQuestionListFromPreload(String hardness) {
		
	}
	
	public void generateQuestionListFromUserDefine(String listName) {
		
	}
	
	//return true on success, return false on the last QAPairs in the list
	public boolean goNext() {
		return false;
		
	}
	/**
	 * Get the current question
	 * 
	 * @return current question
	 */
	public String currentQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get the current answer
	 * 
	 * @return current answer
	 */
	public String currentAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Set the mode of the math aid (pratice, normal math questions, or endless math
	 * questions)
	 * 
	 * @param mode
	 *            (PRACTISE, NORMALMATH, ENDLESSMATH)
	 */
	public void setMode(Mode mode) {
		// TODO Auto-generated method stub

	}

}
