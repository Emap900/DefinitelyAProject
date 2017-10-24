package controllers;

import application.Main;
import enums.Function;
import javafx.event.ActionEvent;
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

	private void show() {
		switch (_currentPage) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			instruction.setText("Practise Instructions");
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			instruction.setText("Math Game Instructions");
			break;
		case 11:
		case 12:
		case 13:
			instruction.setText("Score Instructions");
			break;
		case 14:
			instruction.setText("d");
			break;
		default:
			instruction.setText("e");
			break;

		}

		Image img = new Image(Main.class.getResourceAsStream("/help/" + _currentPage + ".PNG"));
		imageView.setImage(img);
	}

	public void switchTo(Function f) {
		switch (f) {
		case PRACTISE:
			_currentPage = 1;
			break;
		case MATH:
			_currentPage = 6;
			break;
		case SCORE:
			_currentPage = 11;
			break;
		case SETTINGS:
			_currentPage = 4;
			break;
		default:
			_currentPage = 1;
			break;
		}
		show();
	}

	@FXML
	public void next(ActionEvent event) {
		if (_currentPage < 13) {
			_currentPage++;
		}
		show();
	}

	@FXML
	public void previous(ActionEvent event) {
		if (_currentPage > 1) {
			_currentPage--;
		}
		show();
	}

}
