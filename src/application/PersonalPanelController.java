package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
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
	private AreaChart<String, Number> _normalModeChart;

	@FXML
	private Label _endlessModeRank;

	@FXML
	private Label _endlessModeScore;

	@FXML
	private AreaChart<String, Number> _endlessModeChart;

	private Main _main;

	@FXML
	void backToHome(ActionEvent event) {
		_main.showHome();
	}

	@FXML
	void showLeaderBoard(ActionEvent event) {
		_main.switchScene(Function.SCORE);
	}

	public void setParent(Main main) {
		_main = main;
	}

	public void showPersonalHistory(String userName) {
		UserModel model = UserModel.getInstance();
		// show name
		_name.setText(userName);

		// show latest score and rank
		_latestScore.setText(model.getLatestGameScore(userName));
		_latestMode.setText(model.getLatestGameMode(userName));

		// show data of normal mode
		_normalModeRank.setText(model.getRank(Mode.NORMALMATH, userName)); // rank
		_normalModeScore.setText(model.getPersonalBest(Mode.NORMALMATH, userName)); // score

		// add history to the chart
		int[] history = model.getPersonalHistory(Mode.NORMALMATH, userName);
		XYChart.Series<String, Number> serie = new Series<String, Number>();
		for (int i = 0; i < history.length; i++) {
			serie.getData().add(new Data<String, Number>("" + i, history[i]));
		}
		_normalModeChart.getData().add(serie);

		// show data of endless mode
		_endlessModeRank.setText(model.getRank(Mode.ENDLESSMATH, userName)); // rank
		_endlessModeScore.setText(model.getPersonalBest(Mode.ENDLESSMATH, userName)); // score

		// add history to the chart
		history = model.getPersonalHistory(Mode.ENDLESSMATH, userName);
		serie = new Series<String, Number>();
		for (int i = 0; i < history.length; i++) {
			serie.getData().add(new Data<String, Number>("" + i, history[i]));
		}
		_endlessModeChart.getData().add(serie);

	}

}
