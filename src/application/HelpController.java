package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelpController {
	@FXML
	private Button previous;
	@FXML
	private ImageView imageView;
	@FXML
	private Button next;
	@FXML
	private Label instruction;

	private int _currentPage;

	public void initData(Function f) {
		try {
			Image img = new Image(new File("123.PNG").toURI().toURL().toString());
			imageView.setImage(img);
			switch(f) {
			case PRACTISE:
				_currentPage = 1;
				break;
			case MATH:
				_currentPage = 2;
				break;
			case SCORE:
				_currentPage = 3;
				break;
			case SETTINGS:
				_currentPage = 4;
				break;
			default:
				_currentPage = 0;
				break;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		show();
	}
	
	private void show() {
		switch(_currentPage) {
		case 1:
			instruction.setText("a");
			break;
		case 2:
			instruction.setText("b");
			break;
		case 3:
			instruction.setText("c");
			break;
		case 4:
			instruction.setText("d");
			break;
		default:
			instruction.setText("e");
			break;
			
		}
	}

	public void switchTo(Function f) {
		
	}

}
