package application;

import java.io.IOException;
import java.io.InputStream;

import controllers.FoundationBoardController;
import controllers.HelpController;
import controllers.HomeController;
import controllers.LeaderBoardController;
import controllers.PersonalPanelController;
import controllers.SettingsController;
import enums.Function;
import javafx.application.Application;
import javafx.stage.Stage;
import models.QuestionModel;
import models.UserModel;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

/**
 * Note: Casting used in creating new instance of controller, inappropriate use
 * may lead to a failure.
 * 
 *
 */
public class Main extends Application {

	// constants
	public static final int NUMLOWERBOUND = 1;
	public static final int NUMUPPERBOUND = 99;

	// stages
	private Stage _primaryStage;
	private Stage _helpStage;

	// scenes
	private Pane _homePage;
	private Pane _foundationBoard;
	private Pane _leaderBoard;
	private Pane _personalPanel;
	private Pane _settings;
	private Pane _helpScene;

	// controllers
	private HomeController _homePageController;
	private FoundationBoardController _foundationBoardController;
	private LeaderBoardController _leaderBoardController;
	private SettingsController _settingsController;
	private HelpController _helpSceneController;
	private PersonalPanelController _personalPanelController;

	@Override
	public void start(Stage primaryStage) {
		_primaryStage = primaryStage;
		_primaryStage.setMinHeight(640);
		_primaryStage.setMinWidth(1000);

		// initialize models
		QuestionModel.getInstance();
		UserModel.getInstance();
		System.out.println("Models successfully loaded...");

		// initialize controllers
		_homePageController = new HomeController(this);
		_foundationBoardController = new FoundationBoardController(this);
		_leaderBoardController = new LeaderBoardController(this);
		_personalPanelController = new PersonalPanelController(this);
		_settingsController = new SettingsController(this);
		_helpSceneController = new HelpController();
		System.out.println("Controllers successfully initialized...");

		// load FXMLs
		_homePage = loadScene("Home.fxml", _homePageController);
		_foundationBoard = loadScene("FoundationBoard.fxml", _foundationBoardController);
		_leaderBoard = loadScene("LeaderBoard.fxml", _leaderBoardController);
		_personalPanel = loadScene("PersonalPanel.fxml", _personalPanelController);
		_settings = loadScene("Settings.fxml", _settingsController);
		_helpScene = loadScene("Help.fxml", _helpSceneController);
		System.out.println("Views successfully loaded...");
		System.out.println("All done... Home page opening");

		showHome();
	}

	public void switchScene(Function function) {
		switch (function) {
		case HOME:
			showScene(_primaryStage, _homePage);
			break;
		case PRACTISE:
		case MATH:
			showScene(_primaryStage, _foundationBoard);
			_foundationBoardController.setFunction(function);
			break;
		case SCORE:
			showScene(_primaryStage, _leaderBoard);
			_leaderBoardController.initData();
			break;
		case SETTINGS:
			showScene(_primaryStage, _settings);
			break;
		default:
			throw new RuntimeException(
					"Funtions other than HOME,PRACTISE,MATH,SCORE,or SETTINGS are not supported in this method.\n"
							+ "For showing help scene, use showHelp() instead.");
		}
	}

	public void showHelp(Function f) {
		if (_helpStage != null) {
			_helpStage.show();
			_helpStage.toFront();
		} else {
			_helpStage = new Stage();
			_helpStage.setMinHeight(700);
			_helpStage.setMinWidth(1200);
			showScene(_helpStage, _helpScene);
			_helpSceneController.switchTo(f);
		}
	}

	public void showHome() {
		switchScene(Function.HOME);
	}

	public void showPersonalPanel(String userName) {
		showScene(_primaryStage, _personalPanel);
		_personalPanelController.showPersonalHistory(userName);
	}

	/**
	 * Get a FXMLLoader that loads the fxml file into a root pane and set the
	 * controller passes in as its controller.
	 * 
	 * @param fxml
	 * @param controller
	 * @return Scene loaded from the fxml file
	 */
	public Pane loadScene(String fxml, Object controller) {
		// loading fxml from FXML loader
		FXMLLoader loader = new FXMLLoader();
		InputStream in = Main.class.getResourceAsStream("/views/" + fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Main.class.getResource("/views/" + fxml));
		loader.setController(controller);
		Pane root = null;
		try {
			root = (Pane) loader.load(in);
			HBox.setHgrow(root, Priority.ALWAYS);
			VBox.setVgrow(root, Priority.ALWAYS);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return root;
	}

	/**
	 * Make the root showing on the scene of the stage. If the root is null, a
	 * runtime exception will be thrown. If the root cannot be set, an error dialog
	 * will be popped up.
	 *
	 * @param stage
	 * @param root
	 */
	public void showScene(Stage stage, Pane root) {
		try {
			if (root == null) {
				throw new RuntimeException("The root can not be null.");
			}

			Scene scene = stage.getScene();
			if (scene == null) {
				scene = new Scene(root);
				stage.setScene(scene);
			} else {
				scene.setRoot(root);
			}
			stage.show();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error: Could Not Load Page!");
			alert.setHeaderText("Could not load the page!");
			alert.setContentText("Sorry! An error happened and the page cannot be loaded.");

			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		System.out.println("Loading...");
		launch(args);
	}

}
