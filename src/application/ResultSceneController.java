package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;

public class ResultSceneController {
	@FXML
	private Label _correctness;
	@FXML
	private HBox _usrAnsBox;
	@FXML
	private Label _usrAnsLabel;
	@FXML
	private HBox _correctAnsBox;
	@FXML
	private Label _correctAnsLabel;
	@FXML
	private JFXButton _replayBtn;
	@FXML
	private JFXButton _retryBtn;
	@FXML
	private JFXButton _nextBtn;
	@FXML
	private JFXButton _finishBtn;
	private FoundationBoardController _parentController;

	// Event Listener on Button[#_replayBtn].onAction
	@FXML
	public void replayBtnClicked(ActionEvent event) {
		Task<Void> replay = new Task<Void>() {
			@Override
			public Void call() {
				System.gc();

				try {
					AudioClip replay = new AudioClip(
							new File(QuestionModel.getInstance().currentAnswer() + ".wav").toURI().toURL().toString());
					replay.play();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		new Thread(replay).start();
	}

	// Event Listener on Button[#_retryBtn].onAction
	@FXML
	public void retryBtnClicked(ActionEvent event) {
		_parentController.showQuestionScene();
	}

	// Event Listener on Button[#_nextBtn].onAction
	@FXML
	public void nextBtnClicked(ActionEvent event) {
		_parentController.showNextQuestion();
	}

	// Event Listener on Button[#_finishBtn].onAction
	@FXML
	public void finishBtnClicked(ActionEvent event) {

		_parentController.finish();

	}

	public void setParent(FoundationBoardController controller) {
		_parentController = controller;
	}

	/**
	 * Tell controller the correctness of the answer.
	 * 
	 * @param isCorrect
	 */
	public void resultIsCorrect(boolean isCorrect) {
		if (!isCorrect) {
			_correctness.setText("Wrong");
		} else {
			_correctness.setText("Correct");
		}
	}

	/**
	 * Tell controller if the user has another chance to retry
	 * 
	 * @param canRetry
	 */
	public void setCanRetry(boolean canRetry) {
		if (canRetry) {
			_retryBtn.setDisable(false);
		} else {
			_retryBtn.setDisable(true);
		}
	}

	/**
	 * Tell controller what the user said
	 * 
	 * @param userAnswer
	 */
	public void setUserAnswer(String userAnswer) {
		if (userAnswer != null && !userAnswer.isEmpty()) {
			_usrAnsLabel.setText(userAnswer);
			_usrAnsBox.setVisible(true);
		}
	}

	/**
	 * Tell ResultSceneController that this question is the final question and
	 * disable the "next question" button.
	 * 
	 * @param isFinal
	 */
	public void setFinal(boolean isFinal) {
		if (isFinal) {
			_nextBtn.setText("Final question");
			_nextBtn.setDisable(true);
		} else {
			_nextBtn.setText("Next question");
			_nextBtn.setDisable(false);
		}

	}

	/**
	 * Show the hint which is the correct answer (Maori word) for the question
	 * 
	 * @param correctWord
	 */
	public void showCorrectAnswer(String correctWord) {
		if (correctWord != null && !correctWord.isEmpty()) {
			_correctAnsLabel.setText(correctWord);
			_correctAnsBox.setVisible(true);
		}
	}
}
