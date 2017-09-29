package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import application.FoundationBoardController.Function;
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
		// TODO Autogenerated
		_main.showFoundationBoard(Function.PRACTISE);
	}
	// Event Listener on Button[#_playBtn].onAction
	@FXML
	public void goPlay(ActionEvent event) {
		// TODO Autogenerated
		_main.showFoundationBoard(Function.MATH);
	}
	// Event Listener on Button[#_scoreBtn].onAction
	@FXML
	public void showScore(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#_settingsBtn].onAction
	@FXML
	public void goSettings(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#_helpBtn].onAction
	@FXML
	public void showHelp(ActionEvent event) {
		// TODO Autogenerated
	}
}
