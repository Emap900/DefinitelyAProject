package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelpController{
	@FXML
	private ImageView image;
	@FXML
	private TextField txt; 			 	

	public void initData(Function f) {
		//txt.setText("abc");
		 //TODO Auto-generated method stub
		Image img = new Image("file:123.PNG");
		image = new ImageView(img);
	}

}
