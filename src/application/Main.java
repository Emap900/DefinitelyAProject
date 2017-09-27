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
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

public class Main extends Application {

	//private HomeController _homePageController;
	//private Scene _homePage;
	// private HomePageController HomePage;
	private Stage _primaryStage;
	@Override
	public void start(Stage primaryStage) {
		_primaryStage = primaryStage;
		try {
			// BorderPane root = (BorderPane)
			// FXMLLoader.load(getClass().getResource("Home.fxml"));
			// Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// TODO
			Scene homePage = loadScene("Home.fxml");
			HomeController homeController = (HomeController)homePage.getUserData();
			homeController.setParent(this);
			//_homePageController = _homePage.getUserData();
			showScene(primaryStage, homePage);
			// primaryStage.setScene(scene);
			// primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showFoundationBoard(String string) {
		// TODO Auto-generated method stub
		
	}

	public void showPractiseScene() {
		Scene practise = loadScene("PractiseChoosingScene.fxml");
		showScene(_primaryStage, practise);
	}
	public void showSettingsScene() {
		Scene settings = loadScene("Setttings.fxml");
		showScene(_primaryStage, settings);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Get a FXMLLoader that loads the fxml file into a scene and set the default
	 * style sheet application.css as its style sheet. The scene has its controller
	 * be set as its user data and controller be retrieved by calling getUserData() on the
	 * scene. Null will be returned if the scene cannot be loaded.
	 * 
	 * @param fxml
	 * @return Scene loaded from the fxml file
	 */
	private Scene loadScene(String fxml) {
		// TODO
		//loading fxml from FXML loader
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
	 * Make the scene shown on the stage. If the scene is null or the scene cannot
	 * be set, an error dialog will be popped up.
	 * 
	 * @param stage
	 * @param scene
	 */
	private void showScene(Stage stage, Scene scene) {
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


}
