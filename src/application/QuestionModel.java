package application;

public class QuestionModel {

	private static QuestionModel _modelInstance;

	/**
	 * Constructor
	 */
	private QuestionModel() {

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
