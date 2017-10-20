package controllers;

import javafx.fxml.FXML;

import java.io.File;
import java.net.MalformedURLException;

import com.jfoenix.controls.JFXButton;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import models.QuestionModel;

public class ResultSceneController {

	// Labels
	@FXML
	private Label _correctness;
	@FXML
	private Label _usrAnsLabel;
	@FXML
	private Label _correctAnsLabel;

	// Containers
	@FXML
	private HBox _usrAnsBox;
	@FXML
	private HBox _correctAnsBox;

	// Buttons
	@FXML
	private JFXButton _replayBtn;
	@FXML
	private JFXButton _retryBtn;
	@FXML
	private JFXButton _nextBtn;
	@FXML
	private JFXButton _finishBtn;

	private FoundationBoardController _parentController;

	public ResultSceneController(FoundationBoardController foundationBoardController) {
		_parentController = foundationBoardController;
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
		if (userAnswer == null) {
			_usrAnsBox.setVisible(false);
		} else {
			if (!userAnswer.isEmpty()) {
				_usrAnsLabel.setText(userAnswer);
			} else {
				_usrAnsLabel.setText("No Answer");
			}
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
	 * Show the hint which is the correct answer (Maori word) for the question
	 * 
	 * @param correctWord
	 */
	public void showCorrectAnswer(String correctWord) {
		if (correctWord != null && !correctWord.isEmpty()) {
			_correctAnsLabel.setText(correctWord);
			_correctAnsBox.setVisible(true);
		} else {
			_correctAnsBox.setVisible(false);
		}
	}

	@FXML
	void finishBtnClicked(ActionEvent event) {
		_parentController.finish();
	}

	@FXML
	void nextBtnClicked(ActionEvent event) {
		_parentController.showNextQuestion();
	}

	@FXML
	void replayBtnClicked(ActionEvent event) {
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

	@FXML
	void retryBtnClicked(ActionEvent event) {
		_parentController.showQuestionScene();
	}

}
