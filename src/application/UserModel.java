package application;

public class UserModel {

	private static UserModel _modelInstance;

	/**
	 * Constructor
	 */
	private UserModel() {

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

}
