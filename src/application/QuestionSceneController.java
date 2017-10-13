package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXProgressBar;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class QuestionSceneController implements Initializable {
	@FXML
	private Label _question;
	@FXML
	private JFXProgressBar _progressBar;
	@FXML
	private Button _recordBtn;
	private FoundationBoardController _parentController;
	private String _answer;

	/**
	 * Make a link to the FoundationBoardController
	 * 
	 * @param parentController
	 */
	public void setParent(FoundationBoardController parentController) {
		_parentController = parentController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		QuestionModel questionModel = QuestionModel.getInstance();
		_question.setText(questionModel.currentQuestion());
		_answer = questionModel.currentAnswer();
	}

	/**
	 * Set the question to show on screen
	 * 
	 * @param question
	 */
	public void setQuestion(String question, String answer) {
		_question.setText(question);
		_answer = answer;
	}

	// Event Listener on Button[#_recordBtn].onAction
	@FXML
	public void recordBtnClicked(ActionEvent event) {

		// TODO ask questionModel to increment trial number
		_recordBtn.setText("Recording...");
		_recordBtn.setDisable(true);
		Timer timer = new Timer();

		// set up a timer to update the progress indicator every 10ms, until reached
		// 100% after the recording time (get from config) finished
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 0;
			double total = getRecordingTime() * 100.0;

			@Override
			public void run() {
				_progressBar.setProgress(i / total);
				i++;
				if (i == (int) total) {
					_progressBar.setProgress(100);
					timer.cancel();
				}
			}

			/**
			 * Get the recording time from config file.
			 *
			 * @return
			 */
			private double getRecordingTime() {
				// TODO read local config file
				File configFile = new File("config.properties");

				// default recording time is 3 seconds
				String recordingTime = "3.0";

				try {
					FileReader reader = new FileReader(configFile);
					Properties props = new Properties();
					props.load(reader);

					recordingTime = props.getProperty("recordingTime");

					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return Double.parseDouble(recordingTime);
			}

		}, 0, 10);

		Task<Void> record = new Task<Void>() {
			@Override
			public Void call() {
				new BashProcess("./MagicStaff.sh", "record", _answer);

				try {
					Thread.sleep(1000); // TODO
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		record.setOnSucceeded(e -> {
			_parentController.showResult();
		});

		Thread th = new Thread(record);
		th.setDaemon(true);
		th.start();

	}

}
