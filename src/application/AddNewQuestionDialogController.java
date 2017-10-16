package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddNewQuestionDialogController implements Initializable{

    @FXML
    private JFXTextField questionTextField;

    @FXML
    private JFXTextField answerTestField;

    @FXML
    private JFXButton confirmAdd;

    private String _setName;
    
    private QuestionSetEditPanelController _parent;
    
    public void setParent(QuestionSetEditPanelController parent) {
    	_parent = parent;
    }
    @FXML
    void addQuestion(ActionEvent event) {
    	String question = questionTextField.getText();
    	String answer = answerTestField.getText();
    	QuestionModel.getInstance().addQuestionToQuestionSet(_setName, question, answer);
    	_parent.loadQuestions();
    }


	public void initData(String setName) {
		_setName = setName;
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
