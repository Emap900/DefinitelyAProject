package application;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class StatisticsBarController {

	// @FXML
	// private Label _titleLabel;
	//
	// @FXML
	// private Label _infoLabel;

	// TODO
	@FXML
	private ListView<Node> _resultListView;

//	/**
//	 * Set the title to show on statistics bar
//	 * 
//	 * @param title
//	 */
//	public void setTitle(String title) {
//		_titleLabel.setText(title);
//	}

	// /**
	// * Set the information to show on statistics bar
	// *
	// * @param info
	// */
	// public void setInfo(String info) {
	// _infoLabel.setText(info);
	// }

	/**
	 * Append the correctness of the current number to the list view of the
	 * statistics bar. Each time appendResult is called, there will be a new record
	 * added. Only call this method when the current question is finished.
	 * 
	 * @param isCorrect
	 */
	public void appendResult(boolean isCorrect) {
		// TODO Auto-generated method stub
		Label result = new Label();
		// calculate the question number of the new result to append
		int questionNumber = _resultListView.getItems().size() + 1;
		if (isCorrect) {
			result.setText("Question " + questionNumber + ": Correct");
			result.setTextFill(Color.LIGHTGREEN);
		} else {
			result.setText("Question " + questionNumber + ": Wrong");
			result.setTextFill(Color.RED);
		}
		_resultListView.getItems().add(result);
	}

}
