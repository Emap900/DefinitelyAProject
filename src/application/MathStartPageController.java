package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MathStartPageController implements Initializable {

	@FXML
	private Button _normalModeButton;

	@FXML
	private Button _endlessModeButton;

	@FXML
	private TextField _userNameTF;

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
		_userNameTF.textProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue.isEmpty()) {

				// check are all characters in the user input alphanumeric
				boolean isAlphanumeric = true;
				for (char ch : newValue.toCharArray()) {
					if (!Character.isDigit(ch) && !Character.isLetter(ch)) {
						isAlphanumeric = false;
					}
				}

				if (!isAlphanumeric) {
					// unto typing
					_userNameTF.setText(oldValue);
					_tipMessage.setTextFill(Color.RED);
				} else {
					// TODO need to set back to default, current just use black
					_tipMessage.setTextFill(Color.BLACK);
				}
			} else { // if user input is empty
				// TODO need to set back to default, current just use black
				_tipMessage.setTextFill(Color.BLACK);
			}

		});
	}

	public void setParent(FoundationBoardController parentController) {
		_parentController = parentController;
	}

	@FXML
	public void normalModeButtonClicked() {
		if (_userNameTF.getText().isEmpty()) {
			// if the user didn't enter his/her name, use "Guest Player" as the name and
			// start the game. As space cannot exist in user's input, there is no need to
			// worry about that user might type this name as his/her name.
			_parentController.startMathGame(Mode.NORMALMATH, "Guest Player");
		} else {
			_parentController.startMathGame(Mode.NORMALMATH, _userNameTF.getText());
		}
	}

	@FXML
	public void endlessModeButtonClicked() {
		if (_userNameTF.getText().isEmpty()) {
			_parentController.startMathGame(Mode.ENDLESSMATH, "Guest Player");
		} else {
			_parentController.startMathGame(Mode.ENDLESSMATH, _userNameTF.getText());
		}
	}

}
