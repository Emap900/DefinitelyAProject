package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ProgressIndicator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class QuestionSceneController {
	@FXML
	private Label _question;
	@FXML
	private ProgressIndicator _progressInd;
	@FXML
	private Button _recordBtn;
	@FXML
	private Button _helpBtn;
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
		// TODO Autogenerated

		// TODO ask foundationBoard? or QuestionModel? to increment trial number
		// _db.incrementTrial();
		_recordBtn.setText("Recording...");
		_recordBtn.setDisable(true);
		Timer timer = new Timer();

		// set up a timer to update the progress indicator every 10ms, until reached
		// 100% after the recording time (get from config) finished
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 0;
			// double total = getRecordingTime() * 100.0;
			double total = 300.0;

			@Override
			public void run() {
				_progressInd.setProgress(i / total);
				i++;
				if (i == (int) total) {
					_progressInd.setProgress(100);
					timer.cancel();
				}
			}

			/**
			 * Get the recording time from config file.
			 *
			 * @return
			 */
			private double getRecordingTime() {
				// TODO Auto-generated method stub
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
				// TODO
				// new BashProcess("./MagicStaff.sh", "record", _answer);

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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

	// Event Listener on Button[#_helpBtn].onAction
	@FXML
	public void helpBtnClicked(ActionEvent event) {
		// TODO Autogenerated
		_parentController.showHelp("QuestionScene");
	}

}
