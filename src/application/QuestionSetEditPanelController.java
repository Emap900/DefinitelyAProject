package application;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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

	private QuestionModel _questionModel;

	private List<String> _listOfQuestions;

	private String _currentSetName;
	
	public void initData(Stage stage, String setName) {
		_questionModel = QuestionModel.getInstance();
		_editPanelStage = stage;
		
		List<List<String>> rawData = _questionModel.getQuestionsFromSpecificSet(setName);
		_listOfQuestions = new ArrayList<String>();
		for (int i = 0; i < rawData.size(); i++) {
			List<String> temp = rawData.get(i);
			String Combined = temp.get(0) + " = " + temp.get(1);
			_listOfQuestions.add(Combined);
		}
		System.out.println("Step 2 succeed.");
		questionList.getItems().setAll(_listOfQuestions);

	}

	@FXML
	public void addNewQuestion(ActionEvent event) {
		//_questionModel.addQuestionToQuestionSet(_currentSetName, question, answer);
	}

	@FXML
	void confirmCreation(ActionEvent event) {

	}

	@FXML
	void deleteSelectedQuestion(ActionEvent event) {

	}
}
