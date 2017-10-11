package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LeaderBoardController implements Initializable {

	@FXML
	private JFXTextField _nameSearchField;
	@FXML
	private JFXListView<?> _normalModeListView;
	@FXML
	private JFXListView<?> _endlessModeListView;
	private Main _main;

	@FXML
	void backToHome(ActionEvent event) {
		_main.showHome();
	}

	@FXML
	void personalLogBtnClicked(ActionEvent event) {
		// TODO
	}

	public void setParent(Main main) {
		_main = main;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
