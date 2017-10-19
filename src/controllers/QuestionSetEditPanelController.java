package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.stage.Stage;
import models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import application.Main;
import javafx.fxml.FXML;

public class QuestionSetEditPanelController {

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
	
	private Main _main;
	
	public void setParent(Main main) {
		_main = main;
	}
	
	public void initData(Stage stage, String setName) {
		_questionModel = QuestionModel.getInstance();
		_editPanelStage = stage;
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
		//_questionModel.addQuestionToQuestionSet(_currentSetName, question, answer);
		_newQuestionStage = new Stage();
		Scene NQScene = _main.loadScene("AddNewQuestionDialog.fxml");
		AddNewQuestionDialogController aqdController = (AddNewQuestionDialogController)NQScene.getUserData();
		aqdController.initData(_currentSetName, _newQuestionStage);
		aqdController.setParent(this);
		System.out.println("Step 1 done.");
		_main.showScene(_newQuestionStage, NQScene);
		loadQuestions();
	}

	@FXML
	public void confirmCreation(ActionEvent event) {
		_editPanelStage.close();
	}

	@FXML
	public void deleteSelectedQuestion(ActionEvent event) {
		String selectedQ = questionList.getSelectionModel().getSelectedItem();
		String key = selectedQ.split("=")[0];
		System.out.println(key); //TODO
		_questionModel.deleteQuestionFromQuestionSet(_currentSetName, key);
		loadQuestions();
	}
}
