package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
		// load statistics scene at the statistics pane
		_statistics = (StatisticsBarController) replacePaneContent(_statisticsPane, "StatisticsBar.fxml");
	}

	/**
	 * Set the function of the board: Play or Practise
	 * 
	 * @param function
	 *            (either "Play" or "Practise")
	 */
	public void setFunction(String function) {
		if (function.equals("Practise")) {
			showPractiseStartPage();
		} else if (function.equals("Play")) {
			showGameStartPage();
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
	 * Make a link to the questionModel.
	 * 
	 * @param questionModel
	 */
	public void setQuestionModel(QuestionModel questionModel) {
		_questionModel = questionModel;
	}

	/**
	 * Make a link to the userModel.
	 * 
	 * @param userModel
	 */
	public void setUserModel(UserModel userModel) {
		_userModel = userModel;
	}

	/**
	 * Stop any processes running on this foundation board and switch back to the
	 * home page.
	 */
	@FXML
	public void homeBtnClicked() {
		// TODO
		_main.showHome();
	}

	/**
	 * Show the GameStartPage on the main pane
	 */
	private void showGameStartPage() {
		GameStartPageController gameStartPageController = (GameStartPageController) replacePaneContent(_mainPane,
				"GameStartPage.fxml");
		gameStartPageController.setParent(this);
	}

	/**
	 * Show the PractiseStartPage on the main pane
	 */
	public void showPractiseStartPage() {
		PractiseStartPageController practiseStartPageController = (PractiseStartPageController) replacePaneContent(
				_mainPane, "PractiseStartPage.fxml");
		practiseStartPageController.setParent(this);
	}

	/**
	 * Show the QuestionScene on the main pane
	 */
	private void showQuestionScene() {
		// TODO Auto-generated method stub
		QuestionSceneController questionSceneController = (QuestionSceneController) replacePaneContent(
				_mainPane, "QuestionScene.fxml");
		questionSceneController.setParent(this);
		//questionSceneController.setQuestion(_questionModel.nextQuestion());
	}

	/**
	 * Replaces the content in the pane with the pane defined by the FXML file, and
	 * return the controller for the FXML
	 * 
	 * @param pane
	 * @param fxml
	 * @return
	 * @return the corresponding controller for the fxml file
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
	 * Practise the specific number.
	 * 
	 * @param number
	 */
	public void practise(int number) {
		// TODO ask question model to generate questions of the specific number

		showQuestionScene();
	}

	/**
	 * Randomly generate numbers to practise
	 */
	public void practiseRandomNumber() {
		// TODO Auto-generated method stub

	}

	/**
	 * Start the normal mode game using the player's name.
	 * 
	 * @param playerName
	 */
	public void startNormalGame(String playerName) {
		// TODO Auto-generated method stub

	}

	/**
	 * Start the endless mode game using the player's name.
	 * 
	 * @param playerName
	 */
	public void startEndlessGame(String playerName) {
		// TODO Auto-generated method stub

	}

}
