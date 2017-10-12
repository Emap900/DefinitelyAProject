package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.CurrencyStringConverter;

public class FoundationBoardController implements Initializable {

	@FXML
	private Label _modeLabel;

	@FXML
	private Label _scoreLabel;

	@FXML
	private Label _numQLeftLabel;

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
	private JFXDrawer _statisticsBar;

	/**
	 * Home Button
	 */
	@FXML
	private JFXButton _homeBtn;

	@FXML
	private HBox _infoBar;

	@FXML
	private Button _helpBtn;

	private Main _main;

	private QuestionModel _questionModel;

	private UserModel _userModel;

	private Function _function;

	private String _userName;

	/**
	 * A map that has keys that are the answer to the questions that the user did
	 * wrong, and values of the number of times user got it wrong
	 */
	private Map<String, Integer> _wrongQuestions = new HashMap<String, Integer>();

	private int _score = 100;

	private Mode _mode;

	/**
	 * The only statistics controller in the main scene
	 */
	private static StatisticsBarController _statistics;

	/**
	 * Initialize the controller
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		_userModel = UserModel.getInstance();
		_questionModel = QuestionModel.getInstance();

		_infoBar.setVisible(false);

		// load statistics bar
		Pane statBar = new Pane();
		_statistics = (StatisticsBarController) replacePaneContent(statBar, "StatisticsBar.fxml");
		_statisticsBar.setSidePane(statBar);
	}

	/**
	 * Set the function of the board: Play or Practise
	 * 
	 * @param function
	 *            (either PRACTISE or MATH)
	 */
	public void setFunction(Function function) {
		_function = function;

		switch (function) {
		case PRACTISE:
			// _statistics.setTitle("Practise Mode");
			// _statistics.setInfo("Practise Maori pronunciation.");

			// show practise start page
			PractiseStartPageController pController = (PractiseStartPageController) replacePaneContent(_mainPane,
					"PractiseStartPage.fxml");
			pController.setParent(this);
			break;
		case MATH:
			// _statistics.setTitle("Math Game Mode");
			// _statistics.setInfo("Answer math questions in Maori.");

			// show math start page
			MathStartPageController mController = (MathStartPageController) replacePaneContent(_mainPane,
					"MathStartPage.fxml");
			mController.setParent(this);
			break;
		default:
			throw new RuntimeException("Function can only be PRACTISE or MATH");
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
	private void backToHome() {

		if (_function != Function.SCORE) {
			// discard current changes, stop current practise/game, reset question
			// model
			_questionModel.clearQuestionsToAsk();
		}

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

		if (number != null) {
			_questionModel.setSpecificPractiseNumber(number);
		}

		_questionModel.generateQuestions();

		_modeLabel.setText("Practise Maori Pronunciation");

		_infoBar.setVisible(true);
		_scoreLabel.setText("0");
		_numQLeftLabel.setText("Infinite");

		// _statistics.setTitle("Practising");
		// _statistics.setInfo("You got " + _score + " questions correct.");

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

		switch (gameMode) {
		case NORMALMATH:
			_mode = Mode.NORMALMATH;
			_questionModel.setMode(Mode.NORMALMATH);

			_modeLabel.setText("Maths Game: Normal Mode");

			_infoBar.setVisible(true);
			_scoreLabel.setText("0");
			_numQLeftLabel.setText("TODO");

			// ask question model to generate a list of math questions
			_questionModel.generateQuestions();

			break;
		case ENDLESSMATH:
			_mode = Mode.ENDLESSMATH;

			_modeLabel.setText("Maths Game: Endless Mode");

			_infoBar.setVisible(true);
			_scoreLabel.setText("0");
			_numQLeftLabel.setText("Infinite");

			// ask question model to generate a list of math questions
			_questionModel.generateQuestions();

			break;
		default:
			throw new RuntimeException("Game mode can only be NORMALMATH or ENDLESSMATH");
		}

		_score = 0;
		_userName = playerName;
		// _statistics.setTitle("Hi! " + playerName);
		// _statistics.setInfo("Score: " + _score);

		showQuestionScene();
	}

	/**
	 * Show the QuestionScene on the main pane
	 */
	public void showQuestionScene() {
		QuestionSceneController controller = (QuestionSceneController) replacePaneContent(_mainPane,
				"QuestionScene.fxml");
		controller.setParent(this);
		// ask for current question and answer
		controller.setQuestion(_questionModel.currentQuestion(), _questionModel.currentAnswer());
	}

	/**
	 * Show the ResultScene on the main pane
	 */
	public void showResult() {
		Task<Void> check = new Task<Void>() {
			@Override
			public Void call() {
				SpeechRecognizer.checkCorrectness();
				return null;
			}
		};
		check.setOnSucceeded(rce -> {
			// ask question model for correctness of the current question
			boolean isCorrect = _questionModel.isUserCorrect();
			// show result scene
			ResultSceneController controller = (ResultSceneController) replacePaneContent(_mainPane,
					"ResultScene.fxml");
			controller.setParent(this);

			// set the required info of the result controller
			controller.resultIsCorrect(isCorrect);

			// check if the user has a chance to retry
			controller.setCanRetry(_questionModel.canRetry());
			controller.setUserAnswer(_questionModel.answerOfUser());

			// if is in practise mode and the user's answer in incorrect, show the
			// correct answer in result scene
			if (_mode == Mode.PRACTISE && !_questionModel.isUserCorrect()) {
				controller.showCorrectAnswer(_questionModel.correctWord());
			}

			// check is the question the final one
			if (_questionModel.isFinished()) {
				controller.setFinal(true);
			}

		});
		new Thread(check).start();

	}

	@FXML
	private void showStatisticsBar(ActionEvent event) {
		if (_statisticsBar.isHidden()) {
			_statisticsBar.open();
		} else {
			_statisticsBar.close();
		}
	}

	/**
	 * Show the help information for practise or math questions depending on current
	 * function of the foundation board
	 */
	@FXML
	private void showHelp(ActionEvent event) {
		_main.Help(_function);
	}

	/**
	 * Record the correctness of the current question and show next question on the
	 * question scene.
	 */
	public void showNextQuestion() {

		// if in practise mode, add this wrong record to the wrong questions map
		if (_mode == Mode.PRACTISE) {
			String currentQuestion = _questionModel.currentQuestion();
			if (_wrongQuestions.get(currentQuestion) == null) {
				_wrongQuestions.put(currentQuestion, 1);
			} else {
				int numGetWrong = _wrongQuestions.get(currentQuestion);
				_wrongQuestions.put(currentQuestion, numGetWrong++);
			}
		}

		// append the result to the statistics bar
		_statistics.appendResult(_questionModel.isUserCorrect());

		// remove previous recording
		new BashProcess("./MagicStaff.sh", "remove", _questionModel.currentAnswer());
		// ask question model to go to next question
		_questionModel.goNext();
		showQuestionScene();

	}

	/**
	 * Finish practising/math gaming and go back to home (if is under practise mode)
	 * or go to personal summary (if is under math mode).
	 */
	public void finish() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Finish");
		alert.setHeaderText("You are going to finish answering questions");
		if (_mode == Mode.PRACTISE) {
			alert.setContentText("Do you want to finish practising and show summary?");
		} else if (_mode == Mode.NORMALMATH) {
			alert.setContentText("Do you want to skip the rest questions and save your result? "
					+ "(The rest of the questions will be marked wrong)");
		} else {
			alert.setContentText("Do you want to skip the rest questions and save your current result?");
		}

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			_function = Function.SCORE;
			// remove previous recording
			new BashProcess("./MagicStaff.sh", "remove", _questionModel.currentAnswer());
			if (_mode == Mode.PRACTISE) {

				// reset QuestionModel
				_questionModel.clearQuestionsToAsk();
				showPractiseSummary();
			} else {
				_userModel.appendRecord(_userName, _mode, _score);
				_main.showPersonalPanel(_userName);
			}
		}
	}

	private void showPractiseSummary() {
		// TODO Auto-generated method stub
		PractiseSummarySceneController controller = (PractiseSummarySceneController) replacePaneContent(_mainPane,
				"PractiseSummaryScene.fxml");
		controller.setParent(this);
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
			HBox.setHgrow(content, Priority.ALWAYS);
			VBox.setVgrow(content, Priority.ALWAYS);
			pane.getChildren().setAll(content);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return loader.getController();
	}

}
