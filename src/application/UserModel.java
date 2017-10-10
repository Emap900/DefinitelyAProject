package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModel {

	private static UserModel _modelInstance;

	/**
	 * A map that has players' names as keys, and lists of result pairs as values.
	 */
	private static Map<String, User> _users;

	/**
	 * Constructor
	 */
	private UserModel() {
		_users = new HashMap<String, User>();
		File folder = new File("usrRecords");

		// generate new User Objects for the local files existed
		File[] fileList = folder.listFiles();

		for (File file : fileList) {
			if (file.isFile()) {
				// read the user's name
				String fileName = file.getName();
				String userName = fileName.substring(0, fileName.lastIndexOf("."));
				_users.put(userName, new User(userName));
			}
		}
	}

	/**
	 * Get the instance of the singleton class QuestionModel
	 * 
	 * @return the only instance of QuestionModel
	 */
	public static UserModel getInstance() {
		if (_modelInstance == null) {
			_modelInstance = new UserModel();
		}
		return _modelInstance;
	}

	/**
	 * Add a record to the player's personal history and update both the personal
	 * history and global history.
	 * 
	 * @param userName
	 * @param gameMode
	 *            (either NORMALMATH or ENDLESSMATH)
	 * @param score
	 */
	public void appendRecord(String userName, Mode gameMode, int score) {
		// TODO Auto-generated method stub
		if (!_users.containsKey(userName)) {
			User user = new User(userName);
			user.appendNewRecord(gameMode, score);
			_users.put(userName, user);
		} else {
			_users.get(userName).appendNewRecord(gameMode, score);
		}

	}

	private class User {

		private final String _name;
		private final int PRACTISE = 0;
		private final int NORMAL = 1;
		private final int ENDLESS = 2;
		private Map<Integer, List<Integer>> _results;
		private Mode _latestGameMode;
		private Integer _latestGameScore;
		private File _localFile;

		User(String name) {
			_name = name;
			_results = new HashMap<Integer, List<Integer>>();

			// load or create local file which stores user records
			// TODO currently using windows syntax, going to change that into Linux syntax
			_localFile = new File("usrRecords\\" + name + ".csv");
			if (!_localFile.exists()) {
				// create file if does not exist
				try {
					_localFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// read all the game records of the user
				List<String> recordList;
				try {
					recordList = Files.readAllLines(Paths.get(_localFile.toURI()));

					if (!recordList.isEmpty()) {
						// initialize a new user
						for (String record : recordList) {
							String[] entry = record.split(",");
							// add the records to field _results
							if (entry[0].equals("Normal")) {
								this.addToResults(NORMAL, Integer.parseInt(entry[1]));
							} else if (entry[0].equals("Endless")) {
								this.addToResults(ENDLESS, Integer.parseInt(entry[1]));
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		/**
		 * Add a new record to the user.
		 * 
		 * @param gameMode
		 * @param score
		 */
		public void appendNewRecord(Mode gameMode, int score) {
			int mode;
			switch (gameMode) {
			case NORMALMATH:
				mode = NORMAL;
				break;
			case ENDLESSMATH:
				mode = ENDLESS;
				break;
			default:
				throw new RuntimeException("Game mode can only be NORMALMATH or ENDLESSMATH");
			}

			// add the record to field _results
			addToResults(mode, score);

			// append the record to the local file
			try {
				FileWriter fw = new FileWriter(_localFile, true);
				if (mode == NORMAL) {
					fw.append("Normal" + "," + score + "\n");
				} else if (mode == ENDLESS) {
					fw.append("Endless" + "," + score + "\n");
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			_latestGameMode = gameMode;
			_latestGameScore = score;
		}

		private void addToResults(int mode, int score) {
			if (!_results.containsKey(mode)) {
				List<Integer> resultList = new ArrayList<Integer>();
				resultList.add(score);
				_results.put(mode, resultList);
			} else {
				_results.get(mode).add(score);
			}
		}

		/**
		 * 
		 * @return Name of the player.
		 */
		public String getName() {
			return _name;
		}

		/**
		 * @return The game mode of the latest game.
		 */
		public String reportLatestGameMode() {
			if (_latestGameMode == null) {
				return "No Record";
			} else {
				switch (_latestGameMode) {
				case NORMALMATH:
					return "Normal Mode";
				case ENDLESSMATH:
					return "Endless Mode";
				default:
					return "Practise";
				}
			}
		}

		/**
		 * 
		 * @return The score of the latest game
		 */
		public String reportLatestGameScore() {
			if (_latestGameScore == null) {
				return "No Record";
			} else {
				return _latestGameScore.toString();
			}
		}

		/**
		 * 
		 * @param gameMode
		 * @return An int array of the score history of the game mode, null will be
		 *         returned if there is no record
		 */
		public int[] getHistory(Mode gameMode) {
			List<Integer> resultList;
			// get the score history of the specific game mode
			switch (gameMode) {
			case NORMALMATH:
				resultList = _results.get(NORMAL);
				break;
			case ENDLESSMATH:
				resultList = _results.get(ENDLESS);
				break;
			default:
				resultList = _results.get(PRACTISE);
			}

			if (resultList != null) {
				// if the history is not empty, turn it into an int array and return
				int[] history = new int[resultList.size()];
				for (int i = 0; i < resultList.size(); i++) {
					history[i] = resultList.get(i);
				}
				return history;
			} else {
				// otherwise return null
				return null;
			}
		}

		/**
		 * Search through the score history of normal mode games and return the highest
		 * score. Performance is not optimized as the length of the score history will
		 * be small.
		 * 
		 * @return personal highest score of the normal mode games
		 */
		public int getNormalModePersonalBest() {
			List<Integer> resultList = _results.get(NORMAL);
			return findMax(resultList);
		}

		/**
		 * Search through the score history of endless mode games and return the highest
		 * score. Performance is not optimized as the length of the score history will
		 * be small.
		 * 
		 * @return personal highest score of the endless mode games
		 */
		public int getEndlessModePersonalBest() {
			List<Integer> resultList = _results.get(ENDLESS);
			return findMax(resultList);
		}

		private int findMax(List<Integer> resultList) {
			int max = resultList.get(0);
			for (int i = 1; i < resultList.size(); i++) {
				if (resultList.get(i) > max) {
					max = resultList.get(i);
				}
			}
			return max;
		}

	}

}
