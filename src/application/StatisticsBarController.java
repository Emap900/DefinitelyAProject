package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class StatisticsBarController {

	@FXML
	private Label _titleLabel;

	@FXML
	private Label _infoLabel;

	// TODO
	@FXML
	private ListView<Node> _resultListView;

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

}
