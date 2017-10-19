package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import application.Main;
import enums.Function;
import javafx.event.ActionEvent;

public class HomeController {
	@FXML
	private Button _practiseBtn;
	@FXML
	private Button _playBtn;
	@FXML
	private Button _scoreBtn;
	@FXML
	private Button _settingsBtn;
	@FXML
	private Button _helpBtn;

	private Main _main;

	public void setParent(Main main) {
		_main = main;
	}

	// Event Listener on Button[#_practiseBtn].onAction
	@FXML
	public void goPractise(ActionEvent event) {
		_main.showFoundationBoard(Function.PRACTISE);
	}

	// Event Listener on Button[#_playBtn].onAction
	public void goPlay(ActionEvent event) {
		_main.showFoundationBoard(Function.MATH);
	}

	// Event Listener on Button[#_scoreBtn].onAction
	@FXML
	public void showScore(ActionEvent event) {
		_main.switchScene(Function.SCORE);
	}

	// Event Listener on Button[#_settingsBtn].onAction
	@FXML
	public void goSettings(ActionEvent event) {
		_main.switchScene(Function.SETTINGS);
	}

	// Event Listener on Button[#_helpBtn].onAction
	@FXML
	public void showHelp(ActionEvent event) {
		_main.Help(Function.HELP);
	}
}
