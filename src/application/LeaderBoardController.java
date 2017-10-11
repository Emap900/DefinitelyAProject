package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LeaderBoardController implements Initializable {

	@FXML
	private JFXTextField _nameSearchField;
	@FXML
	private JFXListView<UserDataTuple> _normalModeListView;
	@FXML
	private JFXListView<UserDataTuple> _endlessModeListView;
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
		// load observable lists
		ObservableList<UserDataTuple> normalRanking = FXCollections.observableArrayList();
		ObservableList<UserDataTuple> endlessRanking = FXCollections.observableArrayList();
		normalRanking.addAll(UserModel.getInstance().getRankingList(Mode.NORMALMATH));
		endlessRanking.addAll(UserModel.getInstance().getRankingList(Mode.ENDLESSMATH));

		// setup ListViews
		_normalModeListView.setItems(normalRanking);
		_normalModeListView.setCellFactory(lv -> new UserRankListViewCell());
		_endlessModeListView.setItems(endlessRanking);
		_endlessModeListView.setCellFactory(lv -> new UserRankListViewCell());

	}

}
