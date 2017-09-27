package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModeChoosingSceneController {

	@FXML
	private Button _homeButton;

	@FXML
	private Button _normalModeButton;

	@FXML
	private Button _endlessModeButton;

	private FoundationBoardController _parentController;

	public void setParent(FoundationBoardController parentController) {
		_parentController = parentController;
	}

	@FXML
	public void homeButtonClicked() {
		_parentController.returnHome();
	}

	@FXML
	public void normalModeButtonClicked() {
		// TODO
	}

	@FXML
	public void endlessModeButtonClicked() {
		// TODO
	}

}
