package application;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

/**
 * Note: Casting used in creating new instance of controller, inappropriate use
 * may lead to a failure.
 * 
 * @author Eric Chen
 *
 */
public class Main extends Application {

	// private HomeController _homePageController;
	// private Scene _homePage;
	// private HomePageController HomePage;
	private Stage _primaryStage;
	private Stage _helpStage;
	
	private QuestionModel _questionModel;

	@Override
	public void start(Stage primaryStage) {
		_primaryStage = primaryStage;
		_questionModel.getInstance();
		showHome();
	}

	public void Help(Function f) {
		if (_helpStage != null) {
			_helpStage.show();
			_helpStage.toFront();
		} else {
			_helpStage = new Stage();
			Scene helpScene = loadScene("Help.fxml");
			HelpController helpController = (HelpController) helpScene.getUserData();
			helpController.initData(f);
			showScene(_helpStage, helpScene);
		}
	}

	public void switchScene(Function function) {
		switch (function) {
		case PRACTISE:
		case MATH:
			showFoundationBoard(function);
			break;
		case SCORE:
			Scene score = loadScene("LeaderBoard.fxml");
			LeaderBoardController controller = (LeaderBoardController) score.getUserData();
			controller.setParent(this);
			showScene(_primaryStage, score);
			break;
		// TODO
		case SETTINGS:
			Scene settings = loadScene("Settings.fxml");
			SettingsController settingsController = (SettingsController) settings.getUserData();
			settingsController.setParent(this);
			showScene(_primaryStage, settings);
			break;
		// TODO

		default:
			break;
		}
	}

	public void showFoundationBoard(Function function) {
		Scene foundationBoard = loadScene("FoundationBoard.fxml");
		FoundationBoardController fdtController = (FoundationBoardController) foundationBoard.getUserData();
		fdtController.setParent(this);
		fdtController.setFunction(function);
		showScene(_primaryStage, foundationBoard);
	}

	public void showSettingsScene() {
		Scene settings = loadScene("Setttings.fxml");
		showScene(_primaryStage, settings);
	}

	/**
	 * Get a FXMLLoader that loads the fxml file into a scene and set the default
	 * style sheet application.css as its style sheet. The scene has its controller
	 * be set as its user data and controller be retrieved by calling getUserData()
	 * on the scene. Null will be returned if the scene cannot be loaded.
	 * 
	 * @param fxml
	 * @return Scene loaded from the fxml file
	 */
	public Scene loadScene(String fxml) {
		// TODO
		// loading fxml from FXML loader
		FXMLLoader loader = new FXMLLoader();
		InputStream in = Main.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Main.class.getResource(fxml));
		Scene scene = null;
		try {
			Pane content = (Pane) loader.load(in);
			scene = new Scene(content);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.setUserData(loader.getController());
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return scene;
	}

	/**
	 * Make the scene showing on the stage. If the scene is null or the scene cannot
	 * be set, an error dialog will be popped up.
	 * 
	 * @param stage
	 * @param scene
	 */
	public void showScene(Stage stage, Scene scene) {
		try {
			if (scene == null) {
				throw new RuntimeException();
			}
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
		} catch (RuntimeException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error: Could Not Load Page!");
			alert.setHeaderText("Could not load the page!");
			alert.setContentText("Sorry! An error happened and the page cannot be loaded.");

			alert.showAndWait();
		}
	}

	public void showHome() {
		try {
			Scene homePage = loadScene("Home.fxml");
			HomeController homeController = (HomeController) homePage.getUserData();
			homeController.setParent(this);
			showScene(_primaryStage, homePage);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showPersonalPanel(String userName) {
		// TODO Auto-generated method stub
		Scene personal = loadScene("PersonalPanel.fxml");
		PersonalPanelController controller = (PersonalPanelController) personal.getUserData();
		controller.setParent(this);
		controller.showPersonalHistory(userName);
		showScene(_primaryStage, personal);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
