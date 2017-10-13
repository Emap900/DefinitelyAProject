package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class UserRankListViewCell extends ListCell<UserDataTuple> {

	@FXML
	private HBox _cell;

	@FXML
	private Label _rankLabel;

	@FXML
	private Label _nameLabel;

	@FXML
	private Label _scoreLabel;

	public UserRankListViewCell() {
		// load the view for the cell
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserRankListCell.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void updateItem(UserDataTuple item, boolean empty) {
		super.updateItem(item, empty);

		if (empty || item == null) {
			setText(null);
			setGraphic(null);
		} else {
			_rankLabel.setText(item.getRank() + "");
			_nameLabel.setText(item.getName());
			_scoreLabel.setText(item.getHighestScore() + "");

			setText(null);
			setGraphic(_cell);
		}
	}

}
