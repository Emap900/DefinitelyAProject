package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PractiseChoosingSceneController {
	
	@FXML
	private Button _homeButton;
	
	@FXML
	private TextField _numberTextField;
	
	@FXML
	private Button _specificPractiseButton;
	
	@FXML
	private Button _autoGenerateButton;
	

	private FoundationBoardController _parentController;

	public void setParent(FoundationBoardController parentController) {
		_parentController = parentController;
	}
	
	@FXML
	public void homeButtonClicked() {
		_parentController.returnHome();
	}
	
	@FXML
	public void specificPractiseButtonClicked() {
		//TODO
	}

	@FXML
	public void autoGenerateButtonClicked() {
		//TODO
	}


}
