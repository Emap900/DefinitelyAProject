package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.QuestionModel;

public class AddNewQuestionDialogController implements Initializable {

	@FXML
	private JFXTextField questionTextField;
	@FXML
	private JFXTextField answerTextField;
	@FXML
	private Label answerFormatWarningLabel;
	@FXML
	private JFXButton confirmAdd;
	@FXML
	private VBox warningBox;

	private String _setName;

	private boolean _answerIsValid;

	private QuestionSetEditPanelController _parent;

	private Stage _stage;
	private boolean _questionIsValid;

	public void setParent(QuestionSetEditPanelController parent) {
		_parent = parent;
	}

	@FXML
	void addQuestion(ActionEvent event) {
		// TODO add multiple questions bbb
		String question = questionTextField.getText();
		String answer = answerTextField.getText();
		QuestionModel.getInstance().addQuestionToQuestionSet(_setName, question, answer);
		_parent.loadQuestions();
		// _stage.close();
	}

	public void initData(String setName, Stage newQuestionStage) {
		_setName = setName;
		_stage = newQuestionStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		answerFormatWarningLabel
				.setText("Answer must be a number between " + Main.NUMLOWERBOUND + " and " + Main.NUMUPPERBOUND);

		questionTextField.textProperty().addListener((observable, oldValue, newValue) -> {

			if (newValue.isEmpty()) {
				confirmAdd.setDisable(true);
			} else {
				ScriptEngineManager mgr = new ScriptEngineManager();
				ScriptEngine engine = mgr.getEngineByName("JavaScript");
				try {
					int answer = Integer.parseInt(engine.eval(newValue).toString());
					if (answer >= Main.NUMLOWERBOUND && answer <= Main.NUMUPPERBOUND) {
						answerTextField.setText(answer + "");
					} else {
						answerTextField.setText("");
						warningBox.setVisible(true);
						confirmAdd.setDisable(true);
					}
				} catch (ScriptException | NumberFormatException e) {
					answerTextField.setText("");
					warningBox.setVisible(true);
					confirmAdd.setDisable(true);
				}
			}
		});

		answerTextField.textProperty().addListener((observable, oldValue, newValue) -> {

			// check is the answer empty
			if (!newValue.isEmpty()) {
				warningBox.setVisible(false);
				confirmAdd.setDisable(false);
			} else {
				warningBox.setVisible(true);
				confirmAdd.setDisable(true);
			}
		});
	}

}
