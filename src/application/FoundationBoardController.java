package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FoundationBoardController implements Initializable {

	@FXML
	private StackPane _background;
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
	private Mode _mode;
	private int _trailNum;
	private int _maxTrailNum;
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

		_trailNum = 0;

		// load local config file and get the maximum trail number
		File configFile = new File("config.properties");
		// default number of chances to retry is 2
		String maxTrailNumber = "2";
		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			maxTrailNumber = props.getProperty("maxTrailNumber");
			if (maxTrailNumber == null) {
				maxTrailNumber = "2";
			}

			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		_maxTrailNum = Integer.parseInt(maxTrailNumber);

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
			_modeLabel.setText("Practise");

			// show practise start page
			PractiseStartPageController pController = (PractiseStartPageController) replacePaneContent(_mainPane,
					"PractiseStartPage.fxml");
			pController.setParent(this);
			break;

		case MATH:
			_modeLabel.setText("Math Game");

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
	 * Start practising. If the number parameter is a number, then practise this
	 * specific number, of the number parameter is null, then randomly generate
	 * numbers to practise
	 * 
	 * @param number
	 *            (either a number or null)
	 */
	public void startPractise(Integer number) {
		_mode = Mode.PRACTISE;

		_questionModel.initializePractise(number);

		_modeLabel.setText("Practise Maori Pronunciation");

		_infoBar.setVisible(true);
		_scoreLabel.setText("0");
		_numQLeftLabel.setText("Infinite");

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
			_numQLeftLabel.setText("TODO"); // TODO

			break;
		case ENDLESSMATH:
			_mode = Mode.ENDLESSMATH;

			_modeLabel.setText("Maths Game: Endless Mode");

			_infoBar.setVisible(true);
			_scoreLabel.setText("0");
			_numQLeftLabel.setText("Infinite");

			break;
		default:
			throw new RuntimeException("Game mode can only be NORMALMATH or ENDLESSMATH");
		}

		// ask question model to generate a list of math questions
		_questionModel.triggerGameStart();

		_userName = playerName;

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
	 * Inform the foundation board controller that the number of trails the user
	 * took increased by one
	 */
	public void incrementTrial() {
		_trailNum++;
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
			ResultSceneController resultController = (ResultSceneController) replacePaneContent(_mainPane,
					"ResultScene.fxml");
			resultController.setParent(this);

			// set the required info of the result controller
			resultController.resultIsCorrect(isCorrect);

			// check if the user has a chance to retry
			resultController.setCanRetry(_trailNum < _maxTrailNum);
			resultController.setUserAnswer(_questionModel.answerOfUser());

			// if is in practise mode and the user's answer in incorrect, show the
			// correct answer in result scene
			if (_mode == Mode.PRACTISE && !_questionModel.isUserCorrect()) {
				resultController.showCorrectAnswer(_questionModel.correctWord());
			}

			// check is the question the final one
			if (_questionModel.isFinished()) {
				resultController.setFinal(true);
			}

		});
		new Thread(check).start();

	}

	/**
	 * Record the correctness of the current question and show next question on the
	 * question scene.
	 */
	public void showNextQuestion() {

		// append the new result
		appendResult();

		// reset trail number
		_trailNum = 0;

		// ask question model to go to next question
		_questionModel.NextQA();
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

			// append the result
			appendResult();

			_function = Function.SCORE;

			// reset QuestionModel
			_questionModel.clear();

			if (_mode == Mode.PRACTISE) {
				showPractiseSummary();
			} else {
				_userModel.appendRecord(_userName, _mode, _questionModel.getScore());
				_main.showPersonalPanel(_userName);
			}
		}
	}

	/**
	 * Append the new result to the statistics bar and delete the temperate recorded
	 * sound file. If in practise mode and user got the question wrong, make a
	 * record of this question in the _wrongQuestion map.
	 */
	private void appendResult() {
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
	}

	/**
	 * Show the summary scene for the practise
	 */
	private void showPractiseSummary() {
		PractiseSummarySceneController controller = (PractiseSummarySceneController) replacePaneContent(_mainPane,
				"PractiseSummaryScene.fxml");
		controller.setParent(this);
		double correctRate = (double) _questionModel.getScore() / _statistics.getNumOfRecords();
		controller.setCorrectRate(correctRate);
		controller.setWrongAnswerChartData(_wrongQuestions);
	}

	/**
	 * Stop any processes running on this foundation board and switch back to the
	 * home page.
	 */
	@FXML
	private void backToHome() {
		boolean goBack = false;

		// if user haven't began practising or gaming, _mode will be null. In this case
		// do not show confirmation dialog
		if (_mode == null) {
			goBack = true;
		} else {
			// ask user for confirm
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Leave");
			alert.setHeaderText("Return to home page.");
			if (_mode == Mode.PRACTISE) {
				alert.setContentText("Do you want to stop practising and return to home page?");
			} else {
				alert.setContentText(
						"Do you want to leave the game and return to home page (score will not be saved)?");
			}

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				goBack = true;
			}
		}

		if (goBack == true) {
			if (_function != Function.SCORE) {
				// reset question model
				_questionModel.clear();
			}

			_main.showHome();
		}

	}

	/**
	 * Event handler for the "details" button, open/close the statistics bar drawer.
	 * 
	 * @param event
	 */
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
