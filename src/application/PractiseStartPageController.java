package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PractiseStartPageController implements Initializable {

	@FXML
	private TextField _numberTextField;

	@FXML
	private Button _specificPractiseButton;

	@FXML
	private Button _autoGenerateButton;

	@FXML
	private Label _tipMessage;

	private FoundationBoardController _parentController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		/*
		 * Add a text change listener to the user name text field. When the user input
		 * is changed, check is the new character the user typed a non-alphanumeric
		 * character. If the character is non-alphanumeric, delete the new character and
		 * highlight the tip message by setting the text color to be red. Otherwise
		 * change the message back to its default style.
		 */
		_numberTextField.textProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue.isEmpty()) {
				
				// check are all characters in the user input digits 
				boolean isNumber = true;
				for (char ch:newValue.toCharArray()) {
					if (!Character.isDigit(ch)) {
						isNumber = false;
					}
				}
				
				if (!isNumber) {
					// unto typing
					_numberTextField.setText(oldValue);
					_tipMessage.setTextFill(Color.RED);
				} else if (Integer.parseInt(newValue) < 0 || Integer.parseInt(newValue) > 99) {
					_tipMessage.setTextFill(Color.RED);
				} else {
					//TODO need to set back to default, current just use black
					_tipMessage.setTextFill(Color.BLACK);
				}
			}

		});
	}

	public void setParent(FoundationBoardController parentController) {
		_parentController = parentController;
	}

	@FXML
	public void specificPractiseButtonClicked() {
		// TODO
	}

	@FXML
	public void autoGenerateButtonClicked() {
		// TODO
	}

}
