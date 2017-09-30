package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.converter.CurrencyStringConverter;

public class FoundationBoardController implements Initializable {

	/**
	 * This pane is shown on the left side of the scene, which can have different
	 * scenes switching on it
	 */
	@FXML
	private HBox _mainPane;

	/**
	 * This pane is shown on the right side of the scene, which shows the statistics
	 */
	@FXML
	private HBox _statisticsPane;

	/**
	 * Home Button
	 */
	@FXML
	private Button _homeBtn;

	private Main _main;

	private QuestionModel _questionModel;

	private UserModel _userModel;

	private Function _function;

	private String _playerName;

	private int _score = 0;

	private Mode _mode;

	private double _hardnessFactor = 1.0;

	private double _percentCorrectness = 0.0;

	/**
	 * The only statistics controller in the main scene
	 */
	private static StatisticsBarController _statistics;

	/**
	 * Initialize the controller
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO
		_score = 0;
		_userModel = UserModel.getInstance();
		_questionModel = QuestionModel.getInstance();

	}

	/**
	 * Set the function of the board: Play or Practise
	 * 
	 * @param function
	 *            (either PRACTISE or MATH)
	 */
	public void setFunction(Function function) {
		_function = function;

		// load statistics scene at the statistics pane
		Scene statisticsBar = _main.loadScene("StatisticsBar.fxml");
		_statisticsPane.getChildren().setAll(statisticsBar.getRoot());
		_statistics = (StatisticsBarController) statisticsBar.getUserData();

		if (function == Function.PRACTISE) {
			_statistics.setTitle("Practise Mode");
			_statistics.setInfo("Practise Maori pronunciation.");

			// show practise start page
			PractiseStartPageController controller = (PractiseStartPageController) replacePaneContent(_mainPane,
					"PractiseStartPage.fxml");
			controller.setParent(this);
		} else if (function == Function.MATH) {
			_statistics.setTitle("Math Game Mode");
			_statistics.setInfo("Answer math questions in Maori.");

			// show math start page
			MathStartPageController controller = (MathStartPageController) replacePaneContent(_mainPane,
					"MathStartPage.fxml");
			controller.setParent(this);
		}
	}

	/**
	 * Make a link to Main class.
	 * 
	 * @param main
	 */
	public void setParent(Main main) {
		_main = main;
	}

	/**
	 * Stop any processes running on this foundation board and switch back to the
	 * home page.
	 */
	@FXML
	public void backToHome() {
		// TODO

		_main.showHome();
	}

	/**
	 * Start practising. If the number parameter is a number, then practise this
	 * specific number, of the number parameter is null, then randomly generate
	 * numbers to practise
	 * 
	 * @param number
	 *            (either a number or null)
	 */
	public void startPractise(Integer number) {
		_mode = Mode.PRACTISE;
		// TODO ask question model to generate practise questions of a specific number
		// or random numbers

		// if (number != null) {
		//
		// }
		_score = 0;
		_statistics.setTitle("Practising");
		_statistics.setInfo("You got " + _score + " questions correct.");

		showQuestionScene();
	}

	/**
	 * Start math game.
	 * 
	 * @param gameMode
	 *            the gameMode (either Normal or Endless)
	 * @param playerName
	 */
	public void startMathGame(Mode gameMode, String playerName) {

		_mode = gameMode;
		// TODO ask question model to generate a list of math questions or endless
		// questions

		_score = 0;
		_playerName = playerName;
		_statistics.setTitle("Hi! " + playerName);
		_statistics.setInfo("Score: " + _score);

		showQuestionScene();
	}

	/**
	 * Show the QuestionScene on the main pane
	 */
	public void showQuestionScene() {
		QuestionSceneController controller = (QuestionSceneController) replacePaneContent(_mainPane,
				"QuestionScene.fxml");
		controller.setParent(this);
		// controller.setQuestion(_questionModel.currentQuestion(),
		// _questionModel.currentAnswer());
		controller.setQuestion("To be implement", "To be implement");
	}

