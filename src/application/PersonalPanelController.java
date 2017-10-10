package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Label;

public class PersonalPanelController {

	@FXML
	private Label _name;

	@FXML
	private Label _latestScore;

	@FXML
	private Label _latestMode;

	@FXML
	private Label _normalModeRank;

	@FXML
	private Label _normalModeScore;

	@FXML
	private AreaChart<?, ?> _normalModeChart;

	@FXML
	private Label _endlessModeRank;

	@FXML
	private Label _endlessModeScore;

	@FXML
	private AreaChart<?, ?> _endlessModeChart;

	@FXML
	void backToHome(ActionEvent event) {
		// TODO Auto-generated method stub
	}

	@FXML
	void showLeaderBoard(ActionEvent event) {
		// TODO Auto-generated method stub
	}

	public void setParent(FoundationBoardController foundationBoardController) {
		// TODO Auto-generated method stub

	}

	public void showPersonalHistory(String _playerName) {
		// TODO Auto-generated method stub

	}

}
