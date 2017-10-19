package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import models.QuestionModel;

public class AddNewQuestionDialogController implements Initializable{

    @FXML
    private JFXTextField questionTextField;

    @FXML
    private JFXTextField answerTestField;

    @FXML
    private JFXButton confirmAdd;

    private String _setName;
    
    private QuestionSetEditPanelController _parent;
    
    private Stage _stage;
    
    public void setParent(QuestionSetEditPanelController parent) {
    	_parent = parent;
    }
    @FXML
    void addQuestion(ActionEvent event) {
    	//TODO add multiple questions bbb
    	String question = questionTextField.getText();
    	String answer = answerTestField.getText();
    	QuestionModel.getInstance().addQuestionToQuestionSet(_setName, question, answer);
    	_parent.loadQuestions();
    	_stage.close();
    }


	public void initData(String setName, Stage newQuestionStage) {
		_setName = setName;
		_stage = newQuestionStage;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		questionTextField.textProperty().addListener((observable, oldValue, newValue) ->{
			if (newValue.isEmpty()) {
				confirmAdd.setDisable(true);
			}else {
				confirmAdd.setDisable(false);
			}
		});
		
		answerTestField.textProperty().addListener((observable, oldValue, newValue) ->{
			if (newValue.isEmpty()) {
				confirmAdd.setDisable(true);
			}else {
				confirmAdd.setDisable(false);
			}
		});
	}

}
