package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.QuestionModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

public class SettingsController implements Initializable {
	@FXML
	private ComboBox<String> quesitonSetComboBox;
	@FXML
	private Button editQuestionSetBtn;
	@FXML
	private Button addNewSetBtn;
	@FXML
	private Button deleteSetBtn;
	@FXML
	private Label questionListLabel;
	@FXML
	private TextField numOfQuestionsTextFieldForRandom;
	@FXML
	private Button pickARandomListBtn;
	@FXML
	private Button pickYourselfBtn;
	@FXML
	private Button confirmBtn;

	private Stage _editPanelStage;

	private Stage _userPickingStage;
	private Main _main;
	private QuestionModel _questionModel;

	private Properties _props;

	public SettingsController(Main main) {
		_main = main;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_questionModel = QuestionModel.getInstance();
		updateSetList();

		_props = new Properties();
		try {
			_props.load(new FileInputStream("config.properties"));
			String setChosen = _props.getProperty("QSet", "Default");
			String listSize = _props.getProperty("listSize", "10");
			if (setChosen != null && quesitonSetComboBox.getItems().contains(setChosen)) {
				quesitonSetComboBox.getSelectionModel().select(setChosen);
			}
			numOfQuestionsTextFieldForRandom.setText(listSize);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Event Listener on Button[#addNewSetBtn].onAction
	@FXML
	public void addNewSet(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter your name:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			if (_questionModel.createLocalQuestionSet(result.get())) {
				openeditPanel(result.get());
			}
		}
		updateSetList();
	}

	// Event Listener on Button[#editQuestionSetBtn].onAction
	@FXML
	public void editAQuestionSet(ActionEvent event) {
		if (!quesitonSetComboBox.getSelectionModel().isEmpty()
				&& !quesitonSetComboBox.getValue().toString().equals("setABC")) {
			String currentSet = quesitonSetComboBox.getValue().toString();
			System.out.println("Step 0 ... PATH start");
			openeditPanel(currentSet);
		} else {
			permissionDeniededDialog();
		}
	}

	// Event Listener on Button[#deleteSetBtn].onAction
	@FXML
	public void deleteSet(ActionEvent event) {
		if (!quesitonSetComboBox.getSelectionModel().isEmpty()
				&& !quesitonSetComboBox.getValue().toString().equals("setABC")) {
			String setName = quesitonSetComboBox.getValue().toString();
			_questionModel.deleteLocalQuestionSet(setName);
			updateSetList();
		} else {
			permissionDeniededDialog();
		}
	}

	private void permissionDeniededDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("The set is not editable / empty");

		alert.showAndWait();
	}

	// Event Listener on Button[#pickARandomListBtn].onAction
	@FXML
	public void pickRandomList(ActionEvent event) {
		String numOfQuestions = numOfQuestionsTextFieldForRandom.getText();
		if (numOfQuestions != null) {
			String setName = quesitonSetComboBox.getValue().toString();
			_questionModel.setLengthOfQuestionList(Integer.parseInt(numOfQuestions));
			_questionModel.generateQuestionListRandom(setName);
			// TODO underlying code actually support more functionality such as user do not
			// need to specify numberOfQuestions
		}
	}

	// Event Listener on Button[#pickYourselfBtn].onAction
	@FXML
	public void pickCustomizedList(ActionEvent event) {
		_userPickingStage = new Stage();
		PickQuestionListSceneController pickSceneController = new PickQuestionListSceneController();
		Pane root = _main.loadScene("PickQuestionListScene.fxml", pickSceneController);
		_main.showScene(_userPickingStage, root);
		String setName = quesitonSetComboBox.getValue().toString();
		pickSceneController.initData(_userPickingStage, setName);
	}

	// Event Listener on Button[#confirmBtn].onAction
	@FXML
	public void confirmSetting(ActionEvent event) {
		_props = new Properties();
		try {
			_props.load(new FileInputStream("config.properties"));
			String setChosen = quesitonSetComboBox.getSelectionModel().getSelectedItem();
			String listSize = numOfQuestionsTextFieldForRandom.getText();
			if (setChosen != null && !setChosen.isEmpty()) {
				_props.setProperty("QSet", setChosen);
			}
			if (listSize != null && !listSize.isEmpty()) {
				_props.setProperty("listSize", listSize);
			}

			_props.store(new FileOutputStream("config.properties"), "System Settings");
		} catch (IOException e) {
			e.printStackTrace();
		}

		_main.showHome();
	}

	public void openeditPanel(String setName) {
		// TODO pass questionSetName to edit panel
		_editPanelStage = new Stage();
		QuestionSetEditPanelController editPanelController = new QuestionSetEditPanelController();
		Pane root = _main.loadScene("QuestionSetEditPanel.fxml", editPanelController);
		_main.showScene(_editPanelStage, root);
		editPanelController.initData(_editPanelStage, setName);
		editPanelController.setParent(_main);
	}

	private void updateSetList() {
		ObservableList ol = FXCollections.observableArrayList(_questionModel.getListOfsets());
		quesitonSetComboBox.setItems(ol);

		System.out.println(_questionModel.getListOfsets().toString());
	}
}
