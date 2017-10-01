package application;

import java.util.List;
import java.util.Map;

public class UserModel {

	private static UserModel _modelInstance;

	/**
	 * A map that has players' names as keys, and lists of result pairs as values.
	 */
	private static Map<String, List<ResultPair>> _userRecords;

	/**
	 * Constructor
	 */
	private UserModel() {
		_userRecords = loadUserRecords();
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
	 * @param playerName
	 * @param gameMode
	 *            (either NORMALMATH or ENDLESSMATH)
	 * @param score
	 */
	public void appendRecord(String playerName, Mode gameMode, int score) {
		// TODO Auto-generated method stub

	}

	/**
	 * Load the user records from local files
	 * 
	 * @return user record map
	 */
	private Map<String, List<ResultPair>> loadUserRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * The class stores a result pair which is a pair of the game mode and score. It
	 * overrides java.lang.Object's toString() method and returns a string of
	 * specific ResultPair entry format: "[mode (Normal/Endless)],[score (an integer
	 * number)]". This toString method must be used when write the result pair into
	 * a local file, and when read from the local file, the unmodified lines of the
	 * file can be used to construct ResultPairs. This ensures the robustness when
	 * writing to and reading from local files.
	 *
	 */
	private class ResultPair {
		private final Mode _mode;
		private final int _score;

		ResultPair(Mode gameMode, int score) {
			this._mode = gameMode;
			this._score = score;
		}

		/**
		 * Construct a result pair using an entry string in ResultPair entry format:
		 * "[mode (Normal/Endless)],[score (an integer number)]"
		 * 
		 * @param entry
		 */
		ResultPair(String entry) {
			String[] pair = entry.split(",");
			if (pair[0].equals("Normal")) {
				this._mode = Mode.NORMALMATH;
			} else if (pair[0].equals("Endless")) {
				this._mode = Mode.ENDLESSMATH;
			} else {
				this._mode = null;
			}
			_score = Integer.parseInt(pair[1]);
		}

		/**
		 * Return a string in a ResultPair entry format: "[mode (Normal/Endless)],[score
		 * (an integer number)]"
		 */
		@Override
		public String toString() {
			String mode = "Unknown";
			if (_mode == Mode.NORMALMATH) {
				mode = "Normal";
			} else if (_mode == Mode.ENDLESSMATH) {
				mode = "Endless";
			}
			return mode + "," + _score;
		}

		/**
		 * 
		 * @return true if the result pair is a result pair for a normal mode math game
		 */
		public boolean isNormalMode() {
			if (this._mode == Mode.NORMALMATH) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 
		 * @return true if the result pair is a result pair for an endless mode math
		 *         game
		 */
		public boolean isEndlessMode() {
			if (this._mode == Mode.ENDLESSMATH) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 
		 * @return the score of the result pair
		 */
		public int getScore() {
			return _score;
		}

	}

}
