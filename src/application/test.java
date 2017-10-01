package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class test extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Load Image");

		StackPane sp = new StackPane();
		Image img;
		try {
			img = new Image(new File("123.png").toURI().toURL().toString());
			ImageView imgView = new ImageView(img);
			imgView.setFitWidth(500);
			imgView.setFitHeight(500);
			sp.getChildren().add(imgView);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		//Adding HBox to the scene
		Scene scene = new Scene(sp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}