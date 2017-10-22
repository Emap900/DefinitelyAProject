package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import application.Main;

public class QuestionSetEditPanelController {

	@FXML
	private StackPane background;

	// list view multiple selection to be implemented
	@FXML
	private JFXListView<?> questionSetList;

	@FXML
	private JFXButton confirmBtn;

	@FXML
	private JFXButton addQuestionBtn;

	@FXML
	private JFXListView<String> questionList;

	@FXML
	private JFXButton delteQuestionBtn;

	private Stage _editPanelStage;

	private Stage _newQuestionStage;

	private QuestionModel _questionModel;

	private List<String> _listOfQuestions;

	private String _currentSetName;

	private SettingsController _settingsController;

	public QuestionSetEditPanelController(SettingsController settingsController, Stage editPanelStage) {
		_editPanelStage = editPanelStage;
		_settingsController = settingsController;
		// stop the stage from closing if the question set is still empty and show an
		// error panel
		_editPanelStage.setOnCloseRequest(e -> {
			if (_listOfQuestions == null || _listOfQuestions.isEmpty()) {
				e.consume();
				Main.showConfirmDialog("Empty Question Set!",
						"The question set should contain at least one question. You can choose OK to delete this empty set",
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								QuestionModel.getInstance().deleteLocalQuestionSet(_currentSetName);
								settingsController.updateSetList();
								_editPanelStage.close();
							}
						}, null, background);
			}
		});
	}

	public void initData(String setName) {
		_questionModel = QuestionModel.getInstance();
		_currentSetName = setName;
		_listOfQuestions = new ArrayList<String>();
		loadQuestions();
	}

	public void loadQuestions() {
		_listOfQuestions.clear();
		List<List<String>> rawData = _questionModel.getQuestionsFromSpecificSet(_currentSetName);
		for (int i = 0; i < rawData.size(); i++) {
			List<String> temp = rawData.get(i);
			String Combined = temp.get(0) + "=" + temp.get(1);
			_listOfQuestions.add(Combined);
		}
		System.out.println("Step 2 succeed.");
		questionList.getItems().setAll(_listOfQuestions);
	}

	@FXML
	public void addNewQuestion(ActionEvent event) {

		_newQuestionStage = new Stage();
		AddNewQuestionDialogController aqdController = new AddNewQuestionDialogController();
		Pane root = Main.loadScene("AddNewQuestionDialog.fxml", aqdController);
		Main.showScene(_newQuestionStage, root);
		aqdController.initData(_currentSetName, _newQuestionStage);
		aqdController.setParent(this);

		loadQuestions();
	}

	@FXML
	public void confirmCreation(ActionEvent event) {
		if (_listOfQuestions.isEmpty()) {
			Main.showErrorDialog("Error!", "The question set should contain at least one question.", null, background);
		} else {
			_settingsController.updateSetList();
			_editPanelStage.close();
		}
	}

	@FXML
	public void deleteSelectedQuestion(ActionEvent event) {
		String selectedQ = questionList.getSelectionModel().getSelectedItem();
		String key = selectedQ.split("=")[0];
		System.out.println(key); // TODO
		_questionModel.deleteQuestionFromQuestionSet(_currentSetName, key);
		loadQuestions();
	}
}
