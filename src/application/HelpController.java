package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelpController {
	@FXML
	private Button previous;
	@FXML
	private ImageView imageView;
	@FXML
	private Button next;
	
	public void initData(Function f) {
		try {
			Image img = new Image(new File("123.PNG").toURI().toURL().toString());
			imageView.setImage(img);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
