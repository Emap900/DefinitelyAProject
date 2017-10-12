package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * This class will handle local question list as well as question lists in the
 * program. The computed score for any single question therefore come from here
 * and should be stored in User Model for future reuse.
 * 
 * This class gethering input from local question list, user input (customized
 * question list), and correctness from recognizing system.
 * 
 * The class then should have following functionalities:
 * 
 * load local questionSets create new questionSets (warn user when name of
 * question set duplicate) delete existing questionSets add new question to
 * existing questionSet delete questions from existing questionSet (allow
 * multiple selection)
 * 
 * generate a random question list from a given question set and number of
 * questions are given store a user picked question list and give the
 * functionality of randomize it
 * 
 * the class should be able to iterating an generated list (also have a default
 * if no instructions given) during the iteration: current question is stored,
 * current answer is stored, current trial is stored, whether if the next
 * question existed is stored, the list of questions user done is stored
 * 
 */

// generate question, trial number, correct answer, score of game, isfinished?,
// is the question,
public class QuestionModel {

	private static QuestionModel _modelInstance;

	// for preload question set
	private Set<QuestionSet> _sets;
	private List<String> _preloadQAPairs;

	// for current question
	private String _currentQuestion;
	private String _currentAnswer;
	private int _currentTrial;
	private double _pronounciationHardnessFactor;
	private Map<String, String> _maoriCache;

	// for question list
	private List<String> _questionsDid;
	private int _lengthOfQuestionList;
	private boolean _isFinished;
	private List<String> _currentQuestionList;
	private int _numOfquestionsGotCorrect;
	private int _currentScore;

	// for mode
	private Mode _currentMode;

	public boolean isFinished() {
		return _isFinished;
	}

	/**
	 * Constructor
	 */
	private QuestionModel() {
		// load premade question as a list into the program
		_sets = new HashSet();
		_maoriCache = new HashMap();
		_maoriCache.put("expected", null);
		_maoriCache.put("actual", null);
		_pronounciationHardnessFactor = 0;
		_numOfquestionsGotCorrect = 0;
		Scanner s;
		try {
			s = new Scanner(new File("filepath"));
			ArrayList<String> list = new ArrayList<String>();
			while (s.hasNext()) {
				list.add(s.next());
			}
			s.close();
			_preloadQAPairs = list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void generateQuestionListFromUserDefine(String listName, boolean random) {
		if (random) {

		}
	}

	public void createLocalQuestionSet(String filename) {
		for (QuestionSet set : _sets) {
			if (set.getSetName().equals(filename)) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Look, a Confirmation Dialog");
				alert.setContentText("Are you ok with this?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					set.delete();
					_sets.remove(set);
					_sets.add(new QuestionSet(filename));
				}
			}
		}
	}

	// return true on success, return false on the last QAPairs in the list
	public boolean goNext() {
		return false;

	}

	public void generateAPremadeListD(String hardness) {
		switch (hardness) {
		case "easy":
			_currentQuestionList = getEasyList();
		case "medium":
			_currentQuestionList = getMediumList();
		case "hard":
			_currentQuestionList = getHardList();
		}
	}

	public void loadLocalLists() {
		File folder = new File("QuestionSets");

		File[] fileList = folder.listFiles();

		for (File file : fileList) {
			if (file.isFile()) {
				// read the sets' name
				String fileName = file.getName();
				String setName = fileName.substring(0, fileName.lastIndexOf("."));
				_sets.add(new QuestionSet(setName));
			}
		}

	}

	private List<String> getEasyList() {

		return _currentQuestionList;

	}

	private List<String> getMediumList() {

		return _currentQuestionList;

	}

	private List<String> getHardList() {
		return _currentQuestionList;

	}

	/**
	 * Get the current question
	 * 
	 * @return current question
	 */
	public String currentQuestion() {
		return _currentQuestion;
	}

	/**
	 * Get the current answer
	 * 
	 * @return current answer
	 */
	public String currentAnswer() {

		return _currentAnswer;
	}

	public void setLengthOfQuestionList(int length) {
		_lengthOfQuestionList = length;
	}

	/**
	 * Set the mode of the math aid (pratice, normal math questions, or endless math
	 * questions)
	 * 
	 * @param mode
	 *            (PRACTISE, NORMALMATH, ENDLESSMATH)
	 */
	public void setMode(Mode mode) {
		_currentMode = mode;
	}

	public void updateResult(String maoriWord, boolean correctness) {

	}

	private void computeScore(Mode mode) {
		int score = 0;
		switch (mode) {
		case PRACTISE:
			score = _currentScore + 1;
		case NORMALMATH:
			calculateHardnessFactor();
			// new%correctness = num of questions correct / total num of questions =
			// (old%correctness * total num of questions + 1)/total num of questions
			double percentageCorrect = (double) _numOfquestionsGotCorrect / _currentQuestionList.size();
			score = (int) (percentageCorrect * 100 * _pronounciationHardnessFactor
					* (1 + _currentQuestionList.size() / 100));
		case ENDLESSMATH:

		}
		_currentScore = score;
	}

	private void calculateHardnessFactor() {
		double prevFactor = _pronounciationHardnessFactor;
		double currentQuesHardness;
		// TODO warning: may leads to wrong answer, change later
		Integer currentAns = Integer.parseInt(_currentAnswer);
		if (currentAns >= 1 && currentAns <= 10) {
			currentQuesHardness = 1.0;
		} else if (Arrays.asList(20, 30, 40, 50, 60, 70, 80, 90).contains(currentAns)) {
			currentQuesHardness = 1.2;
		} else if (currentAns > 10 && currentAns < 20) {
			currentQuesHardness = 1.4;
		} else {
			currentQuesHardness = 1.6;
		}

		double hardnessFactor = ((prevFactor * (_questionsDid.size() - 1)) + currentQuesHardness)
				/ _questionsDid.size();

		_pronounciationHardnessFactor = hardnessFactor;

	}

	/**
	 * Set the specific number to practise
	 * 
	 * @param number
	 */
	public void setSpecificPractiseNumber(Integer number) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return true if the user has another chance, otherwise false
	 */
	public boolean canRetry() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @return the user's answer (Maori word)
	 */
	public String answerOfUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return true if the user's answer is correct, otherwise false
	 */
	public boolean isUserCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Generate questions to ask
	 */
	public void generateQuestions() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return the Maori word for the correct answer
	 */
	public String correctWord() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Clear the questions generated to ask when finished or stopped a practise or a
	 * math game
	 */
	public void clearQuestionsToAsk() {
		// TODO Auto-generated method stub

	}
}