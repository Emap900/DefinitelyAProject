package application;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;

public class QuestionSetEditPanelController {

	@FXML
	private JFXListView<?> questionSetList;

	@FXML
	private JFXButton confirmBtn;

	@FXML
	private JFXButton addQuestionBtn;

	@FXML
	private JFXListView<?> questionList;

	@FXML
	private JFXButton delteQuestionBtn;
	
	private Stage _editPanelStage;

	public void initData(Stage stage) {
		_editPanelStage = stage;
		
		
	}

	@FXML
	void addNewQuestion(ActionEvent event) {

	}

	@FXML
	void confirmCreation(ActionEvent event) {

	}

	@FXML
	void deleteSelectedQuestion(ActionEvent event) {

	}
}
