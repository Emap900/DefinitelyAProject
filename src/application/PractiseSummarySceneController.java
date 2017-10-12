package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class PractiseSummarySceneController implements Initializable {

	@FXML
	private PieChart _pieChart;

	private FoundationBoardController _parent;

	public void setParent(FoundationBoardController controller) {
		// TODO Auto-generated method stub
		_parent = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Correct", 90),
				new PieChart.Data("Wrong", 10));
		_pieChart.setData(pieChartData);
		pieChartData.get(0).getNode().setStyle("-fx-pie-color: #00c853;");
		pieChartData.get(1).getNode().setStyle("-fx-pie-color: #d50000;");

	}

}
