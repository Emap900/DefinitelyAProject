package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class StatisticsBarController {

	// TODO
	@FXML
	private ListView<Node> _resultListView;

	/**
	 * Append the correctness of the current number to the list view of the
	 * statistics bar. Each time appendResult is called, there will be a new record
	 * added. Only call this method when the current question is finished.
	 * 
	 * @param isCorrect
	 */
	public void appendResult(boolean isCorrect) {
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

	/**
	 * 
	 * @return number of questions been recorded in the statistics bar
	 */
	public int getNumOfRecords() {
		return _resultListView.getItems().size();
	}

}
