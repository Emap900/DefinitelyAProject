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

		// disable the specificPractiseButton as initially the user input is empty
		_specificPractiseButton.setDisable(true);

		/*
		 * Add a text change listener to the user name text field. When the user input
		 * is changed, check is the new character the user typed a non-alphanumeric
		 * character. If the character is non-alphanumeric, delete the new character and
		 * highlight the tip message by setting the text color to be red. Otherwise
		 * change the message back to its default style.
		 */
		_numberTextField.textProperty().addListener((observable, oldValue, newValue) -> {

			// boolean value representing that should the specificPractiseButton be disabled
			boolean toBeDisabled = true;

			if (!newValue.isEmpty()) {

				// check are all characters in the user input digits
				boolean isNumber = true;
				for (char ch : newValue.toCharArray()) {
					if (!Character.isDigit(ch)) {
						isNumber = false;
					}
				}

				if (!isNumber) { // check is the input a number
					// unto typing
					_numberTextField.setText(oldValue);
					_tipMessage.setTextFill(Color.RED);
					toBeDisabled = true;
				} else if (Integer.parseInt(newValue) < 1 || Integer.parseInt(newValue) > 99) { // check is the input in
					_numberTextField.setText(oldValue); // the correct range
					_tipMessage.setTextFill(Color.RED);
					toBeDisabled = false;
				} else { // user input is valid
					// TODO need to set back to default, current just use black
					_tipMessage.setTextFill(Color.BLACK);
					toBeDisabled = false;
				}

			} else { // if user input is empty
				// TODO need to set back to default, current just use black
				_tipMessage.setTextFill(Color.BLACK);
				toBeDisabled = true;
			}

			// disable the specificPractiseButton if needed
			_specificPractiseButton.setDisable(toBeDisabled);
		});
	}

	public void setParent(FoundationBoardController parentController) {
		_parentController = parentController;
	}

	@FXML
	public void specificPractiseButtonClicked() {
		_parentController.startPractise(Integer.parseInt(_numberTextField.getText()));
	}

	@FXML
	public void autoGenerateButtonClicked() {
		_parentController.startPractise(null);
	}

}
