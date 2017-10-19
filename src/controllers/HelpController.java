package controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import enums.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelpController implements Initializable {
	@FXML
	private Button previous;
	@FXML
	private ImageView imageView;
	@FXML
	private Button next;
	@FXML
	private Label instruction;

	private List<Image> pages = new ArrayList<Image>();

	private int _currentPage;

	public void initData(Function f) {
		imageView.setImage(pages.get(0));
		switchTo(f);
	}

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

		imageView.setImage(pages.get(_currentPage - 1));
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// load image for practise tips
		for (int i = 1; i < 6; i++) {
			pages.add(new Image(Main.class.getResource("/help/Practise" + i + ".PNG").toString()));
		}
		// load image for math game tips
		for (int i = 1; i < 6; i++) {
			pages.add(new Image(Main.class.getResource("/help/Math" + i + ".PNG").toString()));
		}
		// load image for score tips
		for (int i = 1; i < 4; i++) {
			pages.add(new Image(Main.class.getResource("/help/Score" + i + ".PNG").toString()));
		}
	}

}
