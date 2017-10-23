package controllers;

import application.Main;
import enums.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class HelpController {
	// @FXML
	// private Button previous;
	// @FXML
	// private ImageView imageView;
	// @FXML
	// private Button next;
	// @FXML
	// private Label instruction;
	//
	// private int _currentPage;

	@FXML
	private StackPane background;

	@FXML
	void helpEndlessMode(ActionEvent event) {
		String title = "What is an Endless Mode Math Game?";
		String body = "In an Endless Mode Math Game, you will be answering math questions \n"
				+ "randomly picked from the default question set (see \"What is a question set\" \n"
				+ "for help). The game will not finish untill you clicked the 'Skip the rest and \n"
				+ "finish' button.";
		Main.showInfoDialog(title, body, null, background);
	}

	@FXML
	void helpMaxTrailNum(ActionEvent event) {
		String title = "How to set the maximum number of tries?";
		String body = "1. Go to \"Settings\".\n"
				+ "2. Under \"Recording time and maximum retry number\" block, find \"Maximum number of tries\" setting.\n"
				+ "3. Enter a number greater than 0 in the text field.\n"
				+ "* The maximum number of tries will be automatically saved when you click the 'Home' button if the number \n"
				+ "  is greater than 0.";
		Main.showInfoDialog(title, body, null, background);
	}

	@FXML
	void helpNormalMode(ActionEvent event) {
		String title = "What is a Normal Mode Math Game?";
		String body = "In an Normal Mode Math Game, you will be answering math questions randomly picked \n"
				+ "from the selected question list if there is one (see \"What is a question list\" for help).\n"
				+ "You can customize the size and the questions in the question list (see \"How to customize \n"
				+ "the question list?\" for help). If not customized, the default number of questions in the \n"
				+ "question list will be 10, and the questions will be randomly selected from the default \n"
				+ "question set (see \"What is a question set\" for help).";
		Main.showInfoDialog(title, body, null, background);
	}

	@FXML
	void helpPractiseMode(ActionEvent event) {
		String title = "How to use Practise Mode?";
		String body = "In Practise Mode, you can either choose to practise pronouncing a specific number in Maori \n"
				+ "or let the system randomly generate numbers for you to practise. \n"
				+ "If you want to choose a specific number to practise, enter the number you want to practise in the \n"
				+ "text field on the practise mode start page and click the 'Practise This Number' button to start practising.";
		Main.showInfoDialog(title, body, null, background);
	}

	@FXML
	void helpQuestionList(ActionEvent event) {
		String title = "What is a question list?";
		String body = "A question list is a list of math questions to be asked in a Normal Mode Math Game. \n"
				+ "Both the size of the question list and the questions inside the question list can be customized (see \"How to \n"
				+ "customize the question list?\" for more details).";
		Main.showInfoDialog(title, body, null, background);
	}

	@FXML
	void helpQuestionListCustomize(ActionEvent event) {
		String title = "How to customize the question list?";
		String body = "To customize the question list, you need to first select a question set in settings. \n"
				+ "Then you can either set the length of the question list and let the system automatically \n "
				+ "generate a question list from the chosen question set for you, or pick your own questions \n "
				+ "list from the chosen question set. \n\n"
				+ "- To let the system automatically generate a question list, first go to \"settings\", under \n"
				+ "  the \"Choose a question set and generate your own question list\" block, select a question \n"
				+ "  set from the combo box in \"Question sets\" setting and find the \"Question list\" setting. \n"
				+ "  Enter the size of the question list in the text field and click the 'Auto Generate' button \n "
				+ "  to let the system auto-generate a question list. You can disable the auto-generating function \n"
				+ "  of the system by clicking the 'Auto Generate' button again. \n\n"
				+ "- To pick your own question list, go to \"Settings\", also first select a question set, and \n"
				+ "  click the 'Pick yourself' button in \"Customize question list\" setting. A new window will \n"
				+ "  be opened for you to pick questions from the selected question set. Click the '>>' button to \n"
				+ "  add a question to the question list, or click the '<<' button to remove a question from the \n"
				+ "  question list. After finished editing the question list, click the 'Confirm' button and the \n"
				+ "  question list will be saved to the system. \n\n"
				+ "* Note the customized question list will not be saved locally, therefore if you want to \n"
				+ "  customize your own question list, you need to pick your own question list every time you \n"
				+ "  close and restart the program.";
		Main.showInfoDialog(title, body, null, background);
	}

	@FXML
	void helpQuestionSet(ActionEvent event) {

	}

	@FXML
	void helpQuestionSetEditing(ActionEvent event) {

	}

	@FXML
	void helpRecordingTime(ActionEvent event) {

	}

	@FXML
	void helpScore(ActionEvent event) {

	}

	// private void show() {
	// switch (_currentPage) {
	// case 1:
	// case 2:
	// case 3:
	// case 4:
	// case 5:
	// instruction.setText("Practise Instructions");
	// break;
	// case 6:
	// case 7:
	// case 8:
	// case 9:
	// case 10:
	// instruction.setText("Math Game Instructions");
	// break;
	// case 11:
	// case 12:
	// case 13:
	// instruction.setText("Score Instructions");
	// break;
	// case 14:
	// instruction.setText("d");
	// break;
	// default:
	// instruction.setText("e");
	// break;
	//
	// }
	//
	// Image img = new Image(Main.class.getResourceAsStream("/help/" + _currentPage
	// + ".PNG"));
	// imageView.setImage(img);
	// }

	// public void switchTo(Function f) {
	// switch (f) {
	// case PRACTISE:
	// _currentPage = 1;
	// break;
	// case MATH:
	// _currentPage = 6;
	// break;
	// case SCORE:
	// _currentPage = 11;
	// break;
	// case SETTINGS:
	// _currentPage = 4;
	// break;
	// default:
	// _currentPage = 1;
	// break;
	// }
	// show();
	// }

	// @FXML
	// public void next(ActionEvent event) {
	// if (_currentPage < 13) {
	// _currentPage++;
	// }
	// show();
	// }
	//
	// @FXML
	// public void previous(ActionEvent event) {
	// if (_currentPage > 1) {
	// _currentPage--;
	// }
	// show();
	// }

}
