package application;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class StatisticsBarController {

	@FXML
	private Label _titleLabel;

	@FXML
	private Label _infoLabel;

	// TODO
	@FXML
	private ListView<Node> _resultListView;

	/**
	 * A map that has keys that are the answer to the questions that the user did
	 * wrong, and values of the number of times user got it wrong
	 */
	private Map<String, Integer> _wrongQuestions;

	/**
	 * Set the title to show on statistics bar
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		_titleLabel.setText(title);
	}

	/**
	 * Set the information to show on statistics bar
	 * 
	 * @param info
	 */
	public void setInfo(String info) {
		_infoLabel.setText(info);
	}

	/**
	 * Append the correctness of the current number to the list view of the
	 * statistics bar. Each time appendResult is called, there will be a new record
	 * added. Only call this method when the current question is finished.
	 * 
	 * @param correctAns
	 * @param isCorrect
	 */
	public void appendResult(String correctAns, boolean isCorrect) {
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

			// update wrong questions map
			if (!_wrongQuestions.containsKey(correctAns)) {
				_wrongQuestions.put(correctAns, 1);
			} else {
				int timesGotWrong = _wrongQuestions.get(correctAns);
				_wrongQuestions.put(correctAns, timesGotWrong++);
			}
		}
		_resultListView.getItems().add(result);
	}

	/**
	 * Get the number of results currently stores in StatisticsBarController (number
	 * of questions already done)
	 * 
	 * @return number of results currently stored
	 */
	public int getNumResults() {
		// TODO Auto-generated method stub
		return _resultListView.getItems().size();
	}

}