	/**
	 * Show the ResultScene on the main pane
	 */
	public void showResult() {
		// TODO Auto-generated method stub
		Task<Void> check = new Task<Void>() {
			@Override
			public Void call() {
				SpeechRecognizer.checkCorrectness();
				return null;
			}
		};
		check.setOnSucceeded(rce -> {
			// TODO ask question model for correctness of the current question
			boolean isCorrect = false;
			// show result scene
			ResultSceneController controller = (ResultSceneController) replacePaneContent(_mainPane,
					"ResultScene.fxml");
			controller.setParent(this);
			controller.resultIsCorrect(isCorrect);
			// // TODO get the user's answer
			controller.setUserAnswer("To be implemented");
		});
		new Thread(check).start();

	}

	/**
	 * Show the help information for the specific scene.
	 * 
	 * @param sceneName
	 *            (must be one of the following: "PractiseStartPage",
	 *            "GameStartPage", "QuestionScene", or "ResultScene")
	 */
	public void showHelp(String sceneName) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return true if the user can have another try, false if there is no another
	 *         chance
	 */
	public boolean canRetry() {
		boolean canRetry = false;
		if (_function == Function.MATH) {
			// TODO ask question model if user has another chance
		}
		return false;
	}

	/**
	 * Record the correctness of the current question and show next question on the
	 * question scene.
	 */
	public void showNextQuestion() {
		// TODO Auto-generated method stub
		boolean isCorrect = true;
		// TODO Ask question model for the correctness of the user's answer to current
		// question
		// append result
		_statistics.appendResult(isCorrect);
		// update _hardness factor
		_hardnessFactor = calculateHardnessFactor();
		if (isCorrect) {
			if (_mode == Mode.PRACTISE) {
				_score++;
				_statistics.setInfo("You got " + _score + " questions correct.");
			} else if (_mode == Mode.ENDLESSMATH) {
				_score = (int) (_hardnessFactor * _statistics.getNumResults() * 10);
				_statistics.setInfo("Score: " + _score);
			} else if (_mode == Mode.NORMALMATH) {
				// TODO get total number of questions
				int numQuestions = 20;
				// new%correctness = num of questions correct / total num of questions =
				// (old%correctness * total num of questions + 1)/total num of questions
				_percentCorrectness = (_percentCorrectness * numQuestions + 1) / numQuestions;
				_score = (int) (_percentCorrectness * 100 * _hardnessFactor * (1 + numQuestions / 100));
				_statistics.setInfo("Score: " + _score);
			}
		}

		// TODO ask question model to go to next question
		showQuestionScene();

	}

	/**
	 * Replaces the content in the pane with the pane defined by the FXML file, and
	 * return the controller for the FXML
	 * 
	 * @param pane
	 * @param fxml
	 * @return Controller
	 */
	private Object replacePaneContent(Pane pane, String fxml) {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = Main.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Main.class.getResource(fxml));
		try {
			Pane content = (Pane) loader.load(in);
			pane.getChildren().setAll(content);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return loader.getController();
	}

	/**
	 * Calculate the hardness factor of the questions already done.
	 * <p>
	 * The hardness factor is calculated as such: the hardness factor of all the
	 * questions that are already done is the average of the hardness factors of
	 * each questions. <br/>
	 * The hardness factor of a question is calculated as such: if the pronunciation
	 * of the answer for the question is one word, the hardness factor is 1; if the
	 * pronunciation is two words, the hardness factor is 1.2; if the pronunciation
	 * is three words, the hardness factor is 1.4; if the pronunciation is four
	 * words, the hardness factor is 1.6
	 * </p>
	 * 
	 * @return the hardness factor of the questions that are already done
	 */
	private double calculateHardnessFactor() {
		int numQuesDone = _statistics.getNumResults();
		double prevFactor = _hardnessFactor;
		double currentQuesHardness;
		// Integer currentAns = new Integer(_questionModel.currentAnswer());
		Integer currentAns = new Integer(20);
		if (currentAns >= 1 && currentAns <= 10) {
			currentQuesHardness = 1.0;
		} else if (Arrays.asList(20, 30, 40, 50, 60, 70, 80, 90).contains(currentAns)) {
			currentQuesHardness = 1.2;
		} else if (currentAns > 10 && currentAns < 20) {
			currentQuesHardness = 1.4;
		} else {
			currentQuesHardness = 1.6;
		}

		double hardnessFactor = ((prevFactor * numQuesDone - 1) + currentQuesHardness) / numQuesDone;

		return hardnessFactor;
	}

}
